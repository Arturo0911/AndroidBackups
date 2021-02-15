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
import com.example.carcompany.process.Empleado;
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

    LocationManager locationManager;
    LocationListener locationListener;

    private Credential credential = null;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(Registro.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                }
            }
        }

    }


    public void onSaveButton(View view) {
        credential = new Credential();
        String cedula = empleadoCedula.getText().toString();
        String nombres = nombresEmpleado.getText().toString();
        String apellidos = empleadoApellido.getText().toString();
        String correo = empleadoCorreo.getText().toString();
        String celular = empleadoCelular.getText().toString();
        String usuario = empleadoUsuario.getText().toString();
        String clave = empleadoClave.getText().toString();
        String clave2 = empleadoClave2.getText().toString();

        if (chequearCamposVacios(cedula, nombres, apellidos, correo, celular, usuario, clave, clave2)){
            if (credential.verificarCedula(cedula) && clave.equalsIgnoreCase(clave2)) {
                Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Cedula incorrecta, ingrese una cedula correcta", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Los campos no pueden estar vacios", Toast.LENGTH_SHORT).show();
        }

    }

    public void getLocation(View view){
        /*Intent intent = new Intent(Registro.this, Geolocation.class);
        startActivity(intent);*/

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                //Toast.makeText(Geolocation.this, "location: "+location.toString(), Toast.LENGTH_SHORT).show();
                /*Empleado.latitude = String.valueOf( location.getLatitude());
                Empleado.longitude = String.valueOf( location.getLongitude());*/

                empleadoLatitud.setText( String.valueOf(location.getLatitude()));
                empleadoLongitud.setText( String.valueOf(location.getLongitude()));

                //Intent intent = new Intent(Registro.this, Registro.class);
                //finishActivity();
                //
                //startActivity(intent);


            }


            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {

            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {

            }
        };

        if (Build.VERSION.SDK_INT < 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }else{
            if (ContextCompat.checkSelfPermission(Registro.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(Registro.this, new String []{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }else{
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            }
        }
    }

    public void onCancelButton(View view) {

        Intent intent = new Intent(Registro.this, Login.class);
        startActivity(intent);
    }

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


        /*empleadoLatitud.setText( Empleado.latitude);
        empleadoLongitud.setText( Empleado.longitude);
        getLocation = (MaterialButton) findViewById(R.id.getLocation);*/




    }


    public boolean chequearCamposVacios(String empleadoCedula, String nombresEmpleado,
                                        String empleadoApellido, String empleadoCorreo, String empleadoCelular, String empleadoUsuario,
                                        String empleadoClave, String empleadoClave2){


        return !empleadoCedula.equals("")&&!nombresEmpleado.equals("")&&!empleadoApellido.equals("")&&!empleadoCorreo.equals("")&&!empleadoCelular.equals("")&&
                !empleadoUsuario.equals("")&&!empleadoClave.equals("")&&!empleadoClave2.equals("");
    }

    public void limpiarCampos(TextInputEditText empleadoCedula, TextInputEditText nombresEmpleado, TextInputEditText empleadoApellido,
                              TextInputEditText empleadoCorreo, TextInputEditText empleadoCelular, TextInputEditText empleadoUsuario,
                              TextInputEditText empleadoClave, TextInputEditText empleadoClave2){

        empleadoCedula.setText("");
        nombresEmpleado.setText("");
        empleadoApellido.setText("");
        empleadoCorreo.setText("");
        empleadoCelular.setText("");
        empleadoUsuario.setText("");
        empleadoClave.setText("");
        empleadoClave2.setText("");


    }


}