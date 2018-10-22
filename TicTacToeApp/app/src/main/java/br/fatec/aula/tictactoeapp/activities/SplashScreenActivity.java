package br.fatec.aula.tictactoeapp.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.List;

import br.fatec.aula.tictactoeapp.R;
import butterknife.BindViews;
import butterknife.ButterKnife;


public class SplashScreenActivity extends AppCompatActivity implements Runnable {

    private static final int DELAY_MILLIS = 2000;

    @BindViews({R.id.logo1, R.id.logo2, R.id.logo3, R.id.logo4})
    List<TextView> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        setlogoAnimation();

        Handler handler = new Handler();
        handler.postDelayed(this, DELAY_MILLIS);
    }

    private void setlogoAnimation() {
        Animation fadeAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        for (TextView view : list)
            view.startAnimation(fadeAnimation);
    }

    @Override
    public void run() {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
