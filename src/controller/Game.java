package controller;

import model.*;

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

        this.currentPlayer = Math.random() > 0.5 ? p1 : p2;
    }

    public void nextMove(int xMove, int yMove) {
        if ((xMove == -1 && yMove == -1) || !field.isOccupied(xMove, yMove)) {
            currentPlayer.move(field, xMove, yMove);

            Player winner = field.getWinner();
            if (winner != null) {
                JOptionPane.showMessageDialog(null, "The Winner is: " + winner.getName());

                List<User> database = ctrl.getDatabase();

                database.toFirst();
                while (database.hasAccess()) {
                    if (winner.getName().equals(database.getContent().getUsername())) {
                        database.getContent().setWins(database.getContent().getWins() + 1);
                    }
                    database.next();
                }

                database.toFirst();
                while (database.hasAccess()) {
                    Player loser = p1 == winner ? p2 : p1;

                    if (loser.getName().equals(database.getContent().getUsername())) {
                        database.getContent().setLosses(database.getContent().getLosses() + 1);
                    }
                    database.next();
                }

                int reply = JOptionPane.showConfirmDialog(null, "Rematch?",null, JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION){
                    this.field = new Field(p1, p2);

                    this.currentPlayer = Math.random() > 0.5 ? p1 : p2;
                }else {
                    ctrl.enterMenu();
                }
            } else if (field.isFull()) {
                JOptionPane.showMessageDialog(null, "It's a draw!");

                int reply = JOptionPane.showConfirmDialog(null, "Rematch?",null, JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION){
                    this.field = new Field(p1, p2);

                    this.currentPlayer = Math.random() > 0.5 ? p1 : p2;
                }else {
                    ctrl.enterMenu();
                }
            } else {

                if (currentPlayer == p1) {
                    currentPlayer = p2;
                } else {
                    currentPlayer = p1;
                }

                if (!currentPlayer.isLocalPlayer()) {
                    nextMove(-1, -1);
                }
            }
        }
    }

    public KITree calcKITree() {
        Player otherPlayer = currentPlayer == p1 ? p2 : p1;

        KITree kiTree = new KITree(field);
        kiTree.calculate(currentPlayer, otherPlayer);

        return kiTree;
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
