package com.jimmy.combinaciones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BayesActivity extends AppCompatActivity {
    EditText numero1;
    EditText numero2;
    EditText numero3;
    EditText numero4;
    EditText numero5;
    EditText numero6;
    EditText numero7;
    EditText numero8;
    EditText numero9;
    EditText numero10;
    EditText numero11;
    EditText numero12;
    float[] nums = {0, 0, 0, 0, 0, 0, 0, 0};
    float n1, n2, n3, n4, d;
    TextView txtResult1;
    TextView txtResult2;
    TextView txtResult3;
    TextView txtResult4;
    Button btnCalcularB;
    Button btnCalcularNoB;
    Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayes);
        initializeElements();
        btnCalcularB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nums = fillArray();
                boolean result = suma1(nums[0], nums[1], nums[2], nums[3]);
                if (result) {
                    n1 = nums[0] * nums[4];
                    n2 = nums[1] * nums[5];
                    n3 = nums[2] * nums[6];
                    n4 = nums[3] * nums[7];
                    d = n1 + n2 + n3 + n4;
                    numero9.setText(String.valueOf(Math.round(10000 * n1 / d) / 10000.0f));
                    numero10.setText(String.valueOf(Math.round(10000 * n2 / d) / 10000.0f));
                    numero11.setText(String.valueOf(Math.round(10000 * n3 / d) / 10000.0f));
                    numero12.setText(String.valueOf(Math.round(10000 * n4 / d) / 10000.0f));
                    txtResult1.setText("Pr(A1/B):");
                    txtResult2.setText("Pr(A2/B):");
                    txtResult3.setText("Pr(A3/B):");
                    txtResult4.setText("Pr(A4/B):");
                }
            }
        });
        btnCalcularNoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nums = fillArray();
                n1 = nums[0] * (1 - nums[4]);
                n2 = nums[1] * (1 - nums[5]);
                n3 = nums[2] * (1 - nums[6]);
                n4 = nums[3] * (1 - nums[7]);
                d = n1 + n2 + n3 + n4;
                numero9.setText(String.valueOf(Math.round(10000 * n1 / d) / 10000.0f));
                numero10.setText(String.valueOf(Math.round(10000 * n2 / d) / 10000.0f));
                numero11.setText(String.valueOf(Math.round(10000 * n3 / d) / 10000.0f));
                numero12.setText(String.valueOf(Math.round(10000 * n4 / d) / 10000.0f));
                txtResult1.setText("Pr(A1/noB):");
                txtResult2.setText("Pr(A1/noB):");
                txtResult3.setText("Pr(A1/noB):");
                txtResult4.setText("Pr(A1/noB):");
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero1.setText("0.0");
                numero2.setText("0.0");
                numero3.setText("0.0");
                numero4.setText("0.0");
                numero5.setText("0.0");
                numero6.setText("0.0");
                numero7.setText("0.0");
                numero8.setText("0.0");
                numero9.setText("0.0");
                numero10.setText("0.0");
                numero11.setText("0.0");
                numero12.setText("0.0");
                txtResult1.setText("");
                txtResult2.setText("");
                txtResult3.setText("");
                txtResult4.setText("");

            }
        });
    }

    private void initializeElements() {
        numero1 = findViewById(R.id.numero1);
        numero2 = findViewById(R.id.numero2);
        numero3 = findViewById(R.id.numero3);
        numero4 = findViewById(R.id.numero4);
        numero5 = findViewById(R.id.numero5);
        numero6 = findViewById(R.id.numero6);
        numero7 = findViewById(R.id.numero7);
        numero8 = findViewById(R.id.numero8);
        numero9 = findViewById(R.id.numero9);
        numero10 = findViewById(R.id.numero10);
        numero11 = findViewById(R.id.numero11);
        numero12 = findViewById(R.id.numero12);
        btnCalcularB = findViewById(R.id.btnCalcularB);
        btnCalcularNoB = findViewById(R.id.btnCalcularNoB);
        txtResult1 = findViewById(R.id.txtResult1);
        txtResult2 = findViewById(R.id.txtResult2);
        txtResult3 = findViewById(R.id.txtResult3);
        txtResult4 = findViewById(R.id.txtResult4);
        btnClear = findViewById(R.id.btnClear);
    }

    private boolean suma1(float numero1, float numero2, float numero3, float numero4) {
        if ((numero1 + numero2 + numero3 + numero4) != 1) {
            Toast.makeText(this, "la suma de AI debe dar 1", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private float[] fillArray() {
        float[] nums = {0, 0, 0, 0, 0, 0, 0, 0};
        nums[0] = Float.parseFloat(numero1.getText().toString());
        nums[1] = Float.parseFloat(numero2.getText().toString());
        nums[2] = Float.parseFloat(numero3.getText().toString());
        nums[3] = Float.parseFloat(numero4.getText().toString());
        nums[4] = Float.parseFloat(numero5.getText().toString());
        nums[5] = Float.parseFloat(numero6.getText().toString());
        nums[6] = Float.parseFloat(numero7.getText().toString());
        nums[7] = Float.parseFloat(numero8.getText().toString());
        return nums;
    }
}
