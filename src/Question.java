import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Question {
    protected String prompt;
    protected ArrayList<String> answers;
    protected int correct;

    /**
     * create a Question object
     * @param prompt
     * @param answers
     * @param correct
     */
    public Question(String prompt, ArrayList<String> answers, int correct) {

        this.prompt = prompt;

        // randomize answer order
        String correctAns = answers.get(correct);
        Collections.shuffle(answers);
        this.answers = answers;

        for (int i=0; i<4; i++) // find the correct answer
            if (answers.get(i) == correctAns)
                this.correct = i;
    }

    /**
     * accessor for prompt
     * @return prompt
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * accessor for answers
     * @return four answers including the correct one in random order
     */
    public ArrayList<String> getAnswers() {
        return answers;
    }

    /**
     *
     * @return
     */
    public int getCorrect() {
        return correct;
    }

    /**
     * returns true if correct answer is given
     * @param x answer
     * @return if correct
     */
    public boolean isCorrect(int x) {
        return x == correct;
    }


}