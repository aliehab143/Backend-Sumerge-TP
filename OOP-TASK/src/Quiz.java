import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz {
    private List<Question> questions;
    public Quiz() {
        this.questions = new ArrayList<Question>();
    }
    public void addQuestion(Question question) {
        questions.add(question);
    }
    public List<Question> getQuestions() {
        Collections.shuffle(questions);
        return questions.subList(0, 3); // im taking 3 random questions
    }
}
