package com.worcestertechhs.quizappsharkweek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeScreen extends AppCompatActivity {
    TextView greetingTV;
    Button beginBTN, redBTN, greenBTN, blueBTN;
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.worcestertechhs.quizappsharkweek";
    private final String COLOR_KEY = "COLOR";
    int value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        greetingTV = (TextView) findViewById(R.id.greetingTV);
        beginBTN = (Button) findViewById(R.id.beginBTN);
        value = 0;

        redBTN = (Button) findViewById(R.id.redBTN);
        blueBTN = (Button) findViewById(R.id.blueBTN);
        greenBTN = (Button) findViewById(R.id.greenBTN);

        //initialize the shared preferences
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        //Read initial value
        int initialColor = mPreferences.getInt(COLOR_KEY,0);
        //grab the main activity's id
        View layout = findViewById(R.id.welcome);

        if (initialColor == R.id.redBTN) {
            layout.setBackgroundColor(Color.parseColor("red"));
            Log.d(null, "Red");
        } else if (initialColor == R.id.blueBTN) {
            layout.setBackgroundColor(Color.parseColor("blue"));
        } else if (initialColor == R.id.greenBTN) {
            layout.setBackgroundColor(Color.parseColor("green"));
        } else {
            layout.setBackgroundColor(Color.parseColor("gray"));
        }

        beginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scoreIntent = new Intent(WelcomeScreen.this, MainActivity.class);
                scoreIntent.putExtra("greetingMain", value);
                startActivity(scoreIntent);
            }
        });
    }
        public void selectColor(View view) {
            // Do something in response to button
            Log.d(null, "Button pressed");

            //grab the main activity's id
            View layout = findViewById(R.id.welcome);

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
            else if(view.getId() == R.id.blueBTN)
            {
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
    }
}