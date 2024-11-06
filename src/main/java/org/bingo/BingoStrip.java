package org.bingo;

import java.util.ArrayList;
import java.util.List;

public class BingoStrip {
    private final List<BingoTicket> tickets = new ArrayList<>();

    public BingoStrip() {
    }

    public void generateStripOfTickets(int strips) {
        for (int i = 0; i < strips; i++) {
            tickets.add(new BingoTicket());
        }
    }

    public void printStrip() {
        for (int i = 0; i < tickets.size(); i++) {
            System.out.printf("Strip %d:%n", i + 1);
            tickets.get(i).printTicket();
            System.out.println();
        }
    }

    public List<BingoTicket> getTickets() {
        return tickets;
    }

}