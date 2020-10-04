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
    private TextView rep1;
    private TextView rep2;
    private TextView rep3;
    private TextView rep4;
    private TextView currentRep;
    private boolean finished = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mModel = new Model();
        mModel.addObserver(this);
        startButton = (Button)findViewById(R.id.button);
        nextButton  = (Button)findViewById(R.id.button2);
        rep1 = (TextView)findViewById(R.id.textView6);
        rep2 = (TextView)findViewById(R.id.textView7);
        rep3 = (TextView)findViewById(R.id.textView8);
        rep4 = (TextView)findViewById(R.id.textView9);
        currentRep = (TextView)findViewById(R.id.currentRep);
        startButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.e("onclick", "clicked");
        switch(v.getId()){
            case R.id.button:
                mModel.SetReps(this.AvgRep);
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

        rep1.setText(mModel.GetRepCount(0));
        rep2.setText(mModel.GetRepCount(1));
        rep3.setText(mModel.GetRepCount(2));
        rep4.setText(mModel.GetRepCount(3));
        if(!currentRep.isShown()){
            currentRep.setVisibility(View.VISIBLE);
        }
        if(!m.IsFinished()){
            currentRep.setText(mModel.GetCurrentRep());
        }
        else{
            currentRep.setText("Finished!");
        }
    }
}
