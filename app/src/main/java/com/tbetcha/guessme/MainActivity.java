package com.tbetcha.guessme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Integer ansPics[] = { R.drawable.item_0, R.drawable.item_1, R.drawable.item_2,R.drawable.item_3};
    private int currAnsPic = 0;
    private Button submitButton;
    private EditText typeGuess;
    private ImageView strikeOne;
    private ImageView strikeTwo;
    private ImageView showItem;
    private ImageView strikeOut;
    private String currAns;
    private String guess;
    private boolean hasOneStrike = false;
    private boolean hasTwoStrike = false;
    public boolean corrAns = false;
    public boolean strikeThree = false;

    private static final String TAG = "Answer:";


    //make random number to generate question
    public int makeRandNumber() {
        Random r = new Random();
        currAnsPic = r.nextInt(4);
        return currAnsPic;
    }

    //question array
    private Question[] questions = new Question[]{
            new Question(0, "MILK"),
            new Question(1, "WATER"),
            new Question(2, "SODA"),
            new Question(3, "JUICE"),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showItem = (ImageView) findViewById(R.id.showItem);
        submitButton = (Button) findViewById(R.id.submit_button);
        strikeOne = (ImageView) findViewById(R.id.strike_one);
        strikeTwo = (ImageView) findViewById(R.id.strike_two);
        strikeOut = (ImageView) findViewById(R.id.strike_out);

        Log.d(TAG, "Text here ");

        typeGuess = (EditText) findViewById(R.id.enter_guess);
        typeGuess.setHint("Guess here");
        begin();

        //once submit is pressed get user input and check it against real ans
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer();
                checkRealAns();
            }
        });
    }

    //generate new question
    public void nextQuestion(int random) {
        makeRandNumber();
        currAns = questions[currAnsPic].getName();
    }

    public void checkRealAns() {
        if (getAnswer().equals(questions[currAnsPic].getName())) {
            correct();
            Toast.makeText(this, "WOOHOOO", Toast.LENGTH_SHORT).show();
            if (questions[currAnsPic].getQuestion() == currAnsPic) {
                showItem.setImageResource(ansPics[currAnsPic]);
                showItem.setVisibility(View.VISIBLE);
            }
            nextQuestion(makeRandNumber());
             delayedBegin();

        } else {
            whichWrong();
        }
    }

    public void whichWrong() {
        if (!hasOneStrike && !hasTwoStrike) {
            hasOneStrike = true;
            hasTwoStrike = false;
            corrAns = false;
            strikeOne.setVisibility(View.VISIBLE);
            strikeTwo.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Strike one", Toast.LENGTH_SHORT).show();
        } else if (hasOneStrike && !hasTwoStrike) {
            hasOneStrike = true;
            hasTwoStrike = true;
            corrAns = false;
            strikeTwo.setVisibility(View.VISIBLE);
            Toast.makeText(this,"Strike two",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "You struck out", Toast.LENGTH_SHORT).show();
            strikeThree = true;
            strikeOut.setVisibility(View.VISIBLE);
            delayedBegin();

        }
    }

    public String getAnswer() {
        guess = typeGuess.getText().toString().toUpperCase();
        return guess;
    }

    //initial method to set game to beginning stage
    public void begin() {
         strikeThree = false;
         corrAns = false;
         noStrikes();
        showItem.setVisibility(View.INVISIBLE);
        nextQuestion(makeRandNumber());
        Log.d(TAG, currAns);
    }

    //if correct answer remove strikes and show pic
    public void correct(){
        strikeThree = false;
        corrAns = true;
        noStrikes();
    }

    //used after a strike out or correct answer to reset after time delay
    public void delayedBegin(){
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        begin();
                    }
                },
                2000);
    }

    public void noStrikes(){
        hasOneStrike = false;
        hasTwoStrike = false;
        strikeOne.setVisibility(View.INVISIBLE);
        strikeTwo.setVisibility(View.INVISIBLE);
        strikeOut.setVisibility(View.INVISIBLE);
    }

}