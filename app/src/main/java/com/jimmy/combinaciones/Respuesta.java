package com.jimmy.combinaciones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Respuesta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respuesta);
        Bundle extras = getIntent().getExtras();
        String tipo = "";
        TextView txtTipo = (TextView)findViewById(R.id.txtTipo);
        TextView txtRespuesta = (TextView)findViewById(R.id.txtRespuesta);
        double respueta = 0.0;
        if (extras == null){
            tipo = "Ocurrió un error";
            respueta = 0;
        }else{
            tipo = extras.getString("tipo");
            respueta = extras.getDouble("respuesta");
        }
        txtTipo.setText(tipo);
        txtRespuesta.setText(String.valueOf(respueta));
    }
}
