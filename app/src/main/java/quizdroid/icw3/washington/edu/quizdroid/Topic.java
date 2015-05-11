package quizdroid.icw3.washington.edu.quizdroid;

import java.util.ArrayList;

/**
 * Created by ichenwu on 2/25/15.
 */
public class Topic {
    private String title;
    private String shortDesc;
    private String longDesc;
    private ArrayList<Question> questions;
    private int currQuestion;
    private int totalCorrect;

    public Topic() {
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public Question getCurrQuestion() {
        return questions.get(currQuestion);
    }

    public int getCurrQuestionNum() {
        return currQuestion;
    }

    public void incrementCurrentQuestion() {
        this.currQuestion++;
    }

    public int getTotalCorrect() {
        return totalCorrect;
    }

    public void incrementTotalCorrect() {
        this.totalCorrect++;
    }
}
