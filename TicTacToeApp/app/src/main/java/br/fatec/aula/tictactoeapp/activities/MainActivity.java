package br.fatec.aula.tictactoeapp.activities;

import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.fatec.aula.tictactoeapp.R;
import br.fatec.aula.tictactoeapp.fragments.AlertDialogFragment;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class MainActivity extends AppCompatActivity {

    private static final String[] MARKER_CELL = new String[]{"O", "X"};
    private static final String EMPTY_CELL = "";
    private static final int PLAYER_ONE = 0;
    private static final int PLAYER_TWO = 1;
    private static final int MAX_PLAY_NUMBER = 9;

    private static final int DIALOG_DISPLAY_DELAY = 50;

    @BindViews({R.id.cell_00_button, R.id.cell_01_button, R.id.cell_02_button,
            R.id.cell_10_button, R.id.cell_11_button, R.id.cell_12_button,
            R.id.cell_20_button, R.id.cell_21_button, R.id.cell_22_button})
    List<Button> buttons;

    @BindView(R.id.player_one)
    TextView playerOneTextView;

    @BindView(R.id.player_two)
    TextView playerTwoTextView;

    private int currentPlayer;

    private int playNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initializeBoardCells();
    }

    @OnClick(R.id.reset_button)
    public void tryAgainOnClickButton(View view) {
        initializeBoardCells();
    }

    private void disabelAllButtons() {
        for (Button button : buttons) {
            button.setEnabled(false);
        }
    }

    private void initializeBoardCells() {
        currentPlayer = 0;
        playNumbers = 0;

        for (Button button : buttons) {
            button.setText(EMPTY_CELL);
            button.setEnabled(true);
        }

        highlightCurrentPlayer();
    }

    private void highlightCurrentPlayer() {
        if (currentPlayer == PLAYER_ONE) {
            playerOneTextView.setTypeface(null, Typeface.BOLD);
            playerTwoTextView.setTypeface(null, Typeface.NORMAL);
        } else if (currentPlayer == PLAYER_TWO) {
            playerOneTextView.setTypeface(null, Typeface.NORMAL);
            playerTwoTextView.setTypeface(null, Typeface.BOLD);
        }
    }

    private void setButtonMarker(Button button) {
        if (TextUtils.isEmpty(button.getText().toString())) {
            button.setText(MARKER_CELL[currentPlayer]);

            if (currentPlayer == PLAYER_ONE)
                button.setTextColor(getResources().getColor(R.color.colorPlayerOne));
            else if (currentPlayer == PLAYER_TWO)
                button.setTextColor(getResources().getColor(R.color.colorPlayerTwo));

            playNumbers++;

            checkWinner();

            currentPlayer = ++currentPlayer % 2;

            highlightCurrentPlayer();
        }
    }

    public boolean verifyCells(int a, int b, int c) {
        return buttons.get(a).getText().toString().equals(MARKER_CELL[currentPlayer]) &&
                buttons.get(b).getText().toString().equals(MARKER_CELL[currentPlayer]) &&
                buttons.get(c).getText().toString().equals(MARKER_CELL[currentPlayer]);
    }

    private void checkWinner() {
        if (verifyCells(0, 3, 6) || verifyCells(1, 4, 7) || verifyCells(2, 5, 8) ||
                verifyCells(0, 1, 2) || verifyCells(3, 4, 5) || verifyCells(6, 7, 8) ||
                verifyCells(0, 4, 8) || verifyCells(2, 4, 6)) {
            disabelAllButtons();
            showWinner("Fim do jogo", "Jogador " + (currentPlayer + 1)  + " ( " + MARKER_CELL[currentPlayer] + " ) " + " ganhou!");
        } else if (playNumbers == MAX_PLAY_NUMBER) {
            disabelAllButtons();
            showWinner("Fim do jogo", "Deu velha!");
        }
    }

    @Optional
    @OnClick({R.id.cell_00_button, R.id.cell_01_button, R.id.cell_02_button,
            R.id.cell_10_button, R.id.cell_11_button, R.id.cell_12_button,
            R.id.cell_20_button, R.id.cell_21_button, R.id.cell_22_button})
    public void onClick(View view) {
        setButtonMarker((Button) view);
    }

    private void showWinner(final String title, final String message){

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                DialogFragment newFragment = AlertDialogFragment.newInstance(title, message, "OK");
                newFragment.show(getSupportFragmentManager(), "DIALOG");

            }
        }, DIALOG_DISPLAY_DELAY);
    }
}
