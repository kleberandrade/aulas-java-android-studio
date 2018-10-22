package br.edu.fatec.aula.lendingstuffapp.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.edu.fatec.aula.lendingstuffapp.R;

public class SplashScreenActivity extends AppCompatActivity implements Runnable {

    private static final int DELAY_MILLIS = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handle = new Handler();
        handle.postDelayed(this, DELAY_MILLIS);
    }

    private void changeToMainActivity() {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void run() {
        changeToMainActivity();
    }
}
