package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private StringBuilder input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        input = new StringBuilder();

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        setButtonClickListeners(gridLayout);
    }

    private void setButtonClickListeners(GridLayout gridLayout) {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View child = gridLayout.getChildAt(i);
            if (child instanceof Button) {
                Button button = (Button) child;
                button.setOnClickListener(view -> handleButtonClick(button.getText().toString()));
            }
        }
    }

    private void handleButtonClick(String buttonText) {
        switch (buttonText) {
            case "=":
                try {
                    double result = evaluateExpression(input.toString());
                    display.setText(String.valueOf(result));
                    input.setLength(0);
                    input.append(result);
                } catch (Exception e) {
                    display.setText("Error");
                    input.setLength(0);
                }
                break;

            case "C":
                input.setLength(0);
                display.setText("");
                break;

            default:
                input.append(buttonText);
                display.setText(input.toString());
                break;
        }
    }

    private double evaluateExpression(String expression) {
        return new Calculator().evaluate(expression);
    }
}
