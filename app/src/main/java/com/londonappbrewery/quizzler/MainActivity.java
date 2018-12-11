package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // TODO: Deklarace konstant


    // TODO: Deklarace member proměnných:
    Button mTrueButton;
    Button mFalseButton;
    TextView mQuestionTextView;
    int mIndex;  //defaultni hodnota je 0
    int mQuestion;

    // TODO: Seznam otázek
    private TrueFalse[] mQuestionBank = new TrueFalse[] {  // TrueFalse třída je deklarovaná v TrueFalse.java
            new TrueFalse(R.string.question_1, true), // ze strings.xml si vytáhne text první otazky
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    mTrueButton = (Button) findViewById(R.id.true_button); //konvertujeme z View do Button
    mFalseButton = findViewById(R.id.false_button);
    mQuestionTextView = findViewById(R.id.question_text_view); //ulozi hodnotu question_text_view do mQuestionTextView

    mQuestion = mQuestionBank[mIndex].getmQuestionID(); // vrátí text otázky, getmQ... definované v TF.java
    mQuestionTextView.setText(mQuestion);  // vloží text do textového pole mQTV

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Quizzler","Tlačítko zmáčknuto."); // zobrazení v logu
                Toast.makeText(getApplicationContext(),"Tlačítko TRUE zmáčknuto!",Toast.LENGTH_SHORT).show();
            }
        });

     // Zkrácený zápis(sloučené) pro levé tlačítko

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // zobrazí se hláška na displayi po ztlačení tlačítka
                Toast.makeText(getApplicationContext(), "Tlačítko FALSE zmáčknuto!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
