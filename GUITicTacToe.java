// This project, when run, will use a JFrame to create a new window in which you will play tictactoe against someone else. Not only can you play tictactoe but you can also set your name to something and view a display how many wins each person has.
// Ayan Masud
// 9/26/23

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUITicTacToe implements ActionListener {

    JFrame frame = new JFrame();
    JButton[][] button = new JButton[3][3];
    int[][] board = new int[3][3];
    final int BLANK = 0;
    final int X_MOVE = 1;
    final int O_MOVE = 2;
    final int X_TURN = 0;
    final int O_TURN = 1;
    int turn = X_TURN;
    Container center = new Container();
    JLabel xLabel = new JLabel("X wins:0");
    JLabel oLabel = new JLabel("O wins:0");
    JButton xChangeName = new JButton("Change X's Name.");
    JButton oChangeName = new JButton("Change O's Name.");
    JTextField xChangeField = new JTextField();
    JTextField oChangeField = new JTextField();
    Container north = new Container();
    String xPlayerName = "X";
    String oPlayerName = "O";
    int xwins = 0;
    int owins = 0;

    public GUITicTacToe() { // this is the code that utilizes the JFrame to create the window along with everything within the window
        frame.setSize(400, 400); // width, height
        frame.setLayout(new BorderLayout());
        center.setLayout(new GridLayout(3, 3)); // rows, columns
        for (int i = 0; i < button.length; i++) { // sets up the buttons for use
            for (int j = 0; j < button[0].length; j++){
                button[j][i] = new JButton();
                center.add(button[j][i]);
                button[j][i].addActionListener(this);
            }
        }
        frame.add(center, BorderLayout.CENTER);
        north.setLayout(new GridLayout(3, 2));
        north.add(xLabel);
        north.add(oLabel);
        north.add(xChangeName);
        xChangeName.addActionListener(this);
        north.add(oChangeName);
        oChangeName.addActionListener(this);
        north.add(xChangeField);
        north.add(oChangeField);
        frame.add(north, BorderLayout.NORTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // this is here so that closing the window stops the code from running
        frame.setVisible(true);
    }

    public static void main(String[] args) { // starts the code by running the GUITicTacToe() method

        new GUITicTacToe();
    }

    public void actionPerformed(ActionEvent event) { // action listeners are what we use to tell use whether someone has used something on the window
        JButton current;
        boolean gridButton = false;
        for (int i = 0; i < button.length; i++) {
            for (int j = 0; j < button[0].length; j++) {
                if (event.getSource().equals(button[j][i])) {
                    gridButton = true;
                    current = button[j][i];
                    if (board[j][i] == BLANK) { // sets the button text to X and also disables the button
                        if (turn == X_TURN) {
                            current.setText("X");
                            current.setEnabled(false);
                            board[j][i] = X_MOVE;
                            turn = O_TURN;
                        }
                        else { // sets the button text to O and also disables the button
                            current.setText("O");
                            current.setEnabled(false);
                            board[j][i] = O_MOVE;
                            turn = X_TURN;
                        }
                        if (checkWin(X_MOVE) == true) { // updates X wins on the window
                            xwins++;
                            xLabel.setText(xPlayerName + " wins: " + xwins);
                            clearBoard();
                        }
                        else if (checkWin(O_MOVE) == true) { // updates O wins on the window
                            owins++;
                            oLabel.setText(oPlayerName + " wins: " + owins);
                            clearBoard();
                        }
                        else if (checkTie() == true){ // runs the clearBoard() method if there is a tie
                            clearBoard();
                        }
                    }
                }
            }
        }
        if (gridButton == false) {
            if (event.getSource().equals(xChangeName) == true) { // updates the name of the player on the window for X
                xPlayerName = xChangeField.getText();
                xLabel.setText(xPlayerName + " wins: " + xwins);
            }
            else if (event.getSource().equals(oChangeName) == true) { // updates the name of the player on the window for O
                oPlayerName = oChangeField.getText();
                oLabel.setText(oPlayerName + " wins: " + owins);
            }
        }
    }

    public boolean checkWin(int player) { // checks whether a player has won or not by looking at all possibilities
        if (board[0][0] == player && board[0][1] == player && board[0][2] == player) {
            return true;
        }
        if (board[1][0] == player && board[1][1] == player && board[1][2] == player) {
            return true;
        }
        if (board[2][0] == player && board[2][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][0] == player && board[1][0] == player && board[2][0] == player) {
            return true;
        }
        if (board[0][1] == player && board[1][1] == player && board[2][1] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][2] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[2][0] == player && board[1][1] == player && board[0][2] == player) {
            return true;
        }
        return false;
    }

    public boolean checkTie() { // checks if there are any blank spaces and if there aren't, it then becomes a tie
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (board[row][column] == BLANK) {
                    return false;
                }
            }
        }
        return true;
    }
    public void clearBoard(){ // clears the board for both the board you can't see and the board on the window as well as it activates the buttons for pressing
        for (int a = 0; a < board.length; a++) {
            for (int b = 0; b < board[0].length; b++) {
                board[a][b] = BLANK;
                button[a][b].setText("");
                button[a][b].setEnabled(true);
            }
            
        }
        turn = X_TURN;
    }

}
