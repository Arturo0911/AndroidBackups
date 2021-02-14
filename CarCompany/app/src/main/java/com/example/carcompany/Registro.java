package com.example.carcompany;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
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
import android.view.View;

import com.example.carcompany.process.Credential;
import com.example.carcompany.process.ListControl;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
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


    private Credential credential = null;
    private LocationManager locationManager;
    private LocationListener locationListener;

    FusedLocationProviderClient fusedLocationProviderClient;


    public void onSaveButton(View view) {

    }

    public void onCancelButton(View view) {

        Intent intent = new Intent(Registro.this, Login.class);
        startActivity(intent);
    }

    public void getLocation(View view) {
        if (ActivityCompat.checkSelfPermission(Registro.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //onGetLocation();
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location == null) {

                        try {
                            Geocoder geocoder = new Geocoder(Registro.this, Locale.getDefault());
                            List<Address> addresses = geocoder.getFromLocation(
                                    location.getLatitude(), location.getLongitude(), 1
                            );

                            empleadoLatitud.setText((int) addresses.get(0).getLatitude());
                            empleadoLongitud.setText((int) addresses.get(0).getLongitude());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } else {
            ActivityCompat.requestPermissions(Registro.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
    }

    /*private void onGetLocation() {
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
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location == null) {

                    try {
                        Geocoder geocoder = new Geocoder(Registro.this, Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );

                        empleadoLatitud.setText((int) addresses.get(0).getLatitude());
                        empleadoLongitud.setText((int) addresses.get(0).getLongitude());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }*/

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

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(Registro.this);


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