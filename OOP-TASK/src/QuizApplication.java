import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class QuizApplication {
    private static Scanner input = new Scanner(System.in);
    private  Quiz quiz;

    public QuizApplication() {
        this.quiz = new Quiz();
        loadQuestions();
    }

    private void loadQuestions() {
        quiz.addQuestion(new Question(
                "How is liverpool winger   ",
                Arrays.asList("salah", "messi", "cr7", "isak"),
                0
        ));

        quiz.addQuestion(new Question(
                "what is the lang used in Germany   ",
                Arrays.asList("German", "English ", "c++", "hello"),
                0
        ));

        quiz.addQuestion(new Question(
                "What does OOP stand for?",
                Arrays.asList("Object-Oriented Programming", "hello world ", "test 2", "hey"),
                0
        ));

        quiz.addQuestion(new Question(
                "How is liverpool winger   ",
                Arrays.asList("salah", "messi", "cr7", "isak"),
                0
        ));
        quiz.addQuestion(new Question(
                "What does OOP stand for?",
                Arrays.asList("Object-Oriented Programming", "hello world ", "test 2", "hey"),
                0
        ));
        quiz.addQuestion(new Question(
                "How is liverpool winger   ",
                Arrays.asList("salah", "messi", "cr7", "isak"),
                0
        ));
    }

    public void start() {
        System.out.println("Welcome to the Quiz Application!");
        System.out.print("Enter your name: ");
        String userName = input.nextLine();
        User user = new User(userName);

        System.out.println("Hello, " + user.getUsername() + "Let's start the quiz.");

        List<Question> selectedQuestions = quiz.getQuestions();

        int questionNumber = 1;
        for (Question question : selectedQuestions) {
            System.out.println("Q" + questionNumber + ": " + question.getQuestion());
            List<String> options = question.getAnswers();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            System.out.print("Your answer (1-" + options.size() + "): ");
            int answer = input.nextInt() - 1;

            if (question.isCorrect(answer)) {
                System.out.println("Correct");
                user.increaseScore();
            }
            questionNumber++;
        }

        System.out.println("Quiz finished");
        System.out.println(user.getUsername() + ", your final score is: " + user.getScore() + "/" + selectedQuestions.size());
    }


    public static void main(String[] args) {
        QuizApplication app = new QuizApplication();
        app.start();
    }
}