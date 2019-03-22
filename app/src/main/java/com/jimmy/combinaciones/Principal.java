package com.jimmy.combinaciones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        final Button btnCalcular = (Button) findViewById(R.id.btnCalcular);
        final CheckBox checkET = (CheckBox) findViewById(R.id.checkET);
        final CheckBox checkIO = (CheckBox) findViewById(R.id.checkIO);
        final CheckBox checkSR = (CheckBox) findViewById(R.id.checkSR);
        final EditText txtElementos = (EditText) findViewById(R.id.txtElementos);
        final EditText txtLugares = (EditText) findViewById(R.id.txtLugares);
        final LinearLayout ll = (LinearLayout) findViewById(R.id.layout2);
        final Button btnBorrar = (Button) findViewById(R.id.btnBorrar);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCalcular.setEnabled(false);
                String elementos = txtElementos.getText().toString();
                String lugares = txtLugares.getText().toString();
                if (elementos.isEmpty() || lugares.isEmpty()) {
                    Toast.makeText(Principal.this, "Hay campos sin diligenciar", Toast.LENGTH_SHORT).show();
                } else if (Integer.parseInt(elementos) < Integer.parseInt(lugares) && !(checkET.isChecked()&& checkIO.isChecked()&& checkSR.isChecked())) {
                    Toast.makeText(Principal.this, "Los lugares no pueden ser mayor a los elementos", Toast.LENGTH_SHORT).show();
                } else {
                    Tipo tipo = getTipoCombinacion(checkET.isChecked(), checkIO.isChecked(), checkSR.isChecked(), Integer.parseInt(txtElementos.getText().toString()), Integer.parseInt(txtLugares.getText().toString()),ll);
                    Intent respuesta = new Intent(Principal.this, Respuesta.class);
                    respuesta.putExtra("tipo", tipo.getNombre());
                    respuesta.putExtra("respuesta", tipo.getCalculo());
                    startActivity(respuesta);
                }
                btnCalcular.setEnabled(true);
            }
        });
        txtLugares.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && checkET.isChecked() && checkIO.isChecked() && checkSR.isChecked()){
                    Toast.makeText(Principal.this, "Ha seleccionado permutacion con repeteción, por favor digite las demas lugares", Toast.LENGTH_SHORT).show();
                    createEditText(Integer.parseInt(txtLugares.getText().toString()), ll);
                }
            }
        });
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtElementos.setText("");
                txtLugares.setText("");
                checkET.setChecked(false);
                checkIO.setChecked(false);
                checkSR.setChecked(false);
                if (ll.getChildCount() > 0)
                    ll.removeAllViews();
            }
        });
    }

    private Tipo getTipoCombinacion(boolean checkedET, boolean checkIO, boolean checkSR, int elementos, int lugares, LinearLayout ll) {
        Tipo tipo = new Tipo();
        if (checkedET && checkIO && !checkSR) {
            tipo.setNombre("PERMUTACION");
            tipo.setCalculo(getFactorial(elementos));
        } else if (checkedET && checkIO && checkSR) {
            tipo.setNombre("PERMUTACIÓN CON REPETICIÓN");
            tipo.setCalculo(getFactorial(elementos) / foreachEditText(ll));
        } else if (!checkedET && checkIO && !checkSR) {
            tipo.setNombre("VARIACIÓN");
            tipo.setCalculo(getFactorial(elementos) / getFactorial(elementos - lugares));
        } else if (!checkedET && checkIO && checkSR) {
            tipo.setNombre("VARIACIÓN CON REPETICIÓN");
            tipo.setCalculo(Math.pow(elementos, lugares));
        } else if (!checkedET && !checkIO && !checkSR) {
            tipo.setNombre("COMBINACIÓN");
            tipo.setCalculo(getFactorial(elementos) / (getFactorial(lugares) * getFactorial(elementos - lugares)));
        } else if (!checkedET && !checkIO && checkSR) {
            tipo.setNombre("COMBINACIÓN CON REPETICIÓN");
            tipo.setCalculo(getFactorial(elementos + lugares - 1) / (getFactorial(lugares) * getFactorial(elementos - lugares)));
        } else {
            tipo.setNombre("NO ES POSIBLE EVALUAR LA EXPRESIÓN");
            tipo.setCalculo(0);
        }
        return tipo;
    }

    private int getFactorial(int n) {
        if (n == 0)
            return 1;
        return n * getFactorial(n - 1);
    }

    private void createEditText(int lugares, LinearLayout ll) {
        if (ll.getChildCount() > 0)
            ll.removeAllViews();
        for (int i = 1; i <= lugares; i++) {
            EditText editText = new EditText(this);
            TextView textView = new TextView(this);
            editText.setId(i);
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setText(((char) (i + 96)) + ": ");
            editText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            ll.addView(textView);
            ll.addView(editText);
            Log.d("", "createEditText: " + i);
        }
    }

    private double foreachEditText(LinearLayout ll) {
        ArrayList<EditText> myEditTextList = new ArrayList<EditText>();
        double acumulador = 0;
        for (int i = 0; i < ll.getChildCount(); i++){
            if (ll.getChildAt(i) instanceof EditText){
                myEditTextList.add((EditText) ll.getChildAt(i));
                int valor = Integer.parseInt(((EditText) ll.getChildAt(i)).getText().toString());
                if (valor == 0){
                    Toast.makeText(this, "hay campos sin informacion", Toast.LENGTH_SHORT).show();
                    return 0;
                }else{
                    acumulador += getFactorial(valor);
                }
            }
        }
        return acumulador;
    }
}
