package controller;

import model.Encryptor;
import model.List;
import model.Player;
import model.User;
import view.View;

import javax.swing.*;
import java.io.*;

public class Controller {

    public static final String ADMIN_PASSWORD = "Geburtenrate";
    public static final String DATABASE_PATH = "database.dat";

    public static void main(String[] args) {
        new Controller();
    }

    /**
     * the view
     */
    private View view;
    /**
     * the game, may be null
     */
    private Game game;

    /**
     * the user database
     */
    private List<User> database;

    /**
     * whether the game is running
     */
    private boolean running = true;

    /**
     * the Constructor of the class Controller
     */
    public Controller() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {}

        Textures.load();

        this.view = new View(this);

        view.setState(View.State.Menu);

        readDatabase();
        refreshUserList();

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

    /**
     * reads the database from the filesystem
     */
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

                    for (int i = 0; i < split.length; i += 4) {
                        String username = split[i+0];
                        String password = split[i+1];
                        String winsString = split[i+2];
                        String lossesString = split[i+3];
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

    /**
     * saves the database to the filesystem
     */
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
                writer.write(Encryptor.encrypt(database.getContent().save() + ","));

                database.next();
            }

            writer.close();



        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new user to the database
     *
     * @param username
     * @param password
     */
    public void addUser(String username, String password){
        User user = new User(username, password);
        database.append(user);
        bubbleSort();
        refreshUserList();
    }

    /**
     * starts a new game with p1 vs p2
     *
     * @param p1
     * @param p2
     */
    public void startGame(Player p1, Player p2) {
        this.game = new Game(p1, p2, this);

        if (!game.getCurrentPlayer().isLocalPlayer()) {
            game.nextMove(-1, -1);
        }

        this.view.setState(View.State.Game);
    }

    public void bubbleSort(){
        int x = 0;

        database.toFirst();
        while (database.hasAccess()){
            x++;
            database.next();
        }

        User[] userArray = new User[x];

        database.toFirst();
        for (int i = 0; i < userArray.length; i++){
            userArray[i] = database.getContent();
            database.next();
        }


        User temp;

        for (int i = 1; i < userArray.length; i++) {
            for (int j = 0; j < userArray.length - i; j++) {
                if (userArray[j].getUsername().compareTo(userArray[j + 1].getUsername()) >= 0 ) {
                    temp = userArray[j];
                    userArray[j] = userArray[j + 1];
                    userArray[j + 1] = temp;
                }
            }
        }

        database = new List<User>();
        for (int i = 0; i < userArray.length; i++){
            database.append(userArray[i]);
        }
    }

    public int binarySearch(int z){
        int[] a = new int[10];
        for (int i = 0; i < a.length; i++){
           a[i] = i*3+1;
        }

        return search(z, 0, a.length-1, a);
    }

    public int search(int z, int low, int high, int[] a){
    if (low <= high) {
         int mid = low + (high - low) / 2;
         if (a[mid] == z) {
              return mid;
          } else if (z > a[mid]) {
              return search(z, mid + 1, high, a);
          } else if (z < a[mid]) {
              return search(z, low, mid - 1, a);
         }
    }
    return -1;
    }

    /**
     * shows the menuview
     */
    public void enterMenu() {
        this.view.setState(View.State.Menu);
        refreshUserList();
    }

    /**
     * Getter
     *
     * @return
     */
    public Game getGame() {
        return game;
    }

    /**
     * Getter
     *
     * @return
     */
    public List<User> getDatabase(){
        return database;
    }

    public void refreshUserList() {
        view.getMenuView().refreshUserList();
    }

}