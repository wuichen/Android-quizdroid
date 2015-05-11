package quizdroid.icw3.washington.edu.quizdroid;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;


public class fragmentActivity extends ActionBarActivity {

    private final String TAG = "TopicQuestionFragments";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        if (savedInstanceState == null) {

            getFragmentManager().beginTransaction()
                    .add(R.id.container, new TopicOverviewFragment())
                    .commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        QuizApp.getInstance().cancel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fragment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.action_settings:
                inflateSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item); //default returns false
        }
    }

    //Inflates the SettingsActivity Activity
    private void inflateSettings() {
        // Display the fragment as the main content.
        Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivityForResult(i, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        QuizApp.getInstance().setChanged(true);
    }

    /**
     * A fragment for the Topic Overview.
     */
    public static class TopicOverviewFragment extends Fragment {

        public TopicOverviewFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.topic_overview_fragment, container, false);

            //Set the textviews with the topic and description
            TextView description = (TextView) rootView.findViewById(R.id.description);
            description.setText(QuizApp.getInstance().getRepository().getLongDesc());

            TextView topic = (TextView) rootView.findViewById(R.id.topic);
            topic.setText(QuizApp.getInstance().getRepository().getTitle());

            TextView numQ = (TextView) rootView.findViewById(R.id.numQ);
            numQ.setText(String.valueOf(QuizApp.getInstance().getRepository().getQuestions().size()) + " Questions");

            // On button click Open Question activity
            Button bStartQuestions = (Button) rootView.findViewById(R.id.begin);
            bStartQuestions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new QuestionFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, fragment)
                            .commit();
//                    android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    QuestionFragment question = new QuestionFragment();
//
//                    fragmentTransaction.replace(R.id.container, question);
//
//                    fragmentTransaction.commit();
                }
            });
            return rootView;
        }

        @Override
        public void onPause() {
            super.onPause();
        }

    }

    /**
     * A fragment for the Question.
     */
    public static class QuestionFragment extends Fragment {

        View rootView;
        private int chosenValue;
        private Question currQuestion;

        public QuestionFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.question_fragment, container, false);
//            getActivity().setTitle(R.string.title_activity_question); //Set the title of the activity

            chosenValue = -1; //Initializes

            currQuestion = QuizApp.getInstance().getRepository().getCurrQuestion();//topic.getQuestions().get(topic.getCurrQuestion()); //Current question object

            //Sets question text
            TextView questionText = (TextView) rootView.findViewById(R.id.question); //Text for question
            questionText.setText(currQuestion.getQuestion()); //Sets question text

            //Sets Text and listeners for buttons
            setButtonText(currQuestion);

            Button bSubmit = (Button) rootView.findViewById(R.id.submit);
            bSubmit.setEnabled(false); //Sets submit enabled to false as default

            // On button click Open Question Summary activity
            bSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (chosenValue != -1) { //an answer has been chosen
                        if (chosenValue == currQuestion.getCorrectOption()) {
                            QuizApp.getInstance().getRepository().incrementTotalCorrect();
                        }
                        Fragment fragment = new QuestionSummaryFragment();
                        FragmentManager fragmentManager = getFragmentManager();

                        Bundle questionBundle = new Bundle();
                        //questionBundle.putSerializable("topic", toSend);
                        questionBundle.putInt("userAnswer", chosenValue);
                        //Log.i("hello", "Sent: " + questionBundle.getInt("userAnswer"));

                        fragment.setArguments(questionBundle);

                        fragmentManager.beginTransaction()
                                .replace(R.id.container, fragment)
                                .commit();
//                        android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//                        QuestionSummaryFragment questionSummary = new QuestionSummaryFragment();
//                        Bundle questionBundle = new Bundle();
//                        //questionBundle.putSerializable("topic", toSend);
//                        questionBundle.putInt("userAnswer", chosenValue);
//                        //Log.i("hello", "Sent: " + questionBundle.getInt("userAnswer"));
//
//                        questionSummary.setArguments(questionBundle);
//
//                        fragmentTransaction.replace(R.id.container, questionSummary);
//                        fragmentTransaction.commit();
                    }
                }
            });

            return rootView;
        }

        @Override
        public void onPause() {
            super.onPause();
        }

        public void onRadioButtonClicked(View v) {
            chosenValue = Integer.parseInt(v.getTag().toString());
            ((Button) rootView.findViewById(R.id.submit)).setEnabled(true); //should enable submit
//            ((Button) rootView.findViewById(R.id.submit)).setVisibility(View.VISIBLE); //should enable submit

        }

        public void setButtonText(Question currQuestion) {
            Button b1 = (RadioButton) rootView.findViewById(R.id.answer0);
            b1.setText(currQuestion.getOptions().get(0));
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRadioButtonClicked(v);
                }
            });

            Button b2 = (RadioButton) rootView.findViewById(R.id.answer1);
            b2.setText(currQuestion.getOptions().get(1));
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRadioButtonClicked(v);
                }
            });

            Button b3 = (RadioButton) rootView.findViewById(R.id.answer2);
            b3.setText(currQuestion.getOptions().get(2));
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRadioButtonClicked(v);
                }
            });

            Button b4 = (RadioButton) rootView.findViewById(R.id.answer3);
            b4.setText(currQuestion.getOptions().get(3));
            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRadioButtonClicked(v);
                }
            });
        }

    }

    /**
     * A fragment for the Question Summary.
     */
    public static class QuestionSummaryFragment extends Fragment {

        View rootView;
        private boolean noMoreQuestions;

        public QuestionSummaryFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.question_results_fragment, container, false);
//            getActivity().setTitle(R.string.title_activity_question_summary);



            noMoreQuestions = false; //intitalize noMoreQuestions
            int userAnswerInt = getArguments().getInt("userAnswer"); //Intents user answer

            QuizApp app = QuizApp.getInstance();
            ArrayList<Question> topicQuestionList = app.getRepository().getQuestions();

            Question currQuestion = app.getRepository().getCurrQuestion();
            ArrayList<String> currQuestionOptions = currQuestion.getOptions();

            String userAnswerTxt = currQuestionOptions.get(userAnswerInt);
            String correctAnswerTxt = currQuestionOptions.get(currQuestion.getCorrectOption());

            TextView userAnswer = (TextView) rootView.findViewById(R.id.selectedAnswer);
            userAnswer.setText("You chose: " + userAnswerTxt);
            TextView correctAnswer = (TextView) rootView.findViewById(R.id.correctAnswer);
            correctAnswer.setText("Correct answer: " + correctAnswerTxt);

            TextView userTotal = (TextView) rootView.findViewById(R.id.correctNum);
            userTotal.setText("You have " + app.getRepository().getTotalCorrect() +
                    " out of " + (app.getRepository().getCurrQuestionNum() + 1) + " correct.");//, topic.getCurrQuestion()));

            //final Topic toSend = topic;
            Button bNextQuestion = (Button) rootView.findViewById(R.id.next);
            if (topicQuestionList.size() - 1 == app.getRepository().getCurrQuestionNum()) {
                bNextQuestion.setText("Return to Home");
                noMoreQuestions = true;
            }

            bNextQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!noMoreQuestions) {
                        QuizApp.getInstance().getRepository().incrementCurrentQuestion();
                        Fragment fragment = new QuestionFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, fragment)
                                .commit();
//                        android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//                        QuestionFragment question = new QuestionFragment();
//                        fragmentTransaction.replace(R.id.container, question);
//
//                        fragmentTransaction.commit();
                    } else {
                        Intent nextActivity = new Intent(getActivity(), TopicListActivity.class);
                        startActivity(nextActivity);
                        getActivity().finish(); // kill this instance self (this activity)
                    }
                }
            });

            return rootView;
        }

        @Override
        public void onPause() {
            super.onPause();
        }


    }
}
