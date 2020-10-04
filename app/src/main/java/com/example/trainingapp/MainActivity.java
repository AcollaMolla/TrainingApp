package com.example.trainingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer, View.OnClickListener {

    private final int AvgRep = 20;
    private Model mModel;
    private Button startButton;
    private Button nextButton;
    private List<TextView> reps;
    private TextView rep0;
    private TextView rep1;
    private TextView rep2;
    private TextView rep3;
    private TextView rep4;
    private TextView rep5;
    private TextView currentRep;
    private int MaxNumberOfSets = 6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mModel = new Model();
        mModel.addObserver(this);
        reps = new ArrayList<TextView>(this.MaxNumberOfSets);

        startButton = (Button)findViewById(R.id.button);
        nextButton  = (Button)findViewById(R.id.button2);

        rep0 = (TextView)findViewById(R.id.rep0);
        reps.add(rep0);

        rep1 = (TextView)findViewById(R.id.rep1);
        reps.add(rep1);

        rep2 = (TextView)findViewById(R.id.rep2);
        reps.add(rep2);

        rep3 = (TextView)findViewById(R.id.rep3);
        reps.add(rep3);

        rep4 = (TextView)findViewById(R.id.rep4);
        reps.add(rep4);

        rep5 = (TextView)findViewById(R.id.rep5);
        reps.add(rep5);

        currentRep = (TextView)findViewById(R.id.currentRep);

        mModel.SetReps(this.AvgRep);
        startButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.e("onclick", "clicked");
        switch(v.getId()){
            case R.id.button:
                mModel.StartWorkout();
                startButton.setVisibility(View.INVISIBLE);
                nextButton.setVisibility(View.VISIBLE);
                break;
            case R.id.button2:
                mModel.FinishRep();
                break;
            default:
                break;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Model m = (Model)o;

        for(int i=0;i<mModel.GetNumberOfSets();i++){
            this.reps.get(i).setText(mModel.GetRepCount(i));
        }

        if(m.WorkoutStarted()){
            currentRep.setVisibility(View.VISIBLE);
        }
        if(m.WorkoutStarted() && !m.IsFinished()){
            currentRep.setText(mModel.GetCurrentRep());
        }
        else if(m.WorkoutStarted() && m.IsFinished()){
            currentRep.setText("Finished!");
        }
    }
}
