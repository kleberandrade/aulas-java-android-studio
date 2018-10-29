package br.fatec.aula.fatecchatapp.activities;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.fatec.aula.fatecchatapp.R;

public class SplashActivity extends BaseActivity implements Runnable {

    private static final int DELAY_MILLIS = 2000;

    private FirebaseAuth auth;

    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        auth = FirebaseAuth.getInstance();
        auth.signOut();

        Handler handle = new Handler();
        handle.postDelayed(this, DELAY_MILLIS);
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = auth.getCurrentUser();
    }

    @Override
    public void run() {
        if (currentUser != null) {
            Intent intent = new Intent(SplashActivity.this, ChatActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
