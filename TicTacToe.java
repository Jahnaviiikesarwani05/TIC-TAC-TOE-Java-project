import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends Frame implements ActionListener {

    Button buttons[] = new Button[9];
    boolean playerX = true;

    public TicTacToe() {

        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setLayout(new GridLayout(3, 3));

        Font f = new Font("Arial", Font.BOLD, 40);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new Button("");
            buttons[i].setFont(f);
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        Button b = (Button) e.getSource();

        if (!b.getLabel().equals(""))
            return;

        if (playerX)
            b.setLabel("X");
        else
            b.setLabel("O");

        playerX = !playerX;

        checkWinner();
    }

    void checkWinner() {

        int win[][] = {
                {0,1,2},
                {3,4,5},
                {6,7,8},
                {0,3,6},
                {1,4,7},
                {2,5,8},
                {0,4,8},
                {2,4,6}
        };

        for (int i = 0; i < win.length; i++) {

            String a = buttons[win[i][0]].getLabel();
            String b = buttons[win[i][1]].getLabel();
            String c = buttons[win[i][2]].getLabel();

            if (!a.equals("") && a.equals(b) && b.equals(c)) {

                Dialog d = new Dialog(this, "Winner", true);
                d.setLayout(new FlowLayout());

                Label l = new Label(a + " Wins!");

                Button ok = new Button("OK");

                ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        d.dispose();
                        resetBoard();
                    }
                });

                d.add(l);
                d.add(ok);

                d.setSize(200,100);
                d.setVisible(true);

                return;
            }
        }

        boolean draw = true;

        for (int i = 0; i < 9; i++) {
            if (buttons[i].getLabel().equals("")) {
                draw = false;
                break;
            }
        }

        if (draw) {

            Dialog d = new Dialog(this, "Draw", true);
            d.setLayout(new FlowLayout());

            Label l = new Label("Match Draw!");

            Button ok = new Button("OK");

            ok.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    d.dispose();
                    resetBoard();
                }
            });

            d.add(l);
            d.add(ok);

            d.setSize(200,100);
            d.setVisible(true);
        }
    }

    void resetBoard() {

        for (int i = 0; i < 9; i++) {
            buttons[i].setLabel("");
        }

        playerX = true;
    }

    public static void main(String args[]) {
        new TicTacToe();
    }
}
