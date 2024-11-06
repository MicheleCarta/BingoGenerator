package org.bingo;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BingoStripTest {

    private final BingoStrip bingoStrip = new BingoStrip();


    /**
     * - The strip contains exactly 6 tickets.
     * - Each ticket has a valid structure with 9 columns and 3 rows.
     * - Each ticket row contains exactly 5 numbers and 4 blanks.
     * - Numbers in the ticket columns are ordered from top to bottom (ASC).
     * - There are no duplicate numbers between 1 and 90 in the strip.
     */
    @Test
    void testGenerateStripOfTickets() {
        BingoStrip bingoStrip = new BingoStrip();  // Initialize BingoStrip with 6 tickets
        bingoStrip.generateStripOfTickets(6);
        assertEquals(6, bingoStrip.getTickets().size(), "Strip should contain exactly 6 tickets.");

        for (BingoTicket ticket : bingoStrip.getTickets()) {
            Set<Integer> ticketNumbers = new HashSet<>();

            int[][] rows = ticket.getTicket();
            assertEquals(3, rows.length, "Each ticket should have 3 rows.");

            for (int[] row : rows) {
                assertEquals(9, row.length, "Each row should have 9 columns.");

                long nonBlankCount = Arrays.stream(row).filter(num -> num != -1).count();
                assertEquals(5, nonBlankCount, "Each row should contain exactly 5 numbers.");

                for (int num : row) {
                    if (num != -1) {
                        assertTrue(ticketNumbers.add(num), "Duplicate number found within the ticket: " + num);
                    }
                }

            }

            for (int col = 0; col < 9; col++) {
                List<Integer> columnNumbers = new ArrayList<>();
                for (int row = 0; row < 3; row++) {
                    if (rows[row][col] != -1) {
                        columnNumbers.add(rows[row][col]);
                    }
                }
                List<Integer> sortedColumnNumbers = new ArrayList<>(columnNumbers);
                Collections.sort(sortedColumnNumbers);
                assertEquals(sortedColumnNumbers, columnNumbers, "Numbers in column " + col + " should be in ascending order.");
            }
        }
    }

    /**
     * - One ticket has a valid structure with 9 columns and 3 rows.
     * - One ticket row contains exactly 5 numbers and 4 blanks.
     * - Numbers in the ticket columns are ordered from top to bottom (ASC).
     * - There are no duplicate numbers between 1 and 90 in the ticket.
     */
    @Test
    void testGenerateBingoTicket() {
        BingoTicket bingoStrip = new BingoTicket();

        int[][] rows = bingoStrip.getTicket();
        assertEquals(3, rows.length, "Each ticket should have 3 rows.");

        Set<Integer> ticketNumbers = new HashSet<>();

        for (int[] row : rows) {
            assertEquals(9, row.length, "Each row should have 9 columns.");

            long nonBlankCount = Arrays.stream(row).filter(num -> num != -1).count();
            assertEquals(5, nonBlankCount, "Each row should contain exactly 5 numbers.");

            for (int num : row) {
                if (num != -1) {
                    assertTrue(ticketNumbers.add(num), "Duplicate number found within the ticket: " + num);
                }
            }

        }
    }


    /**
     * Test that numbers in each column are within the specified range and in ascending order.
     */
    @Test
    void testColumnsContainOrderedNumbersWithinRanges() {
        int[][] columnRanges = {
                {1, 9}, {10, 19}, {20, 29}, {30, 39}, {40, 49},
                {50, 59}, {60, 69}, {70, 79}, {80, 90}
        };

        for (BingoTicket ticket : bingoStrip.getTickets()) {
            int[][] rows = ticket.getTicket();
            for (int col = 0; col < columnRanges.length; col++) {
                int min = columnRanges[col][0];
                int max = columnRanges[col][1];
                List<Integer> columnNumbers = new ArrayList<>();

                for (int[] row : rows) {
                    int num = row[col];
                    if (num != -1) {
                        assertTrue(num >= min && num <= max, "Number " + num + " in column " + col + " is out of range.");
                        columnNumbers.add(num);
                    }
                }

                List<Integer> sortedColumnNumbers = new ArrayList<>(columnNumbers);
                Collections.sort(sortedColumnNumbers);
                assertEquals(sortedColumnNumbers, columnNumbers, "Column numbers should be in ascending order.");
            }
        }
    }

    /**
     * Test performance aspects.
     */
    @Test
    void testCalculationSpeed() {
        long timeStart = System.currentTimeMillis();
        new BingoStrip().generateStripOfTickets(10);
        long endTime = System.currentTimeMillis();

        long duration = endTime - timeStart;
        assertTrue(duration < 1000L, "Execution time should be less than 1 second.");

        System.out.println("Execution Time: " + duration + " ms");
    }
}