package com.example.carcompany;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.carcompany.basededatos.AdminSQLite;
import com.example.carcompany.process.Empleado;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    ActionBar actionBar;


    private TextInputEditText userField;
    private TextInputEditText passField;

    public void claveRecover(View view){
        Intent intent = new Intent(Login.this, RecuperarClave.class);
        startActivity(intent);
    }

    public void initLoginButton(View view){

        userField = (TextInputEditText) findViewById(R.id.userField);
        passField = (TextInputEditText) findViewById(R.id.passField);



        String username = userField.getText().toString();
        String password = passField.getText().toString();

        try {
            if (verificarCamposVacios(username, password)){

                Toast.makeText(this, username, Toast.LENGTH_SHORT).show();
                try {
                    AdminSQLite admin = new AdminSQLite(Login.this, "UsuariosEmpleados", null, 1);
                    SQLiteDatabase data = admin.getWritableDatabase();
                    /*@SuppressLint("Recycle") */
                    @SuppressLint("Recycle") Cursor cursor  = data.rawQuery("select * from user_employee where usuario = '"+username+"' and clave = "+password, null);

                    if (cursor.moveToFirst()){
                        Empleado.nombres = cursor.getString(2);
                        Empleado.apellidos = cursor.getString(3);
                        Empleado.credencial = cursor.getString(1);
                        Log.i("-", cursor.getString(6));
                        //Toast.makeText(this, ""+Empleado.credencial, Toast.LENGTH_SHORT).show();
                        data.close();
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(this, "Usuario o clave incorrectos", Toast.LENGTH_SHORT).show();
                    }



                }catch (Exception e){
                    Toast.makeText(this, "error: "+e.toString(), Toast.LENGTH_SHORT).show();
                    Log.i("error: ", e.toString());
                }

            }else{
                Toast.makeText(this, "Los campos no pueden estar vacios", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Error by: "+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickCrateAccount(View view){
        Intent intent = new Intent(Login.this, Registro.class);
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        actionBar  = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#273036")));
        actionBar.setTitle("CAR SERVICES");
    }

    public boolean verificarCamposVacios(String user, String clave){
        return !user.equals("")&&!clave.equals("");
    }
}