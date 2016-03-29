package controller;

import model.Field;
import model.Player;

public class Game {

    private Player p1, p2;
    private Field field;
    private Player currentPlayer;

    public Game(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.field = new Field(p1, p2);

        this.currentPlayer = p1;
    }

    public void nextMove(int xMove, int yMove) {
        if (!field.isOccupied(xMove, yMove)) {
            currentPlayer.move(field, xMove, yMove);

            Player winner = field.getWinner();
            if (winner != null) {
                System.out.println("The winner is: " + winner.getName());
                System.exit(0);
            }

            if (field.isFull()) {
                System.out.println("It's a draw!!!");
                System.exit(0);
            }

            if (currentPlayer == p1) {
                currentPlayer = p2;
            } else {
                currentPlayer = p1;
            }
        }
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public Field getField() {
        return field;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
