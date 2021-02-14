package com.example.carcompany;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.carcompany.process.Credential;
import com.example.carcompany.process.ListControl;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Registro extends AppCompatActivity {


    private TextInputEditText empleadoCedula;
    private TextInputEditText nombresEmpleado;
    private TextInputEditText empleadoApellido;
    private TextInputEditText empleadoCorreo;
    private TextInputEditText empleadoCelular;
    private TextInputEditText empleadoUsuario;
    private TextInputEditText empleadoClave;
    private TextInputEditText empleadoClave2;
    private TextInputEditText empleadoLatitud;
    private TextInputEditText empleadoLongitud;
    private MaterialButton getLocation;

    private Credential credential = null;
    private LocationManager locationManager;
    private LocationListener locationListener;

    Geocoder geocoder;

    public void onSaveButton(View view) {
        String cedula = empleadoCedula.getText().toString();
        String nombres = nombresEmpleado.getText().toString();
        String apellidos = empleadoApellido.getText().toString();
        String correo = empleadoCorreo.getText().toString();
        String celular = empleadoCelular.getText().toString();
        String usuario = empleadoUsuario.getText().toString();
        String clave = empleadoClave.getText().toString();
        String clave2 = empleadoClave2.getText().toString();

        if (credential.verificarCedula(cedula)) {
            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Cedula incorrecta, ingrese una cedula correcta", Toast.LENGTH_SHORT).show();
        }

    }

    public void onCancelButton(View view) {

        Intent intent = new Intent(Registro.this, Login.class);
        startActivity(intent);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        empleadoCedula = (TextInputEditText) findViewById(R.id.empleadoCedula);
        nombresEmpleado = (TextInputEditText) findViewById(R.id.nombresEmpleado);
        empleadoApellido = (TextInputEditText) findViewById(R.id.empleadoApellido);
        empleadoCorreo = (TextInputEditText) findViewById(R.id.empleadoCorreo);
        empleadoCelular = (TextInputEditText) findViewById(R.id.empleadoCelular);
        empleadoUsuario = (TextInputEditText) findViewById(R.id.empleadoUsuario);
        empleadoClave = (TextInputEditText) findViewById(R.id.empleadoClave);
        empleadoClave2 = (TextInputEditText) findViewById(R.id.empleadoClave2);
        empleadoLatitud = (TextInputEditText) findViewById(R.id.empleadoLatitud);
        empleadoLongitud = (TextInputEditText) findViewById(R.id.empleadoLongitud);
        getLocation = (MaterialButton) findViewById(R.id.getLocation);




    }


    public boolean chequearCamposVacios(String empleadoCedula, String nombresEmpleado,
                                        String empleadoApellido, String empleadoCorreo, String empleadoCelular, String empleadoUsuario,
                                        String empleadoClave, String empleadoClave2, String empleadoLatitud, String empleadoLongitud){


        return !empleadoCedula.equals("")&&!nombresEmpleado.equals("")&&!empleadoApellido.equals("")&&!empleadoCorreo.equals("")&&!empleadoCelular.equals("")&&
                !empleadoUsuario.equals("")&&!empleadoClave.equals("")&&!empleadoClave2.equals("")&&empleadoLatitud.equals("")&&empleadoLongitud.equals("");
    }

    public void limpiarCampos(TextInputEditText empleadoCedula, TextInputEditText nombresEmpleado, TextInputEditText empleadoApellido,
                              TextInputEditText empleadoCorreo, TextInputEditText empleadoCelular, TextInputEditText empleadoUsuario,
                              TextInputEditText empleadoClave, TextInputEditText empleadoClave2, TextInputEditText empleadoLatitud,
                              TextInputEditText empleadoLongitud){

        empleadoCedula.setText("");
        nombresEmpleado.setText("");
        empleadoApellido.setText("");
        empleadoCorreo.setText("");
        empleadoCelular.setText("");
        empleadoUsuario.setText("");
        empleadoClave.setText("");
        empleadoClave2.setText("");
        empleadoLatitud.setText("");
        empleadoLongitud.setText("");

    }


}