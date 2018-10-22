package br.fatec.aula.fatecchatapp.activities;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.fatec.aula.fatecchatapp.R;
import br.fatec.aula.fatecchatapp.utils.FormHelper;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends BaseActivity {

    private static final String TAG = "EmailPassword";

    @BindView(R.id.name)
    EditText nameField;

    @BindView(R.id.email)
    EditText emailField;

    @BindView(R.id.password)
    EditText passwordField;

    @BindView(R.id.confirm_password)
    EditText confirmPasswordField;

    @BindView(R.id.layout_name)
    TextInputLayout nameLayout;

    @BindView(R.id.layout_email)
    TextInputLayout emailLayout;

    @BindView(R.id.layout_password)
    TextInputLayout passwordLayout;

    @BindView(R.id.layout_confirm_password)
    TextInputLayout confirmPasswordLayout;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();
    }

    private boolean validateForm(String name, String email, String password, String confirmPassword) {
        boolean resultValidate = false;

        nameLayout.setError(null);
        emailLayout.setError(null);
        passwordLayout.setError(null);
        confirmPasswordLayout.setError(null);

        if (FormHelper.isEmpty(name)) {
            nameLayout.setError(getString(R.string.error_email_empty));
            resultValidate = false;
        }

        if (FormHelper.isEmpty(email)) {
            emailLayout.setError(getString(R.string.error_email_empty));
            resultValidate = false;
        } else if (!FormHelper.isEmailValid(email)) {
            emailLayout.setError(getString(R.string.error_email_invalid));
            resultValidate = false;
        }

        if (FormHelper.isEmpty(password)) {
            passwordLayout.setError(getString(R.string.error_password_empty));
            resultValidate =  false;
        } else if (!FormHelper.isPasswordValid(password)) {
            passwordLayout.setError(getString(R.string.error_password_invalid));
            resultValidate =  false;
        }

        if (FormHelper.isEmpty(confirmPassword)) {
            confirmPasswordLayout.setError(getString(R.string.error_password_empty));
            resultValidate =  false;
        } else if (!password.equals(confirmPassword)){
            confirmPasswordLayout.setError(getString(R.string.error_password_different));
            resultValidate =  false;
        }

        return resultValidate;
    }

    private void createAccount(final String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm(nameField.getText().toString(), email, password, confirmPasswordField.getText().toString())) {
            return;
        }

        showProgressDialog();

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser firebaseUser = auth.getCurrentUser();

                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }

                        hideProgressDialog();
                    }
                });
    }

    @OnClick(R.id.sign_up_button)
    public void signInOnClick(View view) {
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        System.out.println(email);
        System.out.println(password);

        createAccount(email, password);
    }

    @OnClick(R.id.back_sign_in_button)
    public void backToSignInOnClick(View view){
        finish();
    }
}
