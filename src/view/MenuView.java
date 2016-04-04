package view;

import controller.Controller;
import model.*;
import model.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView {

    private final Controller ctrl;

    private JPanel panel;
    private JButton logButton;
    private JButton regButton;
    private JTextField regName;
    private JTextField logName;
    private JPasswordField logPass;
    private JPasswordField regPass;
    private JPasswordField adminPass;
    private JTextField logName2;
    private JPasswordField logPass2;
    private JCheckBox multiplayerCheckBox;

    public MenuView(final Controller ctrl) {
        this.ctrl = ctrl;

        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (multiplayerCheckBox.isSelected()) {
                    if (!logName.getText().isEmpty() || !logName2.getText().isEmpty()) {
                        if (new String(logPass.getPassword()).isEmpty() || new String(logPass.getPassword()).isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Bitte Password eingeben!");                                                 //Passwort muss eingegeben sein!
                        } else {
                            List<User> database = ctrl.getDatabase();

                            Player p1 = null;

                            database.toFirst();
                            while (database.hasAccess()) {
                                if (database.getContent().getUsername().equals(logName.getText())) {
                                    if (database.getContent().getPassword().equals(new String(logPass.getPassword()))) {
                                        p1 = new Player(logName.getText(), true, ctrl);
                                    }
                                }
                                database.next();
                            }
                            Player p2 = null;

                            database.toFirst();
                            while (database.hasAccess()) {
                                if (database.getContent().getUsername().equals(logName2.getText())) {
                                    if (database.getContent().getPassword().equals(new String(logPass2.getPassword()))) {
                                        p2 = new Player(logName2.getText(), true, ctrl);
                                    }
                                }
                                database.next();
                            }

                            if (p1 == null || p2 == null) {
                                JOptionPane.showMessageDialog(null, "Fehler beim Einloggen! Bitte überprüfe deine Eingabe!");
                            } else {
                                ctrl.startGame(p1, p2);
                            }

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Bitte Username eingeben!");                                                    //Usernames auch!
                    }
                } else {
                    if (!logName.getText().isEmpty()) {
                        if (new String(logPass.getPassword()).isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Bitte Passwort eingeben!");
                        } else {
                            List<User> database = ctrl.getDatabase();

                            Player p1 = null;

                            database.toFirst();
                            while (database.hasAccess()) {
                                if (database.getContent().getUsername().equals(logName.getText())) {
                                    if (database.getContent().getPassword().equals(new String(logPass.getPassword()))) {
                                        p1 = new Player(logName.getText(), true, ctrl);

                                    }
                                }

                                database.next();
                            }

                            Player p2 = new Player("KI Player", false, ctrl);

                            if (p1 == null || p2 == null) {
                                JOptionPane.showMessageDialog(null, "Fehler beim Einloggen! Bitte überprüfe deine Eingabe!");
                            } else {
                                ctrl.startGame(p1, p2);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Bitte Username eingeben!");
                    }
                }
            }
        });

        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!regName.getText().isEmpty()) {

                    List<User> database = ctrl.getDatabase();

                    database.toFirst();
                    while (database.hasAccess()) {
                        if (database.getContent().getUsername().equals(regName.getText())) {
                            JOptionPane.showMessageDialog(null, "Der Username existiert bereits, bitte nutze einen anderen Username!");
                            return;
                        }
                        database.next();
                    }
                    if (new String(regPass.getPassword()).isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Gebe bitte ein Passwort ein!");
                    } else {
                        if (new String(adminPass.getPassword()).equals(Controller.ADMIN_PASSWORD)) {
                            ctrl.addUser(regName.getText(), new String(regPass.getPassword()));
                            JOptionPane.showMessageDialog(null, "Erfolgreiche Registrierung mit dem Username: " + regName.getText());
                            regName.setText(null);
                            regPass.setText(null);
                            adminPass.setText(null);
                        } else {
                            JOptionPane.showMessageDialog(null, "Falsches Adminpasswort!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Bitte Trage einen Namen ein!");
                }
            }
        });

        multiplayerCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!multiplayerCheckBox.isSelected()) {
                    logName2.setEnabled(false);
                    logPass2.setEnabled(false);
                } else {
                    logName2.setEnabled(true);
                    logPass2.setEnabled(true);
                }
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(7, 3, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 6, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        regName = new JTextField();
        panel1.add(regName, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        regPass = new JPasswordField();
        panel1.add(regPass, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        adminPass = new JPasswordField();
        panel1.add(adminPass, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Registration Username");
        panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Preferred Password");
        panel1.add(label2, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Admin Password");
        panel1.add(label3, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 3, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        logName = new JTextField();
        logName.setText("");
        panel2.add(logName, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        logPass = new JPasswordField();
        panel2.add(logPass, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("LogIn Username");
        panel2.add(label4, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("LogIn Password");
        panel2.add(label5, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        logButton = new JButton();
        logButton.setText("Login");
        panel.add(logButton, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        regButton = new JButton();
        regButton.setText("Registrieren");
        panel.add(regButton, new com.intellij.uiDesigner.core.GridConstraints(6, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setEnabled(true);
        label6.setText("LogIn Username Player 2");
        label6.setVerticalAlignment(0);
        label6.setVerticalTextPosition(0);
        panel3.add(label6, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        logName2 = new JTextField();
        panel3.add(logName2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("LogIn Password Player 2");
        panel3.add(label7, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        logPass2 = new JPasswordField();
        panel3.add(logPass2, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        multiplayerCheckBox = new JCheckBox();
        multiplayerCheckBox.setSelected(true);
        multiplayerCheckBox.setText("Multiplayer");
        panel.add(multiplayerCheckBox, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
