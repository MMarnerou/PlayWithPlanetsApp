package cy.ac.uclancyprus.thesisapp.model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Nearchos
 *         Created: 12-Dec-17
 */

public class Question implements Serializable {

    private String question;
    private String [] answers;
    private int correct;

    public Question() { super(); }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getNumOfAnswers() {
        return answers.length;
    }

    public String getAnswer(final int i) {
        return answers[i];
    }

    public int getCorrect() {
        return correct;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", answers=" + Arrays.toString(answers) +
                ", correct=" + correct +
                '}';
    }
}