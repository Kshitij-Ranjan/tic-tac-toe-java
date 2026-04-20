package Resources.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import java.time.*;
import java.awt.*;

import Resources.GameUI.*;
import Resources.GameUI.GameUI;

public class Game {
    public static String winner;
    private static HashMap<Integer, String> boxContent = new HashMap<>();
    private static int turnCount = 0;

    public static String getPlayerSign() {
        return (turnCount % 2 == 0) ? GameUI.getP1Name() : GameUI.getP2Name();
    }

    public static void startGame() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonId = e.getActionCommand();
                int boxNumber = Integer.parseInt(buttonId);
                boxContent.put(boxNumber, getPlayerSign());

                JButton clicked = (JButton) e.getSource();
                clicked.setText(getPlayerSign());
                clicked.setEnabled(false);

                if (checkWin()) {
                    GameUI.showResult();
                } else if (checkTie()) {
                    winner = "Nobody";
                    GameUI.showTie();
                }
                turnCount++;
            }
        };

        GameUI.cards(listener);
    }

    /*
    1 2 3
    4 5 6
    7 8 9
     */

    public static boolean checkWin() {
        java.util.function.BiPredicate<Integer, Integer> match = (a, b) ->
                boxContent.get(a) != null && boxContent.get(a).equals(boxContent.get(b));

        if (
                (match.test(1, 2) && match.test(2, 3)) ||
                        (match.test(4, 5) && match.test(5, 6)) ||
                        (match.test(7, 8) && match.test(9, 7)) ||
                        (match.test(1, 4) && match.test(4, 7)) ||
                        (match.test(2, 5) && match.test(5, 8)) ||
                        (match.test(3, 6) && match.test(6, 9)) ||
                        (match.test(1, 5) && match.test(5, 9)) ||
                        (match.test(3, 5) && match.test(5, 7))
        ) {
            winner = getPlayerSign();
            return true;
        }
        return false;
    }

    public static boolean checkTie() {
        return boxContent.size() == 9 && !checkWin();
    }

    public static void resetLogic() {
        boxContent.clear();
        turnCount = 0;
        winner = null;
    }
}