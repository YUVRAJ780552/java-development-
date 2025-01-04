import java.util.*;
import java.util.concurrent.*;

class Quiz {
    private static class Question {
        String questionText;
        String[] options;
        int correctOption;

        Question(String questionText, String[] options, int correctOption) {
            this.questionText = questionText;
            this.options = options;
            this.correctOption = correctOption;
        }
    }

    private final List<Question> questions = new ArrayList<>();
    private final Map<Integer, Boolean> userAnswers = new HashMap<>();
    private int score = 0;

    public Quiz() {
        questions.add(new Question("What is the capital of France?", new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"}, 3));
        questions.add(new Question("What is 5 + 3?", new String[]{"1. 5", "2. 8", "3. 10", "4. 15"}, 2));
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"1. Earth", "2. Mars", "3. Venus", "4. Jupiter"}, 2));
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + question.questionText);

            for (String option : question.options) {
                System.out.println(option);
            }

            System.out.println("You have 10 seconds to answer.");
            int answer = getUserAnswerWithTimer(scanner, 10);
            if (answer != -1) {
                userAnswers.put(i, answer == question.correctOption);
                if (answer == question.correctOption) {
                    score++;
                }
            } else {
                System.out.println("Time's up! Moving to the next question.");
                userAnswers.put(i, false);
            }
        }

        displayResults();
    }

    private int getUserAnswerWithTimer(Scanner scanner, int seconds) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(() -> {
            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number corresponding to the options.");
                scanner.next();
            }
            return scanner.nextInt();
        });

        try {
            return future.get(seconds, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            future.cancel(true);
            return -1;
        } catch (Exception e) {
            System.out.println("An error occurred while getting your answer.");
            return -1;
        } finally {
            executor.shutdownNow();
        }
    }

    private void displayResults() {
        System.out.println("\n===== Quiz Results =====");
        System.out.println("Your score: " + score + " / " + questions.size());

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            boolean isCorrect = userAnswers.getOrDefault(i, false);
            System.out.println("\nQuestion " + (i + 1) + ": " + question.questionText);
            System.out.println("Correct Answer: " + question.options[question.correctOption - 1]);
            System.out.println("Your Answer: " + (isCorrect ? "Correct" : "Incorrect"));
        }
    }

    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.start();
    }
}
