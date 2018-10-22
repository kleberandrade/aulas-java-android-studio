package br.edu.fatec.aula.trucoscoreapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final int TEAM_A = 1;
    private final int TEAM_B = 2;

    private final int POINTS_FOR_ZERO = 0;
    private final int POINTS_FOR_ONE = 1;
    private final int POINTS_FOR_THREE = 3;
    private final int POINTS_FOR_SIX = 6;
    private final int POINTS_FOR_NINE = 9;
    private final int POINTS_FOR_TWELVE = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increasePointInPlayer(int playerNumber, int point){

        TextView playerScore = null;

        if (playerNumber == TEAM_A){
            playerScore = (TextView) findViewById(R.id.player1_score);
        } else {
            playerScore = (TextView) findViewById(R.id.player2_score);
        }

        int score = Integer.parseInt((String) playerScore.getText());
        score += point;

        playerScore.setText(String.valueOf(score));

        Log.d("BUTTON", playerNumber + " | " + point);
    }


    public void increaseOneInPlayerOne(View view) {
        increasePointInPlayer(TEAM_A, POINTS_FOR_ONE);
    }

    public void increaseThreeInPlayerOne(View view) {
        increasePointInPlayer(TEAM_A, POINTS_FOR_THREE);
    }

    public void increaseSixInPlayerOne(View view) {
        increasePointInPlayer(TEAM_A, POINTS_FOR_SIX);
    }

    public void increaseNineInPlayerOne(View view) {
        increasePointInPlayer(TEAM_A, POINTS_FOR_NINE);
    }

    public void increaseTwelveInPlayerOne(View view) {
        increasePointInPlayer(TEAM_A, POINTS_FOR_TWELVE);
    }

    public void increaseOneInPlayerTwo(View view) {
        increasePointInPlayer(TEAM_B, POINTS_FOR_ONE);
    }

    public void increaseThreeInPlayerTwo(View view) {
        increasePointInPlayer(TEAM_B, POINTS_FOR_THREE);
    }

    public void increaseSixInPlayerTwo(View view) {
        increasePointInPlayer(TEAM_B, POINTS_FOR_SIX);
    }

    public void increaseNineInPlayerTwo(View view) {
        increasePointInPlayer(TEAM_B, POINTS_FOR_NINE);
    }

    public void increaseTwelveInPlayerTwo(View view) {
        increasePointInPlayer(TEAM_B, POINTS_FOR_TWELVE);
    }

    public void resetScore(View view) {
        TextView playerOneScore = (TextView) findViewById(R.id.player1_score);
        playerOneScore.setText("" + POINTS_FOR_ZERO);
        TextView playerTwoScore = (TextView) findViewById(R.id.player2_score);
        playerTwoScore.setText("" + POINTS_FOR_ZERO);
    }
}
