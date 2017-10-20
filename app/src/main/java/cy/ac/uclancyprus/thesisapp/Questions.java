package cy.ac.uclancyprus.thesisapp;

/**
 * Created by MMarnerou on 10/17/2017.
 */

public class Questions {

    private String question;
    private String[] choices = new String[4];
    private String answer;

    public Questions(){
        this.question = question;
        this.choices[0] = choices [0];
        this.choices[1] = choices [1];
        this.choices[2] = choices [2];
        this.choices[3] = choices [3];
        this.answer = answer;
    }

    public String getQuestion() { return question; }

    public String getChoice(int i) { return choices[i]; }

    public String getAnswer() { return answer; }

    public void setQuestion(String question) { this.question = question; }

    public void setChoices(int i,String choices) { this.choices[i] = choices; }

    public void setAnswer(String answer) { this.answer = answer; }

}
