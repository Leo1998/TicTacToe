package controller;

import model.Field;
import model.List;
import model.Player;
import model.User;
import view.View;

public class Controller {

    public static final String ADMIN_PASSWORD = "Geburtenrate";

    public static void main(String[] args) {
        new Controller();
    }

    private View view;
    private Game game;

    private List<User> database;

    private boolean running = true;

    public Controller() {
        this.view = new View(this);

        view.setState(View.State.Menu);

        database = new List<User>();

        while(running) {
            view.repaint();

            try {
                Thread.sleep(16);
            } catch(Exception e) {
            }
        }
    }

    public void addUser(String username, String password){
        User user = new User(username, password);
        database.append(user);
    }

    public void startGame(Player p1, Player p2) {
        this.game = new Game(p1, p2);

        this.view.setState(View.State.Game);
    }

    public Game getGame() {
        return game;
    }

    public List<User> getDatabase(){
        return database;
    }
}