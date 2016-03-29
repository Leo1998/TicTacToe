package controller;

import model.Encryptor;
import model.List;
import model.Player;
import model.User;
import view.View;

import java.io.*;

public class Controller {

    public static final String ADMIN_PASSWORD = "Geburtenrate";
    public static final String DATABASE_PATH = "database.dat";

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

        readDatabase();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                saveDatabase();
            }
        }));

        while(running) {
            view.repaint();

            try {
                Thread.sleep(16);
            } catch(Exception e) {
            }
        }
    }

    public void readDatabase() {
        this.database = new List<User>();

        try {
            File file = new File(DATABASE_PATH);

            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    line = Encryptor.decrypt(line);
                    String[] split = line.split(",");
                    
                    if (split.length == 4) {
                        String username = split[0];
                        String password = split[1];
                        String winsString = split[2];
                        String lossesString = split[3];
                        int wins = Integer.valueOf(winsString);
                        int losses = Integer.valueOf(lossesString);

                        User user = new User(username, password);
                        user.setWins(wins);
                        user.setLosses(losses);

                        database.append(user);
                    }
                }

                reader.close();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void saveDatabase() {
        System.out.println("Saving Database to " + DATABASE_PATH);
        try {
            File file = new File(DATABASE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            database.toFirst();
            while(database.hasAccess()) {
                writer.write(Encryptor.encrypt(database.getContent().save()) + "\n");

                database.next();
            }

            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String username, String password){
        User user = new User(username, password);
        database.append(user);
    }

    public void startGame(Player p1, Player p2) {
        this.game = new Game(p1, p2, this);

        this.view.setState(View.State.Game);
    }

    public void enterMenu() {
        this.view.setState(View.State.Menu);
    }

    public Game getGame() {
        return game;
    }

    public List<User> getDatabase(){
        return database;
    }
}