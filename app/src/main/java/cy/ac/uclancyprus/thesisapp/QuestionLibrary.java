package cy.ac.uclancyprus.thesisapp;

/**
 * This is a Library which holds all the questions and choices and right answers for the QuizActivity
 * Created by maria_m on 11/11/2017.
 */

public class QuestionLibrary {

    //Create Questions Array
    private String questions[] = {
            "How long does it take for light from the Sun to reach Earth",
            "It takes the Sun 225-250 million years to do one revolution of the Milky Way Galaxy. How fast does the Sun travel?"
    };
    //End of Questions array

    //Create answers Array
    private String choices[][] = {{"8", "9", "10", "11"},
            {"220km in a second", "220km in an minute", "220 km in a hour", "220km in a year"}
    };
    //End of Answers array

    //Create the array with the correct answers
    private String correctAnswers[] = {"8", "220km in a year"};
    //End of Correct answers array

    //Create functions to qet the right questions, choices and answers
    public String getQuestion(int a) {
        String question = questions[a];
        return question;
    }

    public String getChoice1(int a) {
        String choice1 = choices[a][0];
        return choice1;
    }

    public String getChoice2(int a) {
        String choice2 = choices[a][1];
        return choice2;
    }

    public String getChoice3(int a) {
        String choice3 = choices[a][2];
        return choice3;
    }

    public String getChoice4(int a) {
        String choice4 = choices[a][3];
        return choice4;
    }

    public String getCorrectAnswer(int a) {
        String answer = correctAnswers[a];
        return answer;
    }
    //End of functions to qet the right questions, choices and answers
}
