package org.bingo;

import java.util.*;

public class BingoGame {
    private final List<BingoTicket> tickets;
    private final Set<Integer> calledNumbers;
    private final List<Integer> numberPool;

    public BingoGame(List<BingoTicket> tickets) {

        this.tickets = tickets;
        this.calledNumbers = new HashSet<>();
        this.numberPool = new ArrayList<>();


        for (int i = 1; i <= 90; i++) {
            numberPool.add(i);
        }
        tickets.forEach(BingoTicket::printTicket);
    }

    public void simulateGame() {
        Collections.shuffle(numberPool);
        System.out.println("Starting Bingo Game!");

        for (int number : numberPool) {
            calledNumbers.add(number);
            System.out.println("Number called: " + number);

            boolean markedOnAnyTicket = false;

            for (BingoTicket ticket : tickets) {
                if (!ticket.isNumberMarked(number)) {
                    ticket.markNumber(number);
                    markedOnAnyTicket = true;
                }
            }

            if (markedOnAnyTicket) {
                System.out.println("Number " + number + " was successfully marked on a ticket.");
            } else {
                System.out.println("Number " + number + " could not be marked on any ticket (not present).");
            }
        }

        System.out.println("All numbers have been called. Final ticket status:");
        tickets.forEach(BingoTicket::printTicket);
    }
}
