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

        //set the tip percent to match the seek br
        currentBill.setTipPercent(percentSeekBar.getProgress() / 100.0);

        //make tip and total text view show 0.0
        tipTextView.setText(currency.format(currentBill.getTipAmount()));
        totalTextView.setText(currency.format(currentBill.getTotalAmount()));


        //connect code to the event on onprogressedchanged for seekbar
        percentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //change the label of the tip percent
                percentTextView.setText(percent.format(progress / 100.0));

                //update model
                currentBill.setTipPercent(progress / 100.0);

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
