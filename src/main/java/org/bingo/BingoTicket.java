package org.bingo;

import java.util.*;

public class BingoTicket {
    private final int[][] ticket;
    private final Random random = new Random();
    private final Set<Integer> stripsNumber;
    private final static int[][] columnRanges = {
            {1, 9}, {10, 19}, {20, 29}, {30, 39}, {40, 49},
            {50, 59}, {60, 69}, {70, 79}, {80, 90}
    };

    public BingoTicket() {
        this.stripsNumber = new HashSet<>();
        this.ticket = new int[3][9];
        for (int[] row : this.ticket) Arrays.fill(row, -1);

        fillTicket();
    }

    private void fillTicket() {

        for (int col = 0; col < columnRanges.length; col++) {
            List<Integer> columnNumbers = generateUniqueColumnNumbers(columnRanges[col]);
            placeNumbersInColumn(col, columnNumbers);

        }
        finalizeTicket();
        sortColumns();
    }

    private List<Integer> generateUniqueColumnNumbers(int[] range) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        while (uniqueNumbers.size() < 3) {
            int num = random.nextInt(range[1] - range[0] + 1) + range[0];
            uniqueNumbers.add(num);
        }
        List<Integer> numbers = new ArrayList<>(uniqueNumbers);
        Collections.sort(numbers);
        return numbers;
    }

    private void placeNumbersInColumn(int col, List<Integer> columnNumbers) {
        int numbersToPlace = random.nextInt(3) + 1;
        for (int i = 0; i < numbersToPlace; i++) {
            int row;
            do {
                row = random.nextInt(3);
            } while (ticket[row][col] != -1);

            ticket[row][col] = columnNumbers.get(i);
        }
    }

    private void finalizeTicket() {
        for (int[] row : ticket) {
            List<Integer> filledColumns = new ArrayList<>();
            Collections.sort(filledColumns);
            for (int col = 0; col < 9; col++) {
                if (row[col] != -1) {
                    filledColumns.add(col);
                }
            }

            while (filledColumns.size() > 5) {
                int colToBlank = filledColumns.remove(random.nextInt(filledColumns.size()));
                row[colToBlank] = -1;
            }

            while (filledColumns.size() < 5) {
                int colToFill;
                do {
                    colToFill = random.nextInt(9);
                } while (row[colToFill] != -1);

                int num;
                do {
                    num = random.nextInt(90) + 1;
                } while (stripsNumber.contains(num));

                row[colToFill] = num;
                stripsNumber.add(num);
                filledColumns.add(colToFill);
            }
        }
    }

    private void sortColumns() {
        for (int col = 0; col < 9; col++) {
            List<Integer> numbers = new ArrayList<>();

            for (int row = 0; row < 3; row++) {
                if (ticket[row][col] != -1) {
                    numbers.add(ticket[row][col]);
                }
            }

            Collections.sort(numbers);

            int index = 0;
            for (int row = 0; row < 3; row++) {
                if (ticket[row][col] != -1) {
                    ticket[row][col] = numbers.get(index++);
                }
            }
        }
    }

    public int[][] getTicket() {
        return ticket;
    }

    public void printTicket() {
        for (int[] row : ticket) {
            for (int num : row) {
                System.out.print(num == -1 ? "[] " : String.format("%2d ", num));
            }
            System.out.println();
        }
    }

    public void markNumber(int number) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                if (ticket[row][col] == number) {
                    ticket[row][col] = -1;
                    return;
                }
            }
        }
    }

    public boolean isNumberMarked(int number) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                if (ticket[row][col] == number) {
                    return false;
                }
            }
        }
        return true;
    }

}
