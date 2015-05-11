package quizdroid.icw3.washington.edu.quizdroid;

import java.util.ArrayList;

/**
 * Created by ichenwu on 2/25/15.
 */
public interface TopicRepository {
    public String getTitle();

    public void setTitle(String title);

    public String getShortDesc();

    public void setShortDesc(String shortDesc);

    public String getLongDesc();

    public void setLongDesc(String longDesc);

    public ArrayList<Question> getQuestions();

    public void setQuestions(ArrayList<Question> questions);

    public Question getCurrQuestion();

    public int getCurrQuestionNum();

    public void incrementCurrentQuestion();

    public int getTotalCorrect();

    public void incrementTotalCorrect();
}

