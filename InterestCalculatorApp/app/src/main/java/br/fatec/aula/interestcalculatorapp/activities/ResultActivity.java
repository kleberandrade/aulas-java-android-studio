package br.fatec.aula.interestcalculatorapp.activities;

import android.content.Intent;
import android.icu.util.DateInterval;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import br.fatec.aula.interestcalculatorapp.R;
import br.fatec.aula.interestcalculatorapp.models.Bill;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultActivity extends AppCompatActivity {

    @BindView(R.id.value_to_pay)
    TextView valueToPayTextView;

    @BindView(R.id.due_date)
    TextView dueDateTextView;

    @BindView(R.id.payment_date)
    TextView paymentDateTextView;

    @BindView(R.id.interest)
    TextView interestTextView;

    @BindView(R.id.fine)
    TextView fineTextView;

    @BindView(R.id.total_value_to_pay)
    TextView totalTextView;

    @BindView(R.id.dif_value_to_pay)
    TextView diffTextView;

    private NumberFormat numberFormat;

    private SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));

        setTextViewFromIntent();
    }

    public void setTextViewFromIntent() {
        Intent intent = getIntent();
        Bill bill = (Bill) intent.getSerializableExtra(Bill.EXTRA_BILL);

        valueToPayTextView.setText(numberFormat.format(bill.getValue()));
        dueDateTextView.setText(dateFormat.format(bill.getDueDate()));
        paymentDateTextView.setText(dateFormat.format(bill.getPaymentDate()));
        if (!bill.isFineCurrency())
            interestTextView.setText(String.valueOf(bill.getInterest()) + "%");
        else
            interestTextView.setText(numberFormat.format(bill.getInterest()));

        if (!bill.isInterestCurrency())
            fineTextView.setText(String.valueOf(bill.getFine()) + "%");
        else
            fineTextView.setText(numberFormat.format(bill.getFine()));

        setOutputs(bill);
    }

    public void setOutputs(Bill bill) {

        Date dueDate = bill.getDueDate();
        Date paymentDate = bill.getPaymentDate();

        long diffInMillies = paymentDate.getTime() - dueDate.getTime();
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        if (diff <= 0) {
            totalTextView.setText(String.valueOf(bill.getValue()));
            diffTextView.setText("0");
            return;
        }

        double value = bill.getValue();
        double interest = bill.getInterest();
        if (!bill.isInterestCurrency()){
            interest = (interest / 100.0) * value;
        }

        if (bill.isInterestDay()) {
           interest *= diff;
        } else {
            interest *= (diff / 30.0);
        }

        double fine = bill.getFine();
        if (!bill.isFineCurrency()){
            fine = (fine / 100.0) * value;
        }

        double totalValueToPay = value + fine + interest;
        totalTextView.setText(numberFormat.format(totalValueToPay));
        diffTextView.setText(numberFormat.format(totalValueToPay - value));
    }


    @OnClick(R.id.back_button)
    public void backOnClickButton(View view) {
        this.onBackPressed();
    }

}
