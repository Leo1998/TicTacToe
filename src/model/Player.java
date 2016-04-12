package model;

import controller.Controller;

public class Player {

    /**
     * the name
     */
    private String name;
    /**
     * if the player is a localPlayer, or a KIPlayer
     */
    private boolean localPlayer;
    /**
     * the controller
     */
    private Controller ctrl;

    /**
     * Constructor
     *
     * @param name
     * @param localPlayer
     * @param ctrl
     */
    public Player(String name, boolean localPlayer, Controller ctrl) {
        this.name = name;
        this.localPlayer = localPlayer;
        this.ctrl = ctrl;
    }

    /**
     * the player does his move
     *
     * @param field
     * @param xMove
     * @param yMove
     */
    public void move(Field field, int xMove, int yMove) {
        if (localPlayer) {
            if (!field.isOccupied(xMove, yMove)) {
                field.setCell(xMove, yMove, this);
            }
        } else {
            KITree kiTree = ctrl.getGame().calcKITree();

            KITree.Move optimalMove = kiTree.findBestMove(this);
            if (optimalMove == null) {
                //System.out.println("random!");
                optimalMove = kiTree.randomMove();
            }

            if (!field.isOccupied(optimalMove.getX(), optimalMove.getY())) {
                field.setCell(optimalMove.getX(), optimalMove.getY(), this);
            }
        }
    }

    /**
     * Getter
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Getter
     *
     * @return
     */
    public boolean isLocalPlayer() {
        return localPlayer;
    }

}