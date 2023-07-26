package com.worcestertechhs.quizappsharkweek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    TextView questionTV;
    ImageView pictureIV;
    Button falseBTN, trueBTN, nextBTN;
    Question q1, q2, q3, q4, q5, currentQ;
    Question[] questions;
    Drawable resource;
    int currentIndex;
    int imageResource;
    String message;
    String uri;
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTV = (TextView) findViewById(R.id.questionTV);
        pictureIV = (ImageView) findViewById(R.id.pictureIV);
        falseBTN = (Button) findViewById(R.id.falseBTN);
        trueBTN = (Button) findViewById(R.id.trueBTN);
        nextBTN = (Button) findViewById(R.id.nextBTN);
        score = 0;
        q1 = new Question(getString(R.string.q1Text), false, "q1");
        q2 = new Question(getString(R.string.q2Text),false,"q2");
        q3 = new Question(getString(R.string.q3Text), true,"q3");
        q4 = new Question(getString(R.string.q4Text), false, "q4");
        q5 = new Question(getString(R.string.q5Text), true, "q5");
        currentQ = q1;
        currentIndex = 0;
        questions = new Question[] {q1, q2, q3, q4, q5};
        message = "";

        falseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(false == currentQ.getCorrectAnswer())
                {
                    score++;
                    message = getString(R.string.correctMessage);
                }
                else
                {
                     message = getString(R.string.incorrectMessage);
                }

                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getApplicationContext(), message, duration);
                toast.show();
            }
        });
        trueBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(true == currentQ.getCorrectAnswer())
                {
                    score++;
                    message = getString(R.string.correctMessage);
                }
                else
                {
                    message = getString(R.string.incorrectMessage);
                }

                int duration = Toast.LENGTH_SHORT;

                //You can use getApplicationContext() call as a parameter.
                Toast toast = Toast.makeText(getApplicationContext(), message, duration);
                toast.show();
            }
        });
        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex++;
                if(currentIndex < 5)
                {
                    currentQ = questions[currentIndex];
                    questionTV.setText(currentQ.getqPrompt());
                    uri = "@drawable/" + currentQ.getPicture();
                    imageResource = getResources().getIdentifier(uri,null,getPackageName());
                    resource = getResources().getDrawable(imageResource, getTheme());
                    pictureIV.setImageDrawable(resource);


                }
                else
                {
                    Intent scoreIntent = new Intent(MainActivity.this, ScoreActivity.class);
                    scoreIntent.putExtra("score", score);
                    startActivity(scoreIntent);
                }

            }
        });
    }
}