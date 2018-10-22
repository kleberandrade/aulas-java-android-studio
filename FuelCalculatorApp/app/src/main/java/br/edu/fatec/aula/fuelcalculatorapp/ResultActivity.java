package br.edu.fatec.aula.fuelcalculatorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();

        String etanolPrice = intent.getStringExtra(MainActivity.EXTRA_ETANOL_PRICE);
        String gasPrice = intent.getStringExtra(MainActivity.EXTRA_GAS_PRICE);
        String etanolMilage = intent.getStringExtra(MainActivity.EXTRA_ETANOL_MILAGE);
        String gasMilage = intent.getStringExtra(MainActivity.EXTRA_GAS_MILAGE);

        TextView etanolPriceTextView = findViewById(R.id.etanol_price_result);
        TextView gasPriceTextView = findViewById(R.id.gas_price_result);
        TextView etanolMilageTextView = findViewById(R.id.etanol_milage_result);
        TextView gasMilageTextView = findViewById(R.id.gas_milage_result);

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        double etanol = Double.parseDouble(etanolPrice);
        double gas = Double.parseDouble(gasPrice);

        etanolPriceTextView.setText(currencyFormat.format(etanol));
        gasPriceTextView.setText(currencyFormat.format(gas));
        etanolMilageTextView.setText(etanolMilage + " " + getString(R.string.km_litro));
        gasMilageTextView.setText(gasMilage  + " " + getString(R.string.km_litro));

        TextView rateTextView = findViewById(R.id.rate);
        TextView spentEthanolTextView = findViewById(R.id.ethanol_spent);
        TextView spentGasTextView = findViewById(R.id.gas_spent);


        double rate = etanol / gas * 100;
        double spentEthanol = etanol / Double.valueOf(etanolMilage);
        double spentGas = gas / Double.valueOf(gasMilage);

        Log.v("VALOR", String.valueOf(spentEthanol));
        Log.v("VALOR", String.valueOf(spentGas));

        rateTextView.setText(String.format("%.2f %%", rate));
        spentEthanolTextView.setText(currencyFormat.format(spentEthanol));
        spentGasTextView.setText(currencyFormat.format(spentGas));

        TextView bestFuelTextView = findViewById(R.id.best_fuel);
        TextView fuelSavingTextView = findViewById(R.id.fuel_saving);

        if (spentEthanol < spentGas)
            bestFuelTextView.setText("ABASTEÇA COM ETANOL");
        else
            bestFuelTextView.setText("ABASTEÇA COM GASOLINA");

        double fuelSaving = Math.abs(spentEthanol - spentGas);
        fuelSavingTextView.setText("Economia de " + currencyFormat.format(fuelSaving) + " / Litro");

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ResultActivity.super.onBackPressed();

            }
        });
    }
}
