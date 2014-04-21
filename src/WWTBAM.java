import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class WWTBAM {
    public static void main (String[] args) {
        ArrayList<Question> questions = QuestionReader.readQuestionsFromFile("questions.xml");
        for (Iterator q=questions.iterator(); q.hasNext();) {
            Question question = (Question) q.next();
            System.out.println(question.getPrompt());
            for (int i=0; i<4; i++) {
                if (i==question.getCorrect())
                    System.out.print(">>>");
                System.out.println(question.getAnswers().get(i));
            }
        }
    }
}