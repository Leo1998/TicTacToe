package view;

import controller.Controller;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class View {

    public enum State {
        Menu, Game
    }

    private Controller ctrl;
    private State state;

    private JFrame frame;

    private MenuView menuView;

    public View(final Controller ctrl){
        this.ctrl = ctrl;

        this.menuView = new MenuView(ctrl);

        frame = new JFrame("TicTacToe");
        frame.setSize(640, 640);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setFocusable(true);
        frame.requestFocus();

        this.frame.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        frame.setVisible(true);
    }

    public void repaint() {
        frame.getContentPane().repaint();
    }

    public MenuView getMenuView() {
        return menuView;
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
                    pane = (JPanel) menuView.$$$getRootComponent$$$();
                }

                frame.getContentPane().removeAll();
                frame.getContentPane().add(pane);
                frame.revalidate();
            }
        });
    }


}
