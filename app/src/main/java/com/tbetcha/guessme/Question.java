package com.tbetcha.guessme;

import java.util.Random;

public class Question {

    private int question;
    private String name;

public Question(int question, String name){
    this.question=question;
    this.name = name;
}

public int makeNumber(){
    Random r = new Random();
    return  r.nextInt(4);
}



}
