package com.caapp1st.math1st;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    int correct;
    SharedPreferences.Editor editor;
    SharedPreferences prefs;
    //public String CORRECT = "correct";
    TextView correctTextView;
    TextView goodTextView;
    ImageView image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editor = getPreferences(MODE_PRIVATE).edit();
        prefs = getSharedPreferences(getString(R.string.saved_correct), MODE_PRIVATE);
        correct=prefs.getInt(getString(R.string.saved_correct), 0);

        goodTextView = (TextView) findViewById(R.id.goodjob);
        image2 = (ImageView) findViewById(R.id.imageView2);

        if(correct==0){
            goodTextView.setText(R.string.keepTrying);
            image2.setImageResource(R.drawable.bad);
        }

        correctTextView = (TextView) findViewById(R.id.correctText);
        correctTextView.setText(Integer.toString(correct));

        if(correct==20){
            image2.setImageResource(R.drawable.twostars);
        }

        if(correct==30){
            image2.setImageResource(R.drawable.threestars);
        }

        if(correct>=40){
            image2.setImageResource(R.drawable.allstars);
        }

        ImageButton ibutton = (ImageButton)findViewById(R.id.goback);


        ibutton.setOnClickListener(new ImageButton.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                //myIntent.setAction(Intent.ACTION_VIEW);
                //myIntent.setData(android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                //startActivity(myIntent);

                //Intent shareIntent = new Intent(Intent.ACTION_SEND);
                //shareIntent.setType("text/plain");
                //shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Android Development");
                //shareIntent.putExtra(Intent.EXTRA_TEXT, "10 correct answers!");
                //startActivity(shareIntent);
            }
        });
    }
}
