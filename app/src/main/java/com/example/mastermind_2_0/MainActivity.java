package com.example.mastermind_2_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private View BUTTONS;
    private View Diff;
    private View Instruction;
    private View ScoreScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BUTTONS = findViewById(R.id.Buttons);
        Diff = findViewById(R.id.Diff);
        Instruction = findViewById(R.id.Instructions);
        ScoreScreen = findViewById(R.id.ScoreScreen);
        Instruction.setActivated(false);
        ScoreScreen.setActivated(false);
        Diff.setActivated(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        BUTTONS.setVisibility(View.VISIBLE);
        BUTTONS.setActivated(true);
        Instruction.setActivated(false);
        ScoreScreen.setActivated(false);
        Diff.setActivated(false);
        Diff.setVisibility(View.INVISIBLE);
    }

    public void StartNewGame(View V){
        Diff.setVisibility(View.VISIBLE);
        Diff.setActivated(true);
        BUTTONS.setActivated(false);
        BUTTONS.setVisibility(View.INVISIBLE);
    }
    public void EasyMode(View V){
        Intent intent = new Intent(this,Game.class);
        intent.putExtra("DIGIT",3);
        intent.putExtra("DIFFI","T");
        intent.putExtra("DifficultyMode","EASY");
        startActivity(intent);
    }
    public void MediumMode(View V){
        Intent intent = new Intent(this,Game.class);
        intent.putExtra("DIGIT",4);
        intent.putExtra("DIFFI","T");
        intent.putExtra("DifficultyMode","MEDIUM");
        startActivity(intent);
    }public void HardMode(View V){
        Intent intent = new Intent(this,Game.class);
        intent.putExtra("DIGIT",5);
        intent.putExtra("DIFFI","T");
        intent.putExtra("DifficultyMode","HARD");
        startActivity(intent);
    }
    public void ShowInstructions(View V){
        Instruction.setActivated(true);
        Instruction.setVisibility(View.VISIBLE);
        BUTTONS.setVisibility(View.INVISIBLE);
        BUTTONS.setActivated(false);
    }
    public void ShowScores(View V){
        ScoreScreen.setActivated(true);
        ScoreScreen.setVisibility(View.VISIBLE);
        BUTTONS.setVisibility(View.INVISIBLE);
        BUTTONS.setActivated(false);
        SharedPreferences sharedPreferences = getSharedPreferences("HIGH_SCORES", Context.MODE_PRIVATE);
        ((TextView)findViewById(R.id.EasyScore)).setText("EASY: " + sharedPreferences.getInt("EASY",0));
        ((TextView)findViewById(R.id.MediumScore)).setText("MEDIUM: " + sharedPreferences.getInt("MEDIUM",0));
        ((TextView)findViewById(R.id.HardScore)).setText("HARD: " + sharedPreferences.getInt("HARD",0));
    }
    public void ExitGame(View V){
        finish();
        System.exit(0);
    }
    public void MenuBack(View V){
        BUTTONS.setActivated(true);
        BUTTONS.setVisibility(View.VISIBLE);
        if(Instruction.isActivated()){
            Instruction.setVisibility(View.INVISIBLE);
            Instruction.setActivated(false);
        }
        if(ScoreScreen.isActivated()){
            ScoreScreen.setVisibility(View.INVISIBLE);
            ScoreScreen.setActivated(false);
        }
        if(Diff.isActivated()){
            Diff.setVisibility(View.INVISIBLE);
            Diff.setActivated(false);
        }
    }
}
