package br.fatec.aula.fatecchatapp.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.fatec.aula.fatecchatapp.R;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements Runnable {

    private static final int DELAY_MILLIS = 2000;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        Handler handle = new Handler();
        handle.postDelayed(this, DELAY_MILLIS);
    }

    private void changeActivity() {

        FirebaseUser user = auth.getInstance().getCurrentUser();
        Intent intent = null;
        //if (user != null){
        //    intent = new Intent(SplashActivity.this, ChatActivity.class);
        //} else {
        intent = new Intent(SplashActivity.this, SignInActivity.class);
        //}

        startActivity(intent);
        finish();
    }

    @Override
    public void run() {
        changeActivity();
    }
}
