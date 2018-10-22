package br.fatec.aula.interestcalculatorapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import br.fatec.aula.interestcalculatorapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreenActivity extends AppCompatActivity implements Runnable {

    private static final int DELAY_MILLIS = 2000;

    @BindView(R.id.title)
    TextView titleTextView;

    @BindView(R.id.subtitle)
    TextView subtitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        Animation moveUpAnimation = AnimationUtils.loadAnimation(this, R.anim.move_up);
        subtitleTextView.startAnimation(moveUpAnimation);

        Animation moveDownAnimation = AnimationUtils.loadAnimation(this, R.anim.move_down);
        titleTextView.startAnimation(moveDownAnimation);

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
