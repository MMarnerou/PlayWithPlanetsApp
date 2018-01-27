package cy.ac.uclancyprus.thesisapp;

import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

/**
 * Created by maria_m on 11/28/2017.
 */

public class Question {

    Vector<Integer> indices = new Vector<>();
    private String question;
    private String[] answers;
    private int correct;

    public Question(String question, String[] answers, int correct) {
        this.question = question;
        this.answers = answers;
        this.correct = correct;

        for (int i = 0; i < answers.length; i++) indices.add(i);
    }

    public static void main(String[] args) {
        Question question = new Question("20 * 2", new String[]{"10", "20", "30", "40"}, 3);
        System.out.println("question: " + question.getQuestion());
        System.out.println("answers: ");
        for (int i = 0; i < question.getNumOfAnswers(); i++) {
            System.out.println(i + " -> " + question.getAnswer(i));
        }
        System.out.println("Correct: " + question.getCorrect());

        System.out.println("Shuffle!");
        question.shuffleAnswers();

        System.out.println("question: " + question.getQuestion());
        System.out.println("answers: ");
        for (int i = 0; i < question.getNumOfAnswers(); i++) {
            System.out.println(i + " -> " + question.getAnswer(i));
        }
        System.out.println("Correct: " + question.getCorrect());
    }

    public String getQuestion() {
        return question;
    }

    public int getNumOfAnswers() {
        return answers.length;
    }

    public String getAnswer(int i) {
        return answers[indices.get(i)];
    }

    public int getCorrect() {
        return indices.get(correct);
    }

    public void shuffleAnswers() {
        Collections.shuffle(indices);
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