import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGuessGame extends JFrame implements ActionListener {
    private int numberToGuess, attemptsLeft, roundsWon, totalRounds, totalAttempts;
    private JTextField guessField;
    private JLabel messageLabel, scoreLabel, attemptsLabel;
    private JButton guessButton, restartButton;

    public NumberGuessGame() {
        setTitle("Number Guessing Game");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        restartButton = new JButton("Play Again");

        messageLabel = new JLabel("Enter a number between 1 and 100");
        scoreLabel = new JLabel("Rounds Won: 0 | Total Rounds: 0");
        attemptsLabel = new JLabel("Attempts Left: 7");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(messageLabel);
        panel.add(guessField);
        panel.add(guessButton);
        panel.add(attemptsLabel);
        panel.add(scoreLabel);
        panel.add(restartButton);

        add(panel);
        guessButton.addActionListener(this);
        restartButton.addActionListener(e -> startNewGame());

        startNewGame();
    }

    private void startNewGame() {
        numberToGuess = new Random().nextInt(100) + 1;
        attemptsLeft = 7;
        messageLabel.setText("Enter a number between 1 and 100");
        attemptsLabel.setText("Attempts Left: " + attemptsLeft);
        guessField.setText("");
        guessField.setEditable(true);
        guessButton.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userInput = guessField.getText();
        try {
            int guess = Integer.parseInt(userInput);
            attemptsLeft--;

            if (guess == numberToGuess) {
                messageLabel.setText("Correct! You guessed it!");
                roundsWon++;
                endRound();
            } else if (guess < numberToGuess) {
                messageLabel.setText("Too low! Try again.");
            } else {
                messageLabel.setText("Too high! Try again.");
            }

            if (attemptsLeft == 0 && guess != numberToGuess) {
                messageLabel.setText("Out of attempts! Number was: " + numberToGuess);
                endRound();
            }

            attemptsLabel.setText("Attempts Left: " + attemptsLeft);

        } catch (NumberFormatException ex) {
            messageLabel.setText("Please enter a valid number.");
        }
    }

    private void endRound() {
        totalRounds++;
        totalAttempts += (7 - attemptsLeft);
        scoreLabel.setText("Rounds Won: " + roundsWon + " | Total Rounds: " + totalRounds);
        guessField.setEditable(false);
        guessButton.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NumberGuessGame().setVisible(true);
        });
    }
}
