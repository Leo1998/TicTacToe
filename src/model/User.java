package model;

public class User {

    private String username, password;
    private int wins, losses;

    public User(String u, String p){
        this.username = u;
        this.password = p;
        this.wins = 0;
        this.losses = 0;
    }

    //Getter
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public String save() {
        return username + "," + password;
    }
    
    public int getWins() {
        return wins;
    }
    public int getLosses() {
        return losses;
    }

    //Setter
    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }
}
