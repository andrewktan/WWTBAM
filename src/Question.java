import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Question {
    protected String prompt;
    protected ArrayList<String> answers;
    protected int correct, difficulty;

    /**
     * create a Question object
     *
     * @param prompt
     * @param answers
     * @param correct
     */
    public Question(String prompt, ArrayList<String> answers, int correct, int difficulty) {
        // set prompt and difficulty
        this.prompt = prompt;
        this.difficulty = difficulty;

        // randomize answer order
        String correctAns = answers.get(correct);
        Collections.shuffle(answers);
        this.answers = answers;

        for (int i = 0; i < 4; i++) // find the correct answer
            if (answers.get(i) == correctAns)
                this.correct = i;
    }

    /**
     * Read from file
     *
     * @param str
     * @return ArrayList of Question objects
     */
    public static ArrayList<Question> readQuestionsFromFile(URL url) {
        // create ArrayList of questions
        ArrayList<Question> ret = new ArrayList<Question>();

        try {
            // create Document object
            SAXReader reader = new SAXReader();
            Document document = reader.read(url);

            // read data
            Element root = document.getRootElement();
            List questions = root.selectNodes("/questions/question"); // get list of questions

            // iterate through questions
            for (Iterator q = questions.iterator(); q.hasNext(); ) {
                Element question = (Element) q.next();

                // get prompt
                String prompt = ((Element) question.selectNodes("prompt").get(0)).getText();

                // get difficulty
                int difficulty = Integer.parseInt(question.attributeValue("difficulty"));

                // get answers
                ArrayList<String> answers = new ArrayList<String>();
                int correct = 0;
                for (Iterator a = question.selectNodes("answer").iterator(); a.hasNext(); ) { // iterate through answers
                    Element answer = (Element) a.next();
                    answers.add(answer.getText());

                    // get correct
                    if (answer.attributeValue("correct").equals("true"))
                        correct = answers.size() - 1;
                }

                // create question object
                ret.add(new Question(prompt, answers, correct, difficulty));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * accessor for difficulty
     *
     * @return difficulty [1,10]
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * accessor for prompt
     *
     * @return prompt
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * accessor for answers
     *
     * @return four answers including the correct one in random order
     */
    public ArrayList<String> getAnswers() {
        return answers;
    }

    /**
     * @return
     */
    public int getCorrect() {
        return correct;
    }

    /**
     * returns true if correct answer is given
     *
     * @param x answer
     * @return if correct
     */
    public boolean isCorrect(int x) {
        return x == correct;
    }
}
