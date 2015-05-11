package quizdroid.icw3.washington.edu.quizdroid;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ichenwu on 2/3/15.
 */
public class Question implements Serializable {
    private String question;
    private ArrayList<String> options;
    private int correctOption;


    public Question() {

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(int correctOption) {
        this.correctOption = correctOption;
    }
}
