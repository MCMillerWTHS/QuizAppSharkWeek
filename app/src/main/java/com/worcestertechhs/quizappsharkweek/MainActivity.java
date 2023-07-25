package com.worcestertechhs.quizappsharkweek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    TextView questionTV;
    Button falseBTN, trueBTN, nextBTN;
    Question q1, q2, q3, q4, q5, currentQ;
    Question[] questions;
    int currentIndex;
    String message;
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTV = (TextView) findViewById(R.id.questionTV);
        falseBTN = (Button) findViewById(R.id.falseBTN);
        trueBTN = (Button) findViewById(R.id.trueBTN);
        nextBTN = (Button) findViewById(R.id.nextBTN);
        score = 0;
        q1 = new Question("Sharks can only be found in salt water?", false);
        q2 = new Question("Sharks must eat continually in order to survive",false);
        q3 = new Question("More shark attacks occur during the day than at night", true);
        q4 = new Question("Sharks can have up to nine gills on each side of their body", false);
        q5 = new Question("The smallest species of shark in the world is the Dwarf Lanternfish", true);
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
                    String message = "You are Correct!";
                }
                else
                {
                    String message = "You are NOT correct!";
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
                    String message = "You are Correct!";
                }
                else
                {
                    String message = "You are NOT correct!";
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