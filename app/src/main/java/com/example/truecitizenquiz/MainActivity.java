package com.example.truecitizenquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button trueButton, falseButton;
    private ImageButton nextButton, backButton;
    private TextView questionTextView;

    private int currentQuestionIndex = 0;

    private Question[] questionBank = new Question[] {
            new Question(R.string.independence_question, false),
            new Question(R.string.aliens_question, false),
            new Question(R.string.american_citizen_question, true),
            new Question(R.string.born_citizen_question, true),
            new Question(R.string.foreign_citizen_question, true),
            new Question(R.string.illegal_alien_question, true),
            new Question(R.string.illegal_immigrant_question, false),
            new Question(R.string.immigration_question, false),
            new Question(R.string.puerto_rico_question, true),
            new Question(R.string.region_question, false),
            new Question(R.string.traditions_question, true) //array length 11
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        questionTextView = findViewById(R.id.question_tv);
        nextButton = findViewById(R.id.next_button);
        backButton = findViewById(R.id.back_button);


        /* one way to call an onClickListener to a button
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
         */

        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        backButton.setOnClickListener(this);

        //Question question = new Question(R.string.independence_question, true);

    }

    //can add multiple on clicks within this listener rather than creating two (like shown above)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.true_button:
                checkAnswer(true);
                break;
            case R.id.false_button:
                checkAnswer(false);
                break;
            case R.id.next_button:
                //go to the next question
                //goes through the index of the questions array to go through each question
                //the array will reset once it gets to the end of the array
                currentQuestionIndex = (currentQuestionIndex + 1) % questionBank.length;
                updateQuestion();
                break;
            case R.id.back_button:
                if (currentQuestionIndex > 0) {
                    backButton.setVisibility(View.VISIBLE);
                    currentQuestionIndex = (currentQuestionIndex - 1) % questionBank.length;
                    Toast.makeText(MainActivity.this, "Next", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
                if (currentQuestionIndex <= 0) {
                    backButton.setVisibility(View.GONE);
                }
        }
    }

    private void updateQuestion() {
        Log.d("Current", "onClick Question Index: " + currentQuestionIndex);
            backButton.setVisibility(View.VISIBLE);
            questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResID());
            Toast.makeText(MainActivity.this, "Next", Toast.LENGTH_SHORT).show();

    }

    private void checkAnswer(boolean userChooseCorrect) {
       //the method answerTrue is in Java and allows us to retrieve it for a boolean answer
        boolean answerIsTrue = questionBank[currentQuestionIndex].isAnswerTrue();
        int toastMessageId = 0;

        if (userChooseCorrect == answerIsTrue) {
            toastMessageId = R.string.correct;
        } else {
            toastMessageId = R.string.incorrect;
        }

        Toast.makeText(MainActivity.this, toastMessageId, Toast.LENGTH_SHORT).show();
    }
}