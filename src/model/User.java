package model;

public class User {

    private String username, password;

    public User(String u, String p){
        this.username = u;
        this.password = p;
    }

    //Getter
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
