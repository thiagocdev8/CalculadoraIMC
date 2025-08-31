package com.example.calculadoraimc;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });

        EditText editTextWeight = findViewById(R.id.edittext_weight);
        SeekBar seekBarHeight = findViewById(R.id.seekbar_height);
        Button buttonClear = findViewById(R.id.button_clear);
        Button buttonCalculate = findViewById(R.id.button_calculate);
        TextView textViewHeight = findViewById(R.id.textview_height);
        TextView textViewResult = findViewById(R.id.textview_result);


        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextWeight.setText("");
                seekBarHeight.setProgress(0);
                textViewResult.setText("");
                textViewResult.setVisibility(View.GONE);
                textViewHeight.setVisibility(View.GONE);
            }
        });

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weightText = editTextWeight.getText().toString();
                double height = (double)seekBarHeight.getProgress() / 100; // /100 to convert from cm to m

                double weight = Double.parseDouble(weightText);

                // IMC : weight / (height * height) in meter
                double result = weight / (height * height);
                textViewResult.setText(String.format(Locale.getDefault(), "IMC: %.2f", result));
                textViewResult.setVisibility(View.VISIBLE);
            }
        });

        seekBarHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewHeight.setText(progress + " cm");
                textViewHeight.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });



    }
}