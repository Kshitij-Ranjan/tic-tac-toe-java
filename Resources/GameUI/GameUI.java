package Resources.GameUI;

import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import java.time.*;
import java.awt.*;
import Resources.Game.*;

public class GameUI {
    public static int[] btns = {1,2,3,4,5,6,7,8,9};
    private static CardLayout cardLayout = new CardLayout();
    private static JPanel mainContainer = new JPanel(cardLayout);
    private static String p1Name = "";
    private static String p2Name = "";

    public static void cards(ActionListener listener) {
        JFrame welcome = new JFrame("Tic Tac Toe");
        welcome.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel setPlayer = new JPanel();
        setPlayer.setBackground(Color.LIGHT_GRAY);
        setPlayer.setLayout(new BoxLayout(setPlayer, BoxLayout.Y_AXIS));
        setPlayer.setBorder(new EmptyBorder(50, 50, 50, 50));

        JLabel l1 = new JLabel("Player 1, what can I call you?");
        l1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField p1Field = new JTextField(20);
        p1Field.setMaximumSize(new Dimension(400, 30));

        JLabel l2 = new JLabel("And Player 2?");
        l2.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField p2Field = new JTextField(20);
        p2Field.setMaximumSize(new Dimension(400, 30));

        JButton toGame = new JButton("Let's gooo...");
        toGame.setAlignmentX(Component.CENTER_ALIGNMENT);

        toGame.addActionListener(e -> {
            p1Name = p1Field.getText();
            p2Name = p2Field.getText();
            if(p1Name.isEmpty() || p2Name.isEmpty()) {
                JOptionPane.showMessageDialog(welcome, "Please enter both names!");
            } else {
                cardLayout.show(mainContainer, "Game");
            }
        });

        setPlayer.add(l1);
        setPlayer.add(p1Field);
        setPlayer.add(javax.swing.Box.createRigidArea(new Dimension(0, 20)));
        setPlayer.add(l2);
        setPlayer.add(p2Field);
        setPlayer.add(javax.swing.Box.createRigidArea(new Dimension(0, 30)));
        setPlayer.add(toGame);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(100, 100, 100, 100));
        buttonPanel.setLayout(new GridLayout(3, 3, 5, 5));
        for (int i = 0; i <= 8; i++) {
            JButton btn = new JButton("" + btns[i]);
            btn.setActionCommand("" + btns[i]);
            btn.addActionListener(listener);
            buttonPanel.add(btn);
        }

        JPanel resultCard = new JPanel();
        resultCard.setLayout(new BoxLayout(resultCard, BoxLayout.Y_AXIS));
        resultCard.setBackground(Color.decode("#f1c7ff"));

        resultText.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultText.setFont(new Font("Arial", Font.BOLD, 24));

        JButton restartGameBtn = new JButton("Restart");
        restartGameBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        restartGameBtn.addActionListener(e -> {
            Game.resetLogic();
            resetButtons(buttonPanel);
            cardLayout.show(mainContainer, "PlayerNames");
        });

        resultCard.add(javax.swing.Box.createVerticalGlue());
        resultCard.add(resultText);
        resultCard.add(javax.swing.Box.createRigidArea(new Dimension(0, 20)));
        resultCard.add(restartGameBtn);
        resultCard.add(javax.swing.Box.createVerticalGlue());

        mainContainer.add(setPlayer, "PlayerNames");
        mainContainer.add(buttonPanel, "Game");
        mainContainer.add(resultCard, "Result");

        welcome.add(mainContainer);
        welcome.setSize(600, 600);
        welcome.setLocationRelativeTo(null);
        welcome.setVisible(true);
    }

    private static JLabel resultText = new JLabel();

    public static void showResult() {
        resultText.setText("Congratulations, " + Game.winner + " !!!");
        cardLayout.show(mainContainer, "Result");
    }

    public static void showTie() {
        resultText.setText("It's a Draw! Well played both.");
        cardLayout.show(mainContainer, "Result");
    }

    public static void resetButtons(JPanel buttonPanel) {
        Component[] components = buttonPanel.getComponents();

        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof JButton) {
                JButton b = (JButton) components[i];
                b.setText("");
                b.setEnabled(true);
            }
        }
    }

    public static String getP1Name() { return p1Name; }
    public static String getP2Name() { return p2Name; }
}