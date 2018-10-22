package br.fatec.aula.fatecchatapp.utils;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormHelper {

    private FormHelper(){}

    public static boolean isEmailValid(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isPasswordValid(String password){
        return password.length() >= 6;
    }

    public static boolean isEmpty(String text){
        return TextUtils.isEmpty(text);
    }
}
