package com.example.carcompany.basededatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLite  extends SQLiteOpenHelper {


    public AdminSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase dataBaseMain) {

        /*
        * private TextInputEditText empleadoCedula;
        * private TextInputEditText nombresEmpleado;
        * private TextInputEditText empleadoApellido;
            private TextInputEditText empleadoCorreo;
            private TextInputEditText empleadoCelular;
            private TextInputEditText empleadoUsuario;
            private TextInputEditText empleadoClave;
            private TextInputEditText empleadoClave2;
            private TextInputEditText empleadoLatitud;
            private TextInputEditText empleadoLongitud;
        * */
        dataBaseMain.execSQL("CREATE TABLE usuario (id INTEGER primary key AUTOINCREMENT, cedula TEXT, nombres TEXT, " +
                "apellidos TEXT, correo TEXT, celular TEXT, usuario TEXT, " +
                "clave TEXT, latitud TEXT, longitud TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
