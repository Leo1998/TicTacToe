package controller;

import model.Field;
import model.Player;

import javax.swing.*;

public class Game {

    private Player p1, p2;
    private Field field;
    private Player currentPlayer;

    private Controller ctrl;

    public Game(Player p1, Player p2, Controller ctrl) {
        this.ctrl = ctrl;
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
                JOptionPane.showMessageDialog(null, "The Winner is: " + winner.getName());
                ctrl.enterMenu();
            } else if (field.isFull()) {
                JOptionPane.showMessageDialog(null, "It's a draw!");
                ctrl.enterMenu();
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
