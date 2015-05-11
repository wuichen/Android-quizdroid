package quizdroid.icw3.washington.edu.quizdroid;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ichenwu on 2/25/15.
 */
public class TopicsRepo implements TopicRepository {

    private ArrayList<Topic> topicList;
    private Integer currTopic;

    public TopicsRepo() {
        this.topicList = createQuestions(); //Create topics with their questions
    }

    private ArrayList<Topic> createQuestions() {
        Topic math = setMathTopic();
        Topic physics = setPhysicsTopic();
        Topic marvel = setMarvelTopic();

        return new ArrayList<Topic>(Arrays.asList(math, physics, marvel));
    }

    public void setCurrTopic(int currTopic) {
        this.currTopic = currTopic;
    }

    public ArrayList<Topic> getTopics() {
        return topicList;
    }

    private Topic setMathTopic() {
        Topic topic = new Topic();
        Question question = new Question();
        ArrayList<Question> questionList = new ArrayList<>();

        //math creation
        topic.setTitle("Math");
        topic.setLongDesc("Math simple but hard subject");
        topic.setShortDesc("A math quiz");
        //Q1
        question.setQuestion("What's 1+1");
        question.setOptions(new ArrayList<>(Arrays.asList("1", "2", "3", "4")));
        question.setCorrectOption(1);
        questionList.add(question);
        question = new Question();
        //Q2
        question.setQuestion("What's 2*2");
        question.setOptions(new ArrayList<>(Arrays.asList("3", "1", "4", "5")));
        question.setCorrectOption(2);
        questionList.add(question);
        question = new Question();
        //Q3
        question.setQuestion("What's 3/3");
        question.setOptions(new ArrayList<>(Arrays.asList("1", "2", "5", "6")));
        question.setCorrectOption(0);
        questionList.add(question);
        question = new Question();
        //Q4
        topic.setQuestions(questionList);
        question.setQuestion("4 / 2 =");
        question.setOptions(new ArrayList<>(Arrays.asList(".666", ".792", "91", "2")));
        question.setCorrectOption(3);
        questionList.add(question);
        question = new Question();

        return topic;
    }

    private Topic setPhysicsTopic() {
        Topic topic = new Topic();
        Question question = new Question();
        ArrayList<Question> questionList = new ArrayList<>();

        //Physics Creation
        topic.setTitle("Physics");
        topic.setLongDesc("Physics - acceleration, gravity and stuff");
        topic.setShortDesc("A physics quiz");
        //Q1
        question.setQuestion("Radiocarbon is produced in the atmosphere as a result of");
        question.setOptions(new ArrayList<>(Arrays.asList("collision between fast neutrons and nitrogen nuclei present in the atmosphere", "action of ultraviolet light from the sun on atmospheric oxygen", "action of solar radiations particularly cosmic rays on carbon dioxide present in the atmosphere", "lightning discharge in atmosphere")));
        question.setCorrectOption(0);
        questionList.add(question);
        question = new Question();
        //Q2
        question.setQuestion("It is easier to roll a stone up a sloping road than to lift it vertical upwards because");
        question.setOptions(new ArrayList<>(Arrays.asList("work done in rolling is more than in lifting", "work done in lifting the stone is equal to rolling it", "work done in both is same but the rate of doing work is less in rolling", "work done in rolling a stone is less than in lifting it")));
        question.setCorrectOption(1);
        questionList.add(question);
        question = new Question();
        //Q3
        question.setQuestion("gravity?");
        question.setOptions(new ArrayList<>(Arrays.asList("9.8", "1", "12", "5")));
        question.setCorrectOption(0);
        questionList.add(question);
        question = new Question();
        //Q4
        topic.setQuestions(questionList);
        question.setQuestion("Force =");
        question.setOptions(new ArrayList<>(Arrays.asList("ma", "dk", "id", "ac")));
        question.setCorrectOption(0);
        questionList.add(question);

        return topic;
    }

    private Topic setMarvelTopic() {
        Topic topic = new Topic();
        Question question = new Question();
        ArrayList<Question> questionList = new ArrayList<>();

        //Marvel Creation
        topic.setTitle("Marvel Super Heroes");
        topic.setLongDesc("Marvel Comics spiderman fantastic 4 not batman");
        topic.setShortDesc("A marvel quiz");
        //Q1
        question.setQuestion("whos spider man");
        question.setOptions(new ArrayList<>(Arrays.asList("peter parker", "ted", "amy", "ron")));
        question.setCorrectOption(0);
        questionList.add(question);
        question = new Question();
        //Q2
        question.setQuestion("whos batman");
        question.setOptions(new ArrayList<>(Arrays.asList("george", "bruce wayne", "jack", "lydia")));
        question.setCorrectOption(1);
        questionList.add(question);
        question = new Question();
        //Q3
        question.setQuestion("how many members in fantastic 4");
        question.setOptions(new ArrayList<>(Arrays.asList("1", "2", "5", "4")));
        question.setCorrectOption(3);
        questionList.add(question);
        question = new Question();
        //Q4
        topic.setQuestions(questionList);
        question.setQuestion("whos peter parkers college girlfriend");
        question.setOptions(new ArrayList<>(Arrays.asList("Jason", "steve jobs", "lisa", "mary jane")));
        question.setCorrectOption(3);
        questionList.add(question);

        return topic;
    }

    /*BEGIN TOPICREPOSITORY INTERFACE*/
    public String getTitle() {
        return topicList.get(currTopic).getTitle();
    }

    public void setTitle(String title) {
        topicList.get(currTopic).setTitle(title);
    }

    public String getShortDesc() {
        return topicList.get(currTopic).getShortDesc();
    }

    public void setShortDesc(String shortDesc) {
        topicList.get(currTopic).setShortDesc(shortDesc);
    }

    public String getLongDesc() {
        return topicList.get(currTopic).getLongDesc();
    }

    public void setLongDesc(String longDesc) {
        topicList.get(currTopic).setLongDesc(longDesc);
    }

    public ArrayList<Question> getQuestions() {
        return topicList.get(currTopic).getQuestions();
    }

    public void setQuestions(ArrayList<Question> questions) {
        topicList.get(currTopic).setQuestions(questions);
    }

    public Question getCurrQuestion() {
        return topicList.get(currTopic).getCurrQuestion();
    }

    public int getCurrQuestionNum() {
        return topicList.get(currTopic).getCurrQuestionNum();
    }

    public void incrementCurrentQuestion() {
        topicList.get(currTopic).incrementCurrentQuestion();
    }

    public int getTotalCorrect() {
        return topicList.get(currTopic).getTotalCorrect();
    }

    public void incrementTotalCorrect() {
        topicList.get(currTopic).incrementTotalCorrect();
    }
    /*END TOPICREPOSITORY INTERFACE*/


}

