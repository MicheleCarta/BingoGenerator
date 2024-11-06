package org.bingo;

public class Main {
    public static void main(String[] args) {
        int numStrips = 6;

        if (args.length > 0) {
            try {
                numStrips = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number of strips provided, using default value of 6.");
            }
        }
        BingoStrip bingoStrip = new BingoStrip();
        bingoStrip.generateStripOfTickets(numStrips);
        bingoStrip.printStrip();
        BingoGame game = new BingoGame(bingoStrip.getTickets());
        game.simulateGame();
    }
}