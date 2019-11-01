package com.example.braintrainer2;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    TextView sumTextView;
    TextView showAns;
    TextView timerTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    TextView scoreTextView;
    int locationOfCorrectAns;
    int score=0;
    int noOfQues=0;
    boolean gameState = true;

    public void reset(View view){
        finish();
    }

    public void checkAns(View view) {
        showAns.setVisibility(View.VISIBLE);
        if (gameState) {
            if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAns + 1))) {
                showAns.setText("CORRECT!!!");
                score++;
            } else {
                showAns.setText("WRONG!!!");
            }
            noOfQues++;
            scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(noOfQues));
            newQuestion();
        }
    }
    public void newQuestion() {
        Random rand = new Random();
        int a = rand.nextInt(51);
        int b = rand.nextInt(51);
        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        answers.clear();
        locationOfCorrectAns = rand.nextInt(4);

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAns) {
                answers.add(a + b);
            } else {
                int wrongAnswer = rand.nextInt(101);
                while (wrongAnswer == a + b) {
                    wrongAnswer = rand.nextInt(101);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }
    public void play(){
        score = 0;
        noOfQues = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(noOfQues));
        gameState = true;
        showAns.setVisibility(View.INVISIBLE);

        newQuestion();

        new CountDownTimer(30100,1000){
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                showAns.setVisibility(View.VISIBLE);
                showAns.setText("Time Up!!!");
                playAgainButton.setVisibility(View.VISIBLE);
                gameState = false;
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        sumTextView = findViewById(R.id.sumTextView);
        timerTextView = findViewById(R.id.timerTextView);
        button0 = findViewById(R.id.button1);
        button1 = findViewById(R.id.button2);
        button2 = findViewById(R.id.button3);
        button3 = findViewById(R.id.button4);
        showAns = findViewById(R.id.showAns);
        scoreTextView = findViewById(R.id.scoreTextView);
        playAgainButton = findViewById(R.id.playAgainButton);

        playAgainButton.setVisibility(View.INVISIBLE);

        play();
    }
}

