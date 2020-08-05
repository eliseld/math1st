package com.caapp1st.math1st;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    TextView mainTextView;
    TextView mainTextView2;
    TextView timeTextView;
    TextView correctTextView;
    TextView wrongTextView;
    Button mainButton;
    Button secondButton;
    Button resetButton;
    EditText mainEditText;
    EditText mainEditInt;
    private boolean timerHasStarted = false;
    Switch timed;
    int result;
    int correct;
    int wrong;
    int optype=0;
    int modeType=0;
    int answer = 0;
    SharedPreferences.Editor editor;
    SharedPreferences prefs;
    //public String CORRECT = "correct";
    SharedPreferences.Editor editorw;
    SharedPreferences prefsw;
    //private String WRONG = "wrong";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainTextView = (TextView) findViewById(R.id.main_textview);
        mainTextView.setText(R.string.letsgo);
        mainTextView2 = (TextView) findViewById(R.id.main_textview2);
        timeTextView = (TextView) findViewById(R.id.time_textview);
        correctTextView = (TextView) findViewById(R.id.correct_textview);
        wrongTextView = (TextView) findViewById(R.id.wrong_textview);

        mainButton = (Button) findViewById(R.id.main_button);
        secondButton = (Button) findViewById(R.id.second_button);
        resetButton = (Button) findViewById(R.id.reset_button);
        mainButton.setOnClickListener(this);
        secondButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);

        mainEditText = (EditText) findViewById(R.id.main_edittext);
        mainEditInt = (EditText) findViewById(R.id.main_editint);

        //spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ops_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        timed = (Switch) findViewById(R.id.switchTimed);

        editor = getSharedPreferences(getString(R.string.saved_correct),MODE_PRIVATE).edit();
        prefs = getSharedPreferences(getString(R.string.saved_correct), MODE_PRIVATE);
        correct=prefs.getInt(getString(R.string.saved_correct), 0);
        correctTextView.setText(Integer.toString(correct));

        editorw = getPreferences(MODE_PRIVATE).edit();
        prefsw = getPreferences(MODE_PRIVATE);
        wrong=prefs.getInt(getString(R.string.saved_wrong), 0);
        wrongTextView.setText(Integer.toString(wrong));

    }


    @Override
    public void onClick(final View v) {
        // manages the buttons when clicked
        switch(v.getId()) {
            case R.id.main_button:
                mainTextView2.setText(" ");
                if (optype == 0) {
                    int number1 = (int) (Math.random() * 13 + 0);
                    int number2 = 0;
                    if (modeType == 0) {
                        if (mainEditText.getText().toString().trim().length() == 0) {
                            mainTextView.setText(R.string.entNumAb);
                        } else {
                            number2 = Integer.parseInt(mainEditText.getText().toString());
                            result = number1 + number2;
                            mainTextView.setText(Integer.toString(number1) + "+" + Integer.toString(number2));
                            mainEditInt.getText().clear();
                        }
                    } else {
                        number2 = (int) (Math.random() * 12 + 1);
                        result = number1 + number2;
                        mainTextView.setText(Integer.toString(number1) + "+" + Integer.toString(number2));
                        mainEditInt.getText().clear();
                    }
                } else if (optype == 1) {
                    int number1 = (int) (Math.random() * 12 + 1);
                    int number2 = 0;
                    if (modeType == 0) {
                        if (mainEditText.getText().toString().trim().length() == 0) {
                            mainTextView.setText(R.string.entNumAb);
                        } else {
                            number2 = Integer.parseInt(mainEditText.getText().toString());
                            if (number1<number2){
                                result = number2 - number1;
                                mainTextView.setText(Integer.toString(number2) + "-" + Integer.toString(number1));
                                mainEditInt.getText().clear();
                            }
                            else {
                                result = number1 - number2;
                                mainTextView.setText(Integer.toString(number1) + "-" + Integer.toString(number2));
                                mainEditInt.getText().clear();
                            }
                        }
                    } else {
                        number2 = (int) (Math.random() * 12 + 1);
                        while (number1<number2){
                            number1 = (int) (Math.random() * 12 + 1);
                        }
                        result = number1 - number2;
                        mainTextView.setText(Integer.toString(number1) + "-" + Integer.toString(number2));
                        mainEditInt.getText().clear();
                    }
                } else if (optype == 2) {
                    int number1 = (int) (Math.random() * 12 + 1);
                    int number2 = 0;
                    if (modeType == 0) {
                        if (mainEditText.getText().toString().trim().length() == 0) {
                            mainTextView.setText(R.string.entNumAb);
                        } else {
                            number2 = Integer.parseInt(mainEditText.getText().toString());
                            result = number1 * number2;
                            mainTextView.setText(Integer.toString(number1) + "x" + Integer.toString(number2));
                            mainEditInt.getText().clear();
                        }
                    } else {
                        number2 = (int) (Math.random() * 12 + 1);
                        result = number1 * number2;
                        mainTextView.setText(Integer.toString(number1) + "x" + Integer.toString(number2));
                        mainEditInt.getText().clear();
                    }

                }
                else {
                    int number1 = (int) (Math.random() * 12 + 1);
                    int number2 = 0;
                    if (modeType == 0) {
                        if (mainEditText.getText().toString().trim().length() == 0) {
                            mainTextView.setText(R.string.entNumAb);
                        } else {
                            number2 = Integer.parseInt(mainEditText.getText().toString());
                            result = number1;
                            mainTextView.setText(Integer.toString(number1*number2) + "/" + Integer.toString(number2));
                            mainEditInt.getText().clear();
                        }
                    } else {
                        number2 = (int) (Math.random() * 12 + 1);
                        result = number1;
                        mainTextView.setText(Integer.toString(number1*number2) + "/" + Integer.toString(number2));
                        mainEditInt.getText().clear();
                    }
                }


                if (timed.isChecked()) {
                    if (!timerHasStarted) {
                        timerHasStarted = true;
                        correct = 0;
                        editor.putInt(getString(R.string.saved_correct), correct);
                        editor.commit();
                        correctTextView.setText(Integer.toString(correct));
                        wrong = 0;
                        editorw.putInt(getString(R.string.saved_wrong), wrong);
                        editorw.commit();
                        wrongTextView.setText(Integer.toString(wrong));
                        new CountDownTimer(60000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                timeTextView.setText("t: " + millisUntilFinished / 1000);
                            }

                            public void onFinish() {
                                timeTextView.setText(R.string.done);
                                timerHasStarted = false;
                                Intent intent = new Intent(v.getContext(), Main2Activity.class);
                                startActivity(intent);
                                //createNotification();
                            }
                        }.start();
                    }
                }


                break;

            case R.id.second_button:
                if (mainEditInt.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), R.string.entAnswer, Toast.LENGTH_LONG).show();
                } else {
                    answer = Integer.parseInt(mainEditInt.getText().toString());
                    String resultString = String.format("%d",result);
                    if (answer == result) {
                        mainTextView.setText(R.string.yourRight);
                        mainTextView2.setText(resultString);
                        correct++;
                        editor.putInt(getString(R.string.saved_correct), correct);
                        editor.commit();
                        correctTextView.setText(Integer.toString(correct));
                        if (!timed.isChecked()) {
                            if (correct % 10 == 0) {
                                Intent intent = new Intent(v.getContext(), Main2Activity.class);
                                startActivity(intent);
                            }
                        }

                    } else {
                        mainTextView.setText(R.string.yourWrong);
                        mainTextView2.setText(resultString);
                        wrong++;
                        editor.putInt(getString(R.string.saved_wrong), wrong);
                        editor.commit();
                        wrongTextView.setText(Integer.toString(wrong));
                    }
                    mainEditInt.getText().clear();
                }
                break;

            case R.id.reset_button:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.reset);
                builder.setCancelable(false);
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        correct = 0;
                        editor.putInt(getString(R.string.saved_correct), correct);
                        editor.commit();
                        correctTextView.setText(Integer.toString(correct));
                        wrong = 0;
                        editorw.putInt(getString(R.string.saved_wrong), wrong);
                        editorw.commit();
                        wrongTextView.setText(Integer.toString(wrong));
                    }
                });
                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                break;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.easy) {
            Toast.makeText(getApplicationContext(), R.string.enterNum, Toast.LENGTH_LONG).show();
            mainEditText.getText().clear();
            modeType=0;
            return true;
        }
        else if(id == R.id.difficult) {
            Toast.makeText(getApplicationContext(), R.string.mixedOp, Toast.LENGTH_LONG).show();
            mainEditText.setText(R.string.difficult);
            modeType=1;

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                // Additions
                optype = 0;
                break;
            case 1:
                // Subtractions
                optype = 1;
                break;
            case 2:
                // Multiplications
                optype = 2;
                break;
            case 3:
                // Divisions
                optype = 3;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        optype = 0;
    }
}


