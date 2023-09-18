package com.example.picword;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.picword.Adapter.GridView;
import com.example.picword.Adapter.GridViewSuggestAdapter;
import com.example.picword.Adapter.GridViewSuggestAdapterSet2;
import com.example.picword.Adapter.GridViewSuggestAdapterSet4;
import com.example.picword.Adapter.GridViewSuggestAdapterSet5;
import com.example.picword.Common.Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Set5Activity extends AppCompatActivity {

    public List<String> suggestSource = new ArrayList<>();
    public GridView answerAdapter;
    public GridViewSuggestAdapterSet5 suggestAdapter;

    public TextView puzzleNumber;
    public TextView textViewCountDown;

    public Button btnSubmit;
    public android.widget.GridView gridViewAnswer;
    public android.widget.GridView gridViewSuggest;

    public ImageView imgViewQuestion;
    public ImageView imgViewQuestion2;
    public int fill;

    private static final long COUNTDOWN_IN_MILLIS = 180000;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    int[] image_list = {
            R.drawable.teapot,
            R.drawable.stopwatch,
            R.drawable.cockpit,
            R.drawable.peanuts,
            R.drawable.laptop,
            R.drawable.milkshake,
            R.drawable.people,
            R.drawable.rainbow,
            R.drawable.sandwich,
            R.drawable.spyglass
    };

    int[] image_list2 = {
            R.drawable.pot,
            R.drawable.watch,
            R.drawable.pit,
            R.drawable.nut,
            R.drawable.top,
            R.drawable.hshake,
            R.drawable.pole,
            R.drawable.bow,
            R.drawable.wich,
            R.drawable.glass
    };

    int totalQuestions = image_list.length;
    int questionNumber = 1;
    int score = 0;
    int cnt = 0;

    public char[] answer;

    String correct_answer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set5);

        textViewCountDown = findViewById(R.id.text_view_countdown);

        initView();
        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        startCountDown();
    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMillis = l;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                finishQuiz();
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int)(timeLeftInMillis / 1000) / 60;
        int seconds = (int)(timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewCountDown.setText(timeFormatted);

        if(timeLeftInMillis < 30000) {
            textViewCountDown.setTextColor(Color.RED);
        }
    }

    private void initView() {
        gridViewAnswer = (android.widget.GridView) findViewById(R.id.gridViewAnswer);
        gridViewSuggest = (android.widget.GridView) findViewById(R.id.gridViewSuggest);

        puzzleNumber = (TextView) findViewById(R.id.puzzleNumber);
        puzzleNumber.setText("Puzzle Number : " + questionNumber);

        imgViewQuestion = (ImageView) findViewById(R.id.imgLogo);
        imgViewQuestion2 = (ImageView) findViewById(R.id.imgLogo2);

        //add setuplist here
        setupList();

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = "";
                for (int i=0; i<Common.user_submit_answer.length; i++) {
                    result += String.valueOf(Common.user_submit_answer[i]);
                }

                questionNumber++;

                if(result.equals(correct_answer)) {
                    score++;
                    Toast.makeText(getApplicationContext(), "Finish ! This is " + result, Toast.LENGTH_SHORT).show();

                    //reset
                    Common.count = 0;
                    Common.user_submit_answer = new char[correct_answer.length()];

                    //set adapter
                    GridView answerAdapter = new GridView(setupNullList(), getApplicationContext());
                    gridViewAnswer.setAdapter(answerAdapter);
                    answerAdapter.notifyDataSetChanged();

                    GridViewSuggestAdapterSet5 suggestAdapter = new GridViewSuggestAdapterSet5(suggestSource, getApplicationContext(), Set5Activity.this);
                    gridViewSuggest.setAdapter(suggestAdapter);
                    suggestAdapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(Set5Activity.this, "Incorrect !!", Toast.LENGTH_SHORT).show();
                }
                setupList();
                if(questionNumber > totalQuestions) {
                    finishQuiz();
                }
            }
        });
    }

    private void setupList() {
        fill = 0;
        puzzleNumber.setText("Puzzle Number : " + questionNumber);
        //random logo

        Random random = new Random();
        int imageSelected = image_list[cnt];
        int imageSelected2 = image_list2[cnt];

        if(cnt < image_list.length-1) {
            cnt++;
        }

        imgViewQuestion.setImageResource(imageSelected);
        imgViewQuestion2.setImageResource(imageSelected2);

        correct_answer = getResources().getResourceName(imageSelected);
        correct_answer = correct_answer.substring(correct_answer.lastIndexOf("/")+1);

        answer = correct_answer.toCharArray();

        Common.user_submit_answer = new char[answer.length];

        //add answer character to list
        suggestSource.clear();
        for (char item: answer) {
            //Add logo name to list
            suggestSource.add(String.valueOf(item));
        }

        //random add some character to list
        for(int i=answer.length; i<answer.length*2; i++) {
            suggestSource.add(Common.alphaber_character[random.nextInt(Common.alphaber_character.length)]);
        }

        //sort random
        Collections.shuffle(suggestSource);

        //set for gridView
        answerAdapter = new GridView(setupNullList(), this);
        suggestAdapter = new GridViewSuggestAdapterSet5(suggestSource, this, this);

        answerAdapter.notifyDataSetChanged();
        suggestAdapter.notifyDataSetChanged();

        gridViewSuggest.setAdapter(suggestAdapter);
        gridViewAnswer.setAdapter(answerAdapter);
    }

    private char[] setupNullList() {
        char result[] = new char[answer.length];
        for(int i=0; i<answer.length; i++) {
            result[i] = ' ';
        }
        return result;
    }

    private void finishQuiz() {
        new AlertDialog.Builder(this).setMessage("Score is " + score + " / " + totalQuestions)
                .setPositiveButton("RESTART", (DialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();
    }

    private void restartQuiz() {
        cnt = 0;
        score = 0;
        questionNumber = 1;
        setupList();
        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        startCountDown();
    }
}