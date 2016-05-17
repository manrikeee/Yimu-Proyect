package com.example.mk.yimu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class SignIn extends AppCompatActivity {
     EditText  nombre;
   EditText password ;
     EditText direccion,email;
     EditText fecha ;
    RadioButton r1,r2;
    String tipo;
   WebService.ObtenerWebService hiloconexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_registrar);
        nombre = (EditText)findViewById(R.id.nombre);
        password = (EditText)findViewById(R.id.password);
       direccion = (EditText)findViewById(R.id.direccion);
        fecha = (EditText)findViewById(R.id.fecha);
        email = (EditText)findViewById(R.id.email);
        //foto = (TextView)findViewById(R.id.foto);
        //estado = (EditText)findViewById(R.id.estado);
        r1=(RadioButton)findViewById(R.id.r1);
        r2=(RadioButton)findViewById(R.id.r2);



        String IP = "http://10.0.2.2";
       final String INSERT = IP + "/webservice/insertar_usuario.php";
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button registrar = (Button) findViewById(R.id.registrar);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (r2.isChecked()) {
                    tipo = "normal";
                }
                if (r1.isChecked()) {
                    tipo = "profesor";
                }

                hiloconexion = new WebService.ObtenerWebService();
                hiloconexion.execute(INSERT,"3",nombre.getText().toString(),password.getText().toString(),direccion.getText().toString(),fecha.getText().toString(),email.getText().toString(),"1",tipo);
                Intent intent = new Intent(SignIn.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
