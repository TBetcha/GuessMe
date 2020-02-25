package com.tbetcha.guessme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Integer ansPics[] = {R.drawable.item_3, R.drawable.item_0, R.drawable.item_1, R.drawable.item_2};
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
    private boolean corrAns = false;
    private boolean strikeThree = false;

   //TODO fix guess line so it has a hint and not solid text


    public int makeRandNumber() {
        Random r = new Random();
        currAnsPic = r.nextInt(4);
        return currAnsPic;
    }

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


        typeGuess = (EditText) findViewById(R.id.enter_guess);
        typeGuess.setHint("Guess here");
        begin();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer();
                checkRealAns();


            }
        });


    }

    public void nextQuestion(int random) {
        makeRandNumber();
        currAns = questions[currAnsPic].getName();
    }

    public void checkRealAns() {
        if (getAnswer().equals(questions[currAnsPic].getName())) {
            correct();
            Toast.makeText(this, "WOOHOOO", Toast.LENGTH_SHORT).show();
            if (questions[currAnsPic].getQuestion() == 0) {
                showItem.setImageResource(R.drawable.item_0);
                showItem.setVisibility(View.VISIBLE);
            } else if (questions[currAnsPic].getQuestion() == 1) {
                showItem.setImageResource(R.drawable.item_1);
                showItem.setVisibility(View.VISIBLE);
            } else if (questions[currAnsPic].getQuestion() == 2) {
                showItem.setImageResource(R.drawable.item_2);
                showItem.setVisibility(View.VISIBLE);
            } else if (questions[currAnsPic].getQuestion() == 3) {
                showItem.setImageResource(R.drawable.item_3);
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

    public void begin() {
         hasOneStrike = false;
         hasTwoStrike = false;
         strikeThree = false;
         corrAns = false;
        strikeOne.setVisibility(View.INVISIBLE);
        strikeTwo.setVisibility(View.INVISIBLE);
        strikeOut.setVisibility(View.INVISIBLE);
        showItem.setVisibility(View.INVISIBLE);
        nextQuestion(makeRandNumber());
    }

    public void correct(){
        hasOneStrike = false;
        hasTwoStrike = false;
        strikeThree = false;
        corrAns = true;
        strikeOne.setVisibility(View.INVISIBLE);
        strikeTwo.setVisibility(View.INVISIBLE);
        strikeOut.setVisibility(View.INVISIBLE);
    }

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


}