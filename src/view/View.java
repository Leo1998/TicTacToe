package view;

import controller.Controller;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class View {

    /**
     * the state of the view
     */
    public enum State {
        Menu, Game
    }

    /**
     * the Controller
     */
    private Controller ctrl;
    /**
     * the current state
     */
    private State state;

    /**
     * the frame
     */
    private JFrame frame;

    /**
     * the menuview
     */
    private MenuView menuView;

    /**
     * Controller
     *
     * @param ctrl
     */
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

    /**
     * forces a repaint
     */
    public void repaint() {
        frame.getContentPane().repaint();
    }

    /**
     * Getter
     *
     * @return
     */
    public MenuView getMenuView() {
        return menuView;
    }

    /**
     * Getter
     *
     * @return
     */
    public State getState() {
        return state;
    }

    /**
     * sets the views state
     * @param state
     */
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
