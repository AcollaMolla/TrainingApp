package com.example.trainingapp;

import java.util.ArrayList;
import java.util.Observable;
import java.util.List;

public class Model extends Observable {
    private int NumberOfReps = 4;
    private List<SetModel> set;
    private int CurrentRep = 0;

    public Model(){
        set = new ArrayList<SetModel>(NumberOfReps);
    }

    public String GetRepCount(int index) throws IndexOutOfBoundsException{
        return Integer.toString(this.set.get(index).GetReps());
    }

    public String GetCurrentRep(){
        return Integer.toString(this.set.get(CurrentRep).GetReps());
    }

    public void SetReps(int avgRep){

        for(int i=0;i<this.NumberOfReps;i++){
            this.set.add(new SetModel(avgRep));
        }
        setChanged();
        notifyObservers();
    }

    public void FinishRep(){
        if(!IsFinished()){
            this.set.get(this.CurrentRep).SetFinished();
            NextRep();
        }
        setChanged();
        notifyObservers();
    }

    private void NextRep(){
        if(this.CurrentRep < this.set.size()){
            this.CurrentRep = this.CurrentRep + 1;
        }
    }

    public boolean IsFinished(){
        if(this.CurrentRep <= this.set.size()-1){
            return false;
        }
        return true;
    }
}
