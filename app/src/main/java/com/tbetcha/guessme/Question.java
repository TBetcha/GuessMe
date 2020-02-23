package com.tbetcha.guessme;

import java.util.Random;

public class Question {

    private int question;
    private String name;



public Question(int question, String name){
    this.question=question;
    this.name = name;

}

public void oneWrong(){

}

public void twoWrong(){

}

    public void setName(int Question){
        this.question = question;
        this.name = name;

    }

    public String getName(){
    return this.name;
    }

    public void rightAns(){

    }

    public int  getQuestion(){
      return this.question;
    }

/*public int makeNumber(){     might not need this here - moved main activity
    Random r = new Random();
    return  r.nextInt(4);*/
}




