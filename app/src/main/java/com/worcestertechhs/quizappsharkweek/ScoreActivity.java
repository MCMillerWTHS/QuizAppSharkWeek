package com.worcestertechhs.quizappsharkweek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {
    TextView scoreTV,hScore;
    String name, scoreList;
    Button emailBTN, highScoreBTN,retreiveScoreBTN;
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.worcestertechhs.quizappsharkweek";
    private final String COLOR_KEY = "COLOR";
    int score, num;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("High Score");
    HighScore hS1;

    public static final String TAG = "BHQuiz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreTV = (TextView) findViewById(R.id.scoreTV);
        emailBTN = (Button) findViewById(R.id.emailBTN);
        retreiveScoreBTN = (Button) findViewById(R.id.retreiveScoreBTN);
        highScoreBTN = (Button) findViewById(R.id.highScoreBTN);

        Intent incomingIntent = getIntent();
        score = incomingIntent.getIntExtra("score", 0);

        scoreTV.setText("" + score);
        //initialize the shared preferences
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        //Read initial value
        int initialColor = mPreferences.getInt(COLOR_KEY, 0);
        //grab the main activity's id
        View layout = findViewById(R.id.mainViewScore);

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

        emailBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = getString(R.string.subjectText);
                String body = getString(R.string.bodyPart1) + score + getString(R.string.bodyPart2);

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // Only email apps handle this.
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, body);
                //if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
                //}
            }
        });
        highScoreBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hS1 = new HighScore(score, name);
                String key = myRef.push().getKey();
                myRef.child(key).setValue(hS1);
            }
        });

        retreiveScoreBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Read from the database
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        for (DataSnapshot hsSnapShot : dataSnapshot.getChildren()) {

                            if (num < 2) {
                                HighScore newHS = hsSnapShot.getValue(HighScore.class);
                                Log.d(TAG, "Value is: " + newHS);

                                num++;
                                scoreList += newHS.getpName() + "\t" + newHS.getpScore() + "\n";
                            }
                        }
                        hScore.setText(scoreList);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
            }
        });
    }
}