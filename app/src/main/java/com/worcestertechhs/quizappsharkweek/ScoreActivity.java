package com.worcestertechhs.quizappsharkweek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ScoreActivity extends AppCompatActivity {
    TextView scoreTV;
    Button emailBTN;

    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreTV = (TextView) findViewById(R.id.scoreTV);
        emailBTN = (Button) findViewById(R.id.emailBTN);

        Intent incomingIntent = getIntent();
        score = incomingIntent.getIntExtra("score", 0);

        scoreTV.setText("" + score);

        emailBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = getString(R.string.subjectText);
                String body = getString(R.string.bodyPart1)+ score + getString(R.string.bodyPart2);

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // Only email apps handle this.
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, body);
                    //if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
                    //}

            }
        });
    }


}