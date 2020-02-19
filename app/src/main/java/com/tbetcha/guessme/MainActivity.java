package com.tbetcha.guessme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Integer ansPics[] = {R.drawable.coffee, R.drawable.juice, R.drawable.milk, R.drawable.water, R.drawable.soda_cans};
    private int currAnsPic = 0;

    private Button SubmitButton;
    private EditText typeGuess;
    private ImageView strikeOne;
    private ImageView strikeTwo;
    private ImageView showItem;

    private Question[] questions = new Question[]{
            new Question(0,"MILK"),
            new Question(1, "WATER"),
            new Question(2, "SODA"),
            new Question(3,"JUICE" ),
            new Question(4, "COFEE")
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
}
