package br.edu.fatec.aula.fuelcalculatorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_ETANOL_PRICE = "ETANOL_PRICE";
    public static final String EXTRA_GAS_PRICE = "GAS_PRICE";
    public static final String EXTRA_ETANOL_MILAGE = "ETANOL_MILAGE";
    public static final String EXTRA_GAS_MILAGE = "GAS_MILAGE";

    private EditText etanolPriceEditText;
    private EditText gasPriceEditText;
    private EditText etanolMilageEditText;
    private EditText gasMilagePriceEditText;
    private Button calculatorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etanolPriceEditText = findViewById(R.id.etanol_price_edit_text);
        etanolMilageEditText = findViewById(R.id.etanol_milage_edit_text);

        gasPriceEditText = findViewById(R.id.gas_price_edit_text);
        gasMilagePriceEditText = findViewById(R.id.gas_milage_edit_text);

        calculatorButton = findViewById(R.id.calculator_button);
        calculatorButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateFields()) {
                    changeToResultScreen();
                }
            }
        });
    }

    private void changeToResultScreen() {
        Intent intent = new Intent(this, ResultActivity.class);

        EditText etanolPrice = (EditText) findViewById(R.id.etanol_price_edit_text);
        EditText gasPrice = (EditText) findViewById(R.id.gas_price_edit_text);
        EditText etanolMilage = (EditText) findViewById(R.id.etanol_milage_edit_text);
        EditText gasMilage = (EditText) findViewById(R.id.gas_milage_edit_text);

        intent.putExtra(EXTRA_ETANOL_PRICE, etanolPrice.getText().toString());
        intent.putExtra(EXTRA_GAS_PRICE, gasPrice.getText().toString());
        intent.putExtra(EXTRA_ETANOL_MILAGE, etanolMilage.getText().toString());
        intent.putExtra(EXTRA_GAS_MILAGE, gasMilage.getText().toString());

        startActivity(intent);
    }

    private boolean validateFields() {

        boolean correctValidation = true;

        if (etanolPriceEditText.getText().toString().length() == 0) {
            etanolPriceEditText.setError(getString(R.string.erro_price_etanol));
            correctValidation = false;
        }

        if (gasPriceEditText.getText().toString().length() == 0) {
            gasPriceEditText.setError(getString(R.string.erro_price_gas));
            correctValidation = false;
        }

        if (etanolMilageEditText.getText().toString().length() == 0) {
            etanolMilageEditText.setError(getString(R.string.erro_milage_etanol));
            correctValidation = false;
        }

        if (gasMilagePriceEditText.getText().toString().length() == 0) {
            gasMilagePriceEditText.setError(getString(R.string.erro_milage_gas));
            correctValidation = false;
        }

        return correctValidation;
    }
}
