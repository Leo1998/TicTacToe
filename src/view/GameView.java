package view;

import controller.Controller;
import controller.Game;
import model.Field;
import model.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class GameView extends JPanel implements MouseListener, ComponentListener {

    private Game game;
    private int fieldX;
    private int fieldY;
    private int fieldW;
    private int fieldH;

    private static BufferedImage background;

    static {
        background = loadImage("/res/background.png");
    }

    private static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(GameView.class.getResourceAsStream(path));
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public GameView(Game game) {
        this.game = game;

        this.addMouseListener(this);
        this.addComponentListener(this);

        this.setBackground(Color.BLACK);

        this.onResize(this.getWidth(), this.getHeight());
    }

    private void onResize(int w, int h) {
        int r = Math.min(w, h);
        r -= r / 10;

        fieldX = (w / 2) - (r / 2);
        fieldY = (h / 2) - (r / 2);
        fieldW = r;
        fieldH = r;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(background, 0, 0, getWidth(), getHeight(), null);

        g2d.setColor(Color.white);
        g2d.drawString("Aktueller Spieler: " + game.getCurrentPlayer().getName(), 10, 25);

        Field field = game.getField();
        if (field != null) {
            drawField(g2d, field, fieldX, fieldY, fieldW, fieldH);
        }
    }

    private void drawField(Graphics2D g2d, Field field, int x, int y, int w, int h) {
        g2d.setColor(Color.white);

        g2d.drawLine(x, y, x, y + h);
        g2d.drawLine(x, y, x + w, y);
        g2d.drawLine(x, y + h, x + w, y + h);
        g2d.drawLine(x + w, y + h, x + w, y);

        int w3 = w / 3;
        int h3 = h / 3;
        g2d.drawLine(x + w3, y, x + w3, y + h);
        g2d.drawLine(x + w3*2, y, x + w3*2, y + h);
        g2d.drawLine(x, y + h3, x + w, y +  + h3);
        g2d.drawLine(x, y + h3*2, x + w, y + h3*2);

        for (int xf = 0; xf < 3; xf++) {
            for (int yf = 0; yf < 3; yf++) {
                Player cell = field.getCell(xf, yf);

                if (cell != null) {
                    int x0 = x + xf * (w / 3);
                    int y0 = y + yf * (h / 3);

                    if (cell == game.getP1()) {
                        g2d.drawLine(x0, y0, x0 + (w / 3), y0 + (h / 3));
                        g2d.drawLine(x0, y0 + (h / 3), x0 + (w / 3), y0);
                    } else if (cell == game.getP2()) {
                        g2d.drawOval(x0, y0, (w / 3), (h / 3));
                    }
                }
            }
        }
    }

    // mouse listener

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mb = e.getButton();
        int mx = e.getX();
        int my = e.getY();

        if (mb == MouseEvent.BUTTON1) {
            if (mx >= fieldX && mx < fieldX + fieldW && my >= fieldY && my < fieldY + fieldH) {
                int x0 = (mx - fieldX) / (fieldW / 3);
                int y0 = (my - fieldY) / (fieldH / 3);

                game.nextMove(x0, y0);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    // component listener

    @Override
    public void componentResized(ComponentEvent e) {
        onResize(e.getComponent().getWidth(), e.getComponent().getHeight());
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
}
