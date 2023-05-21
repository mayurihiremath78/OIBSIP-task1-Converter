package com.example.oasis_converter_task1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private EditText inputEditText, outputEditText;
    private Spinner sourceSpinner, targetSpinner;
    private Button convertButton;

    private String[] units = {"Meter", "Kilometer", "Foot", "Mile"};
    private double[] factors = {1.0, 0.001, 3.281, 0.0006214};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        inputEditText = findViewById(R.id.input_edit_text);
        outputEditText = findViewById(R.id.output_edit_text);
        sourceSpinner = findViewById(R.id.source_spinner);
        targetSpinner = findViewById(R.id.target_spinner);
        convertButton = findViewById(R.id.convert_button);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sourceSpinner.setAdapter(adapter);
        targetSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertValue();
            }
        });

}

    private void convertValue() {
        String inputValueString = inputEditText.getText().toString();
        if (inputValueString.isEmpty()) {
            return;
    }

        double inputValue = Double.parseDouble(inputValueString);

        String sourceUnit = sourceSpinner.getSelectedItem().toString();
        String targetUnit = targetSpinner.getSelectedItem().toString();
        int sourceIndex = Arrays.asList(units).indexOf(sourceUnit);
        int targetIndex = Arrays.asList(units).indexOf(targetUnit);
        double factor = factors[targetIndex] / factors[sourceIndex];

        double outputValue = inputValue * factor;

        outputEditText.setText(String.format(Locale.getDefault(), "%.2f", outputValue));
    }
}