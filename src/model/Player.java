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
            if (!field.isOccupied(xMove, yMove)) {
                field.setCell(xMove, yMove, this);
            }
        } else {
            KITree kiTree = ctrl.getGame().calcKITree();

            KITree.Move optimalMove = kiTree.findBestMove(this);
            if (optimalMove == null) {
                System.out.println("random!");
                optimalMove = kiTree.randomMove();
            }

            if (!field.isOccupied(optimalMove.getX(), optimalMove.getY())) {
                field.setCell(optimalMove.getX(), optimalMove.getY(), this);
            }
        }
    }

    public String getName() {
        return name;
    }

    public boolean isLocalPlayer() {
        return localPlayer;
    }

}