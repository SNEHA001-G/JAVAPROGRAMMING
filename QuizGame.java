import java.util.*;
import java.util.concurrent.TimeUnit;

class QuizQuestion {
    private String question;
    private String[] options;
    private int correctAnswerIndex;

    public QuizQuestion(String question, String[] options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

public class QuizGame {
    private static final int TIME_LIMIT = 30; // Time limit per question in seconds
    private static List<QuizQuestion> questions = new ArrayList<>();
    private static int score = 0;
    private static int currentQuestionIndex = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Add quiz questions to the list
        questions.add(new QuizQuestion(
            "What is the capital of France?", 
            new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 
            2
        ));
        questions.add(new QuizQuestion(
            "Which planet is known as the Red Planet?", 
            new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, 
            1
        ));
        questions.add(new QuizQuestion(
            "Who wrote 'Romeo and Juliet'?", 
            new String[]{"Shakespeare", "Dickens", "Austen", "Hemingway"}, 
            0
        ));

        // Start the quiz
        while (currentQuestionIndex < questions.size()) {
            askQuestion(scanner);
            currentQuestionIndex++;
        }

        // Show the result
        showResult();
    }

    private static void askQuestion(Scanner scanner) {
        QuizQuestion currentQuestion = questions.get(currentQuestionIndex);

        System.out.println("\nQuestion " + (currentQuestionIndex + 1) + ": " + currentQuestion.getQuestion());

        // Display options
        String[] options = currentQuestion.getOptions();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }

        // Start the timer
        long startTime = System.currentTimeMillis();
        long endTime = startTime + TimeUnit.SECONDS.toMillis(TIME_LIMIT);

        // Get user input
        int userAnswer = -1;
        boolean answered = false;
        while (System.currentTimeMillis() < endTime) {
            if (scanner.hasNextInt()) {
                userAnswer = scanner.nextInt();
                if (userAnswer >= 1 && userAnswer <= options.length) {
                    answered = true;
                    break;
                } else {
                    System.out.println("Invalid option. Please enter a number between 1 and " + options.length + ".");
                }
            }
        }

        if (!answered) {
            System.out.println("Time's up! Moving to the next question.");
        } else if (userAnswer - 1 == currentQuestion.getCorrectAnswerIndex()) {
            score++;
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect. The correct answer was: " + options[currentQuestion.getCorrectAnswerIndex()]);
        }
    }

    private static void showResult() {
        System.out.println("\nQuiz Over!");
        System.out.println("Your final score: " + score + " out of " + questions.size());
        System.out.println("Thank you for playing!");
    }
}
