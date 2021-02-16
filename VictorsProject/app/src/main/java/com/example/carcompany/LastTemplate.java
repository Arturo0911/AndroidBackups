package com.example.carcompany;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import com.example.carcompany.process.ListControl;
import com.example.carcompany.process.Vehicle;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.util.ArrayList;

public class LastTemplate extends AppCompatActivity{

    private TextView whichService;
    private TextView fullMake;
    private TextView fullModel;
    private TextView fullName;
    private TextView fullCredentials;
    private TextView serviceDescription;
    private TextView fullOrder;

    private MaterialButton deleteOrder;
    private MaterialButton confirmOrder;

    private ImageView finalImage;

    public void onDeleteOrder(View view){
        fullOrder = (TextView) findViewById(R.id.fullOrder);
        int index = Integer.parseInt(fullOrder.getText().toString());
        generateDialogOnDelete(index);
        //Toast.makeText(this, "order number: "+fullOrder.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    public void sendOnclickButton(View view){

        generateDialogOnConFirm();

        try {
            //RequestMethodPOST(fullName.getText().toString(), emailAddress,whichService.getText().toString(), url );
            Toast.makeText(this, "email sended successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LastTemplate.this, MainActivity.class);
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(this, "Error by: "+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_template);

        whichService = (TextView)findViewById(R.id.whichService);
        fullMake = (TextView)findViewById(R.id.fullMake);
        fullModel = (TextView)findViewById(R.id.fullModel);
        fullName = (TextView)findViewById(R.id.fullName);
        fullCredentials = (TextView)findViewById(R.id.fullCredentials);
        serviceDescription = (TextView)findViewById(R.id.serviceDescription);
        fullOrder = (TextView) findViewById(R.id.fullOrder);
        finalImage = (ImageView) findViewById(R.id.finalImage);

        Intent lastIntent = getIntent();
        int index = Integer.parseInt(lastIntent.getStringExtra("position"));

        ArrayList<Vehicle> valuesAdd = ListControl.vehiculoLista;

        //valuesAdd = ListControl.vehiculoLista.get(index);


        fullOrder.setText(String.valueOf(index+1));
        whichService.setText(valuesAdd.get(index).getTipoServicio());
        fullMake.setText("Make: "+valuesAdd.get(index).getMarca());
        fullModel.setText("Model: "+valuesAdd.get(index).getModelo());
        fullName.setText(valuesAdd.get(index).getClienteNombre()+" "+valuesAdd.get(index).getClienteApellido());
        fullCredentials.setText(valuesAdd.get(index).getClienteCedula());
        serviceDescription.setText("La placa del carro es: "+valuesAdd.get(index).getPlaca()+". Con nuestro servicio tendrás la mejor experienza");

        if(whichService.getText().toString().equals("Car washing")){
            finalImage.setImageResource(R.drawable.wash);
        }else if (whichService.getText().toString().equals("Car maintenance")){
            finalImage.setImageResource(R.drawable.maintenance_2);
        }else{
            finalImage.setImageResource(R.drawable.paint_2);
        }



    }



    private void generateDialogOnConFirm(){
        AlertDialog.Builder builder = new AlertDialog.Builder(LastTemplate.this);
        builder.setTitle("Alert");
        builder.setMessage("confirm the purchase?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }

    private void generateDialogOnDelete(int index){
        AlertDialog.Builder builder = new AlertDialog.Builder(LastTemplate.this);
        builder.setTitle("Alert");
        builder.setMessage("¿Seguro que desea eliminar esta orden?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Borrando...", Toast.LENGTH_LONG).show();

                        if(ListControl.vehiculoLista.size()<=1){
                            ListControl.vehiculoLista.remove(index);
                            Intent intent = new Intent(LastTemplate.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            ListControl.vehiculoLista.remove(index);
                            Intent intent = new Intent(LastTemplate.this, MainActivity.class);
                            startActivity(intent);
                        }

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "ok! No problem", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }

}