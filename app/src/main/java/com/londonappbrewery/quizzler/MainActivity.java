package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {



    // TODO: Deklarace member proměnných:
    Button mTrueButton;
    Button mFalseButton;
    TextView mQuestionTextView;
    TextView mScoreTextView;
    ProgressBar mProgressBarr;
    int mIndex;  //defaultni hodnota je 0
    int mScore; // počet spravnych odpovedí
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

    // TODO: Deklarace konstant
    final   int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.0 / mQuestionBank.length ); // jednotka progres baru

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    mTrueButton = (Button) findViewById(R.id.true_button); //konvertujeme z View do Button
    mFalseButton = findViewById(R.id.false_button);
    mQuestionTextView = findViewById(R.id.question_text_view); //ulozi hodnotu question_text_view do mQuestionTextView
    mScoreTextView = findViewById(R.id.score);
    mProgressBarr = findViewById(R.id.progress_bar);

    mQuestion = mQuestionBank[mIndex].getmQuestionID(); // vrátí text otázky, getmQ... definované v TF.java
    mQuestionTextView.setText(mQuestion);  // vloží text do textového pole mQTV

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswers(true); // zkontroluje jestli true je správná odpoved
                updateQuestion();

            }
        });

     // Zkrácený zápis(sloučené) pro levé tlačítko

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateQuestion();
                checkAnswers(false); // zkontroluje jestli false je správná odpoved
            }
        });


    }

    private void updateQuestion(){  // metoda na vkladani textu otazky do text. pole
        mIndex=(mIndex+1) % mQuestionBank.length;   //zaciname na nule, modulo zajisti nepřekročení indexu 12 (13 otazka)
        if (mIndex==0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);  // this = MainActivity
            alert.setTitle("Konec kvízu.");
            alert.setCancelable(false); // zamezi zmizeni boxu kliknutím nekam mimo něj
            alert.setMessage("Vaše skóre je: " + mScore + " bodů."); // zpráva v boxu
            alert.setPositiveButton("Zavřít", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();  // ukončení aplikace
                }
            });
            alert.show(); // zobrazi dialog na obrazovku
         }
        mQuestion=mQuestionBank[mIndex].getmQuestionID(); //ziskame text otazky
        mQuestionTextView.setText(mQuestion); // a vlozime ho do textoveho pole otazky
        mProgressBarr.incrementProgressBy(PROGRESS_BAR_INCREMENT); // zvýší hodnotu o 8
        mScoreTextView.setText("Skóre " + mScore + "/" + mQuestionBank.length); // přepíše text ve Score
    }

    private void    checkAnswers(boolean userSelection){
        boolean spravnaOdpoved = mQuestionBank[mIndex].ismAnswer();

        if (spravnaOdpoved==userSelection){
            Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT).show(); // Oznameni o správné odpovedi
            mScore++; // když je spravna odpoveď tak ji pridame do Score
        }
        else {
            Toast.makeText(getApplicationContext(),R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
        }

    }
}
