package com.example.mastermind_2_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

public class Game extends AppCompatActivity {
    private  EditText guess;
    private String DifficultyMode;
    private  TextView Attempts;
    private TextView Results;
    private TextView Attemptss;
    private TextView INVALID;
    private View WinScreen;
    private char Difficulty;
    private TextView ScoreText;
    int digitLength = 5;
    int score;
    int tries = 0;
    String CorrectNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Attemptss = findViewById(R.id.textView);
        Attempts = findViewById(R.id.Attempts);
        guess = findViewById(R.id.UserGuess);
        INVALID = findViewById(R.id.INVALID);
        Results = findViewById(R.id.Results);
        WinScreen = findViewById(R.id.WinScreen);
        ScoreText = findViewById(R.id.ScoreText);
        WinScreen.setActivated(false);
        digitLength = getIntent().getIntExtra("DIGIT",4);
        Difficulty = getIntent().getCharExtra("Diffi",'T');
        SetCorrectNo(Difficulty);
        DifficultyMode = getIntent().getStringExtra("DifficultyMode");
        score = (int)Math.pow(10,digitLength);
        guess.setHint("Enter a " + digitLength + " digit No");
    }
    private void SetCorrectNo(char Diffi){
        Random random = new Random();
        int min = (int)Math.pow(10,digitLength-1);
        CorrectNo = Integer.toString(random.nextInt(min) + min);
    }
    private String GetResult(String Guessed){
        if(Guessed.compareTo(CorrectNo) == 0){
            GameWon();
        }
        String correctNo = CorrectNo;
        char[] resultTrial = {' ' , ' ' , ' ' , ' ',' ',' '};
        String ResultT ="";
        Guessed = guess.getText().toString();
        int s = Guessed.length();
        if(s!=digitLength){
            return "INVALID";
        }
        boolean flag =true;
        for (int i = 0; i < digitLength; i++) {
            for (int j = 0; j < digitLength; j++) {
                if (Guessed.charAt(i) == correctNo.charAt(j)) {
                    correctNo = correctNo.substring(0, j) + '|' + correctNo.substring(j + 1);  //'|' is anything arbitrary;
                    flag = false;
                    if (i == j)
                        resultTrial[i] = 'X';
                    else
                        resultTrial[i] = '0';
                    break;
                }
            }
        }

        if (flag)
            return "Sed Lyf!";
        for(int i =0;i<digitLength;i++)
            ResultT += resultTrial[i] + " ";
        return ResultT;
    }
    public void Process_guess(View V) {
        tries++;
        if(INVALID.getVisibility() == View.VISIBLE)
            INVALID.setVisibility(View.INVISIBLE);
        Attemptss.setText("Attempts (" + tries + ") :");
        String Guessed = guess.getText().toString();
        String res = GetResult(Guessed);
        if(res == "INVALID"){
            INVALID.setVisibility(View.VISIBLE);
            tries--;
        }else{
        Attempts.setText( Guessed + "\n\n" + Attempts.getText().toString());
        Results.setText(res + "\n\n" + Results.getText().toString());
        }
        guess.setText("");
    }
    private void GameWon(){
        SharedPreferences sharedPreferences = getSharedPreferences("HIGH_SCORES",Context.MODE_PRIVATE);
        guess.setEnabled(false);
        findViewById(R.id.SubmitButton).setActivated(false);
        WinScreen.setVisibility(View.VISIBLE);
        WinScreen.setActivated(true);
        score -= tries*(tries+1)/2;
        if(sharedPreferences.getInt(DifficultyMode,0)<score) {
            sharedPreferences.edit().putInt(DifficultyMode, score).commit();
        }
        ScoreText.setText("Score:\t\n\t\t\t\t\t\t\t\t\t\t\t\t\t" + score);
    }

    public void MenuPress(View V){
        finish();
    }
    public void RestartPress(View V){
        Intent intent = new Intent(this,Game.class);
        intent.putExtra("DIGIT",digitLength);
        intent.putExtra("DIFFI",Difficulty);
        intent.putExtra("DifficultyMode",DifficultyMode);
        startActivity(intent);
    }
}
