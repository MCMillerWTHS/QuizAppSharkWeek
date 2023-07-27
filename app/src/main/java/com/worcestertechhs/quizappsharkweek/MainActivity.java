package com.worcestertechhs.quizappsharkweek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.worcestertechhs.quizappsharkweek";
    private final String COLOR_KEY = "COLOR";
    TextView questionTV;
    ImageView pictureIV;
    Button falseBTN, trueBTN, nextBTN, redBTN, greenBTN, blueBTN;
    Question q1, q2, q3, q4, q5, currentQ;
    Question[] questions;
    Drawable resource;
    int currentIndex, score, imageResource;
    String message, uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTV = (TextView) findViewById(R.id.questionTV);
        pictureIV = (ImageView) findViewById(R.id.pictureIV);
        falseBTN = (Button) findViewById(R.id.falseBTN);
        trueBTN = (Button) findViewById(R.id.trueBTN);
        nextBTN = (Button) findViewById(R.id.nextBTN);
        redBTN = (Button) findViewById(R.id.redBTN);
        blueBTN = (Button) findViewById(R.id.blueBTN);
        greenBTN = (Button) findViewById(R.id.greenBTN);
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

        //initialize the shared preferences
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        //Read initial value
        int initialColor = mPreferences.getInt(COLOR_KEY,0 );
        //grab the main activity's id
        View layout = findViewById(R.id.mainView);
        
        if(initialColor == R.id.redBTN)
        {
            layout.setBackgroundColor(Color.parseColor("red"));
            Log.d(null,"Red");
        }
        else if(initialColor == R.id.blueBTN){
            layout.setBackgroundColor(Color.parseColor("blue"));
        }
        else if(initialColor == R.id.greenBTN)
        {
            layout.setBackgroundColor(Color.parseColor("green"));
        }
        else
        {
            layout.setBackgroundColor(Color.parseColor("gray"));
        }
        /*
        switch (initialColor) {
            case R.id.redBTN:
                layout.setBackgroundColor(Color.parseColor("red"));
                Log.d(null,"Red");
                break;
            case R.id.blueBTN:
                layout.setBackgroundColor(Color.parseColor("blue"));
                break;
            case R.id.greenBTN:
                layout.setBackgroundColor(Color.parseColor("green"));
                break;
        }*/


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
    /** Called when the user taps the Send button */
    public void selectColor(View view) {
        // Do something in response to button
        Log.d(null, "Button pressed");

        //grab the main activity's id
        View layout = findViewById(R.id.mainView);

        //Created SharedPreferences editor object
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();

        //Write the id of the selected button to our SharedPreferences file
        //this is an int Key/Value pair where the:
        //key = COLOR_KEY = "COLOR"
        //value = view.getID() = "red_button", "blue_button", etc.
        preferencesEditor.putInt(COLOR_KEY, view.getId());

        //Commit the value and save the file.
        preferencesEditor.apply();

        if(view.getId() == R.id.redBTN)
        {
            layout.setBackgroundColor(Color.parseColor("red"));
            Log.d(null,"Red");
        }
        else if(view.getId() == R.id.blueBTN){
            layout.setBackgroundColor(Color.parseColor("blue"));
        }
        else if(view.getId() == R.id.greenBTN)
        {
            layout.setBackgroundColor(Color.parseColor("green"));
        }
        else
        {
            layout.setBackgroundColor(Color.parseColor("gray"));
        }
        /*
        //Switch based on  which button was pressed
        switch (view.getId()) {
            case R.id.redBTN:
                //Set background color of main activity
                layout.setBackgroundColor(Color.parseColor("red"));
                Log.d(null,"Red");
                break;
            case R.id.greenBTN:
                layout.setBackgroundColor(Color.parseColor("green"));
                break;
            case R.id.blueBTN:
                layout.setBackgroundColor(Color.parseColor("blue"));
                break;
        }*/
    }
}