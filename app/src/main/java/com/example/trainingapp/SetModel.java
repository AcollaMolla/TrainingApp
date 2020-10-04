package com.example.trainingapp;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SetModel {
    private int reps;
    private boolean finished;

    public SetModel(int avgRep){
        this.reps = GenerateRep(avgRep);
        finished = false;
    }

    public int GetReps(){
        return this.reps;
    }

    public boolean IsFinished(){
        return this.finished;
    }

    public void SetFinished(){
        this.finished = true;
    }

    private int GenerateRep(int avgRep){
       int min = (int)Math.floor(avgRep*0.7);
       int max = (int)Math.ceil(avgRep*1.3);
       return new Random().nextInt((max-min)+1) + min;
    }
}
