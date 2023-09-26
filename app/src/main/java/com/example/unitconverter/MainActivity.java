package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//    1m = 39.3701in
    private EditText enterMeters;
    private TextView resultEditText;
    private TextView currentConv;
    private Button convertButton;
    private Button mToI;
    private Button kgToLbs;
    private Button swapper;
    private int convId = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        convertButton = (Button) findViewById(R.id.convertId);
        resultEditText = (TextView) findViewById(R.id.resultId);
        currentConv = (TextView) findViewById(R.id.currentConv);
        enterMeters = (EditText) findViewById(R.id.metersEditText);
        mToI = (Button) findViewById(R.id.mToIButton);
        kgToLbs = (Button) findViewById(R.id.kgToLbsButton);
        swapper = (Button) findViewById(R.id.swapper);

        // applying default conversion settings (inches to meters)
        convertButton.setText(R.string.button_name);
        enterMeters.setHint("Enter a Number (m)");

        // mapping each setting to an array index
        final String[] unit = {"inches", "meters", "lbs", "kg"};
        final double[] multiplier = {39.37, 0.0254, 2.20462, 0.453592};
        final String[] hint = {" (m)", " (in)", " (kg)", " (lbs)"};
        final String[] disp = {"Meters to Inches", "Inches to Meters", "Kgs to lbs", "lbs to Kgs"};



        mToI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //assign index respective to setting (meters to inches)
                convId = 0;
                //change hint unit
                chngHint(" (m)");
                //change conversion display
                currentConv.setText("Meters to Inches");
            }
        });

        kgToLbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //assign index respective to setting (kg to lbs)
                convId = 2;
                //change hint unit
                chngHint(" (Kg)");
                //change conversion display
                currentConv.setText("Kgs to lbs");
            }
        });

        //switch between conversion pairs ("m to i and i to m" and "kg to lbs and lbs to kg")
        swapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //(convId == 0)||(convId == 2)
                if ((convId%2)==0) {
                    convId += 1;
                }
                else {
                    convId -= 1;
                }
                //apply changes
                chngHint(hint[convId]);
                currentConv.setText(disp[convId]);
            }
        });

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double result = 0.0;

                if (enterMeters.getText().toString().equals("")) {
                    resultEditText.setText(R.string.error_message);
                    resultEditText.setTextColor(Color.RED);
                }
                else {
                    double meterValue = Double.parseDouble(enterMeters.getText().toString());
                    resultEditText.setTextColor(Color.DKGRAY);
                    result = meterValue* multiplier[convId];
                    resultEditText.setText(String.format("%.2f", result)+" "+unit[convId]);
                }
            }
        });

    }
    private void chngHint(String i) {
        enterMeters.setHint("Enter a Number" + i);
    }
}