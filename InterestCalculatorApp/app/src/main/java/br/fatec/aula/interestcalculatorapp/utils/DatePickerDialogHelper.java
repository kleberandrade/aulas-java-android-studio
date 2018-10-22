package br.fatec.aula.interestcalculatorapp.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class DatePickerDialogHelper {

    private DatePickerDialogHelper() {
    }

    public static void setDatePickerDialog(final Context context, View view, final SimpleDateFormat dateFormatter) {

        final EditText dateEditText = (EditText) view;

        dateEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    dateEditText.callOnClick();
                }
            }
        });

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();

                if (!TextUtils.isEmpty(dateEditText.getText().toString())) {
                    try {
                        Date date = dateFormatter.parse(dateEditText.getText().toString());
                        calendar.setTime(date);
                    } catch (ParseException e) {
                        Log.e("DATE PICKER", e.getMessage());
                    }
                }

                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog picker = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        calendar.set(year, month, day);
                        String date = dateFormatter.format(calendar.getTime());
                        dateEditText.setText(date);

                    }
                }, year, month, day);

                picker.show();
            }
        });
    }

}
