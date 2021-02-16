package com.example.carcompany;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.carcompany.basededatos.AdminSQLite;
import com.google.android.material.textfield.TextInputEditText;

public class RecuperarClave extends AppCompatActivity {

    private TextInputEditText cedulaRecover;
    private TextInputEditText claveRecover;
    private TextInputEditText claveRecover2;

    public void onRecoverPassword(View view){

        String cedula = cedulaRecover.getText().toString();
        String clave = claveRecover.getText().toString();
        String clave2 = claveRecover2.getText().toString();

        if (verificarCamposVacios(cedula, clave, clave2)){
            if (clave.equals(clave2)){
                AdminSQLite admin = new AdminSQLite(this, "UsuariosEmpleados", null, 1);
                SQLiteDatabase data = admin.getWritableDatabase();

                ContentValues register = new ContentValues();
                register.put("clave", clave);
                register.put("cedula", cedula);

                int cantidad = data.update("user_employee", register,"cedula='"+cedula+"'",null);
                data.close();


                if (cantidad == 1){
                    Intent intent = new Intent(RecuperarClave.this, Login.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "No se pudo actualizar", Toast.LENGTH_SHORT).show();
                }
                //@SuppressLint("Recycle") Cursor cursor  = data.rawQuery("update user_employee set clave='"+clave+"' where cedula = '"+cedula+"'", null);





            }else{
                Toast.makeText(this, "los campos de las claves deben coincidir", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Los campos de recuperación no pueden estar vacios", Toast.LENGTH_SHORT).show();
        }



    }

    public void onCancelButton (View view){
        Intent intent = new Intent(RecuperarClave.this, Login.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_clave);

        cedulaRecover = (TextInputEditText) findViewById(R.id.cedulaRecover);
        claveRecover = (TextInputEditText) findViewById(R.id.claveRecover);
        claveRecover2 = (TextInputEditText) findViewById(R.id.claveRecover2);
    }

    public boolean verificarCamposVacios(String cedula, String clave, String clave2){
        return !cedula.equals("")&&!clave.equals("")&&!clave2.equals("");
    }

    public void ClearFields(TextInputEditText cedulaRecover, TextInputEditText claveRecover, TextInputEditText claveRecover2){
        cedulaRecover.setText("");
        claveRecover.setText("");
        claveRecover2.setText("");
    }


}