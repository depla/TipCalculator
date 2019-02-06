package dla.cs134.miracosta.edu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import edu.miracostacollege.cs134.tipcalculator.Bill;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Tip calculator - Calculates tip for a restaurant bill
 * Dennis La
 * CS134
 *
 * Algorithm:
 * 1. Instantiate the views and a Bill object
 * 2. Set the seekbar, tip percent, tip and total textviews to defaults
 * 3. When seekbar is changed: update tipPercent on Bill to the progress, update tip percent,
 * tip amount and total amount text views
 * 4. When the edit text is changed: update Bill and then update the tip amount and total amount
 * text views
 */

public class MainActivity extends AppCompatActivity {

    //member variables to format as currency or percent (NumberFormat)
    NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.getDefault());
    NumberFormat percent = NumberFormat.getPercentInstance(Locale.getDefault());

    private EditText amountEditText;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;
    private SeekBar percentSeekBar;

    //member variables for our model
    private Bill currentBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize all member variables in onCreate
        amountEditText = findViewById(R.id.amountEditText);
        percentTextView = findViewById(R.id.percentTextView);
        tipTextView = findViewById(R.id.tipTextView);
        totalTextView = findViewById(R.id.totalTextView);
        percentSeekBar = findViewById(R.id.percentSeekBar);

        //init our model
        currentBill = new Bill();

        //set the tip percent to match the seek bar
        currentBill.setTipPercent(percentSeekBar.getProgress() / 100.0);
        //update text view for percent tip
        percentTextView.setText(percent.format(currentBill.getTipPercent()));

        //make tip and total text view show 0.0
        tipTextView.setText(currency.format(currentBill.getTipAmount()));
        totalTextView.setText(currency.format(currentBill.getTotalAmount()));


        //connect code to the event on onprogressedchanged for seekbar
        percentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                //update model
                currentBill.setTipPercent(progress / 100.0);

                //change the label of the tip percent, tip and total amount text views
                percentTextView.setText(percent.format(currentBill.getTipPercent()));
                tipTextView.setText(currency.format(currentBill.getTipAmount()));
                totalTextView.setText(currency.format(currentBill.getTotalAmount()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //do nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //do nothing
            }
        });


        //connect code to the event onTextChanged
        amountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //update model currentBill
                if (amountEditText.getText().toString().length() > 0)
                {
                    currentBill.setAmount(Double.parseDouble(amountEditText.getText().toString()));
                }
                else
                {
                    currentBill.setAmount(0.0);
                }


                //update all the text views
                tipTextView.setText(currency.format(currentBill.getTipAmount()));
                totalTextView.setText(currency.format(currentBill.getTotalAmount()));
            }

            @Override
            public void afterTextChanged(Editable s) {
                //do nothing
            }
        });
    }
}
