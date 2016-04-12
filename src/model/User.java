package model;

public class User {

    /**
     * username and password
     */
    private String username, password;
    /**
     * wins and losses
     */
    private int wins, losses;

    /**
     * Constructor
     *
     * @param u
     * @param p
     */
    public User(String u, String p){
        this.username = u;
        this.password = p;
        this.wins = 0;
        this.losses = 0;
    }

    /**
     * save the users data
     * @return
     */
    public String save() {
        return username + "," + password + "," + wins + "," + losses;
    }

    /**
     * Getter
     *
     * @return
     */
    public String getUsername() {
        return username;
    }
    /**
     * Getter
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter
     *
     * @return
     */
    public int getWins() {
        return wins;
    }
    /**
     * Getter
     *
     * @return
     */
    public int getLosses() {
        return losses;
    }

    /**
     * Setter
     *
     * @param wins
     */
    public void setWins(int wins) {
        this.wins = wins;
    }

    /**
     * Setter
     *
     * @param losses
     */
    public void setLosses(int losses) {
        this.losses = losses;
    }
}
