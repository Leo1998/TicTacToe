package view;

import controller.Controller;

import javax.swing.*;

public class View {

    public enum State {
        Menu, Game;
    }

    private Controller ctrl;
    private State state;

    private JFrame frame;

    public View(Controller ctrl){
        this.ctrl = ctrl;

        frame = new JFrame("TicTacToe");
        frame.setSize(640, 640);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

    public void repaint() {
        frame.getContentPane().repaint();
    }

    public State getState() {
        return state;
    }

    public void setState(final State state) {
        this.state = state;

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                JPanel pane = null;
                if (state == State.Game) {
                    pane = new GameView(ctrl.getGame());
                } else if (state == State.Menu) {
                    pane = (JPanel) new MenuView(ctrl).$$$getRootComponent$$$();
                }
                frame.getContentPane().removeAll();
                frame.getContentPane().add(pane);
                frame.revalidate();
            }
        });
    }
}
