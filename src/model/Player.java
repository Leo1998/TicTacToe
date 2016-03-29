package model;

import controller.Controller;
import model.Field;

public class Player {

    private String name;
    private boolean localPlayer;
    private Controller ctrl;

    public Player(String name, boolean localPlayer, Controller ctrl) {
        this.name = name;
        this.localPlayer = localPlayer;
        this.ctrl = ctrl;
    }

    public void move(Field field, int xMove, int yMove) {
        if (localPlayer) {
            field.setCell(xMove, yMove, this);
        } else {

        }
    }

    public String getName() {
        return name;
    }

    public boolean isLocalPlayer() {
        return localPlayer;
    }

    public Controller getCtrl() {
        return ctrl;
    }

}