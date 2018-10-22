package br.fatec.aula.interestcalculatorapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import br.fatec.aula.interestcalculatorapp.R;
import br.fatec.aula.interestcalculatorapp.models.Bill;
import br.fatec.aula.interestcalculatorapp.utils.DatePickerDialogHelper;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.value_to_pay)
    EditText valueToPayEditText;

    @BindView(R.id.due_date)
    EditText dueDateEditText;

    @BindView(R.id.payment_date)
    EditText paymentDateEditText;

    @BindView(R.id.interest)
    EditText interestEditText;

    @BindView(R.id.fine)
    EditText fineEditText;

    private SimpleDateFormat dateFormat;
    private Bill currentBill;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));

        DatePickerDialogHelper.setDatePickerDialog(this, dueDateEditText, dateFormat);
        DatePickerDialogHelper.setDatePickerDialog(this, paymentDateEditText, dateFormat);

        clearOnClick(null);
    }

    @OnClick(R.id.clear_button)
    public void clearOnClick(View view) {

        currentBill = new Bill();
        currentBill.setInterestCurrency(true);
        currentBill.setInterestDay(true);
        currentBill.setFineCurrency(true);

        RadioButton radioButtonInterestCurrency = findViewById(R.id.interest_currency);
        radioButtonInterestCurrency.setChecked(true);

        RadioButton radioButtonInterestDay = findViewById(R.id.interest_day);
        radioButtonInterestDay.setChecked(true);

        RadioButton radioButtonFineCurrency = findViewById(R.id.fine_currency);
        radioButtonFineCurrency.setChecked(true);

        valueToPayEditText.setText("");
        dueDateEditText.setText("");
        paymentDateEditText.setText("");
        interestEditText.setText("");
        fineEditText.setText("");

        valueToPayEditText.onWindowFocusChanged(true);
    }

    @OnCheckedChanged({R.id.interest_currency, R.id.interest_percentage})
    public void onInterestCurrencyOrPercentageRadioButtonCheckChanged(CompoundButton button, boolean checked) {
        if (checked) {
            switch (button.getId()) {
                case R.id.interest_currency:
                    currentBill.setInterestCurrency(true);
                    break;
                case R.id.interest_percentage:
                    currentBill.setInterestCurrency(false);
                    break;
            }
        }
    }

    @OnCheckedChanged({R.id.interest_day, R.id.interest_month})
    public void onInterestDayOrMonthRadioButtonCheckChanged(CompoundButton button, boolean checked) {
        if (checked) {
            switch (button.getId()) {
                case R.id.interest_day:
                    currentBill.setInterestDay(true);
                    break;
                case R.id.interest_month:
                    currentBill.setInterestDay(false);
                    break;
            }
        }
    }

    @OnCheckedChanged({R.id.interest_day, R.id.interest_month})
    public void onFineCurrencyOrPercentageButtonCheckChanged(CompoundButton button, boolean checked) {
        if (checked) {
            switch (button.getId()) {
                case R.id.fine_currency:
                    currentBill.setFineCurrency(true);
                    break;
                case R.id.fine_percentage:
                    currentBill.setFineCurrency(false);
                    break;
            }
        }
    }

    @OnClick(R.id.calculator_button)
    public void evaluateOnClick(View view) {
        try {
            currentBill.setValue(Double.valueOf(valueToPayEditText.getText().toString()));
            currentBill.setDueDate(dateFormat.parse(dueDateEditText.getText().toString()));
            currentBill.setPaymentDate(dateFormat.parse(paymentDateEditText.getText().toString()));
            currentBill.setInterest(Double.valueOf(interestEditText.getText().toString()));
            currentBill.setFine(Double.valueOf(fineEditText.getText().toString()));

            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra(Bill.EXTRA_BILL, currentBill);
            startActivity(intent);
        } catch (ParseException e) {
            Log.e("BILL", e.getMessage());
        }
    }
}
