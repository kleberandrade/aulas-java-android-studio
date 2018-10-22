package br.fatec.aula.interestcalculatorapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateHelper {

    public static final String DATE_FORMAT = "dd/MM/yyyy";

    private DateHelper() {

    }

    public static String getStringFromDate(Date date) {
        Locale locale = new Locale("pt", "BR");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", locale);
        return dateFormat.format(date);
    }

    public static Date getDateFromString(String dateText) {
        Locale locale = new Locale("pt", "BR");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", locale);

        try {
            return dateFormat.parse(dateText);
        } catch (ParseException e) {
            return null;
        }
    }
}
