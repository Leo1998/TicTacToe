package controller;

import model.Field;
import model.Player;
import view.View;

public class Controller {

    public static void main(String[] args) {
        new Controller();
    }

    private View view;
    private Game game;

    private boolean running = true;

    public Controller() {
        this.view = new View(this);

        view.setState(View.State.Menu);

        while(running) {
            view.repaint();

            try {
                Thread.sleep(16);
            } catch(Exception e) {
            }
        }
    }

    public void startGame(Player p1, Player p2) {
        this.game = new Game(p1, p2);

        this.view.setState(View.State.Game);
    }

    public Game getGame() {
        return game;
    }
}
