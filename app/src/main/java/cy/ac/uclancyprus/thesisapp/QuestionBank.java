package cy.ac.uclancyprus.thesisapp;

/**
 * Created by maria_m on 10/21/2017.
 */

public class QuestionBank {
    //    List<Questions> list = new ArrayList<>();
//    MyDatabaseHelper myDatabaseHelper;
//
//    public int getLength() {
//        return list.size();
//    }
//
//    public String getQuestion(int a) {
//        return list.get(a).getQuestion();
//    }
//
//    public String getChoice(int index, int num) {
//        return list.get(index).getChoice(num - 1);
//    }
//
//    public String getCorrectAnswer(int index, int num) {
//        return list.get(index).getAnswer();
//    }
//
//    public void initQuestions(Context context) {
//        myDatabaseHelper = new MyDatabaseHelper(context);
//        list = myDatabaseHelper.getQuestionsList(); //get questions & answers from database
//
//        if (list.isEmpty()) {
//            myDatabaseHelper.addInitialQuestion(new Questions("1. How many planets are there in our solar system?",
//            new String[]{"Eight", "Nine", "Ten", "Eleven"}, "Eight"));
//            list = myDatabaseHelper.getQuestionsList();
//        }
//    }
    private String mQuestions[] = {"How long does it take for light from the Sun to reach Earth",
            "It takes the Sun 225-250 million years to do one revolution of the Milky Way Galaxy. How fast does the Sun travel?"
    };
    private String mChoices[][] = {{"8", "9", "10", "11"},
            {"220km in a second", "220km in an minute", "220 km in a hour", "220km in a year"}
    };
    private String correctAnswers[] = {"8", "220km in a year"};

    public String getQuestion(int a) {
        String question = mQuestions[a];
        return question;
    }

    public String getChoice1(int a) {
        String choice1 = mChoices[a][0];
        return choice1;
    }

    public String getChoice2(int a) {
        String choice2 = mChoices[a][1];
        return choice2;
    }

    public String getChoice3(int a) {
        String choice3 = mChoices[a][2];
        return choice3;
    }

    public String getChoice4(int a) {
        String choice4 = mChoices[a][3];
        return choice4;
    }

    public String getCorrectAnswer(int a) {
        String answer = correctAnswers[a];
        return answer;
    }
}
