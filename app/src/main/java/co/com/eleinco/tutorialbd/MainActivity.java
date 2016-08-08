package co.com.eleinco.tutorialbd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etNombre,etLatitud,etLongitud,etID;
    TextView tvID,tvNombre,tvLatitud,tvLongitud,tvTipo;
    RadioGroup rgTipo;

    String nombre="",latitud="",longitud="",tipo="",ID="";
    int okrg=0;

    BDManagment BaseD;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre= (EditText) findViewById(R.id.etNombre);
        etLatitud= (EditText) findViewById(R.id.etLatitud);
        etLongitud= (EditText) findViewById(R.id.etLongitud);
        etLongitud= (EditText) findViewById(R.id.etID);
        etID = (EditText) findViewById(R.id.etID);
        rgTipo = (RadioGroup) findViewById(R.id.rdTipo);
        tvID= (TextView) findViewById(R.id.tvID);
        tvLatitud= (TextView) findViewById(R.id.tvLatitud);
        tvLongitud = (TextView) findViewById(R.id.tvLongitud);
        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvTipo = (TextView) findViewById(R.id.tvTipo);

        //Constructor
        BaseD = new BDManagment(this,1);

        BDHelper helper = new BDHelper(this,1);
        SQLiteDatabase db = helper.getWritableDatabase();

        //Para agregar registros
       // BaseD.insertar("David", "6.78", "76.48", "Persona");
       // BaseD.insertar("Toño", "6.56", "76.444", "Persona");


//        ID= String.valueOf(etID.getText());

    }

    public void ingresar (View view){
        nombre=String.valueOf(etNombre.getText());
        latitud=String.valueOf(etLatitud.getText());
        longitud=String.valueOf(etLongitud.getText());
        rgTipo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                okrg = 1;
                switch (checkedId) {
                    case R.id.rbVehiculo:
                        tipo = "Automovil";
                        break;

                    case R.id.rbPersona:
                        tipo = "Persona";
                        break;
                }


            }

        });
        etNombre.setText("");
        etLatitud.setText("");
        etLongitud.setText("");

        BaseD.insertar(nombre, latitud, longitud, "t");

        tvID.setText("ID:" + ID);
        tvNombre.setText("Nombre:" + nombre);
        tvLatitud.setText("Latitud:"+latitud);
        tvLongitud.setText("Longitud:" + longitud);
        tvTipo.setText("Tipo:" + tipo);
    }

    public void consultarNombre(View view){

        cursor=BaseD.buscarPorNombre(String.valueOf(etNombre.getText()));
        if (cursor.moveToFirst()==true) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                ID = cursor.getString(0);
                nombre = cursor.getString(1);
                latitud = cursor.getString(2);
                longitud = cursor.getString(3);
                tipo = cursor.getString(4);
            } while(cursor.moveToNext());

            tvID.setText("ID:" + ID);
            tvNombre.setText("Nombre:" + nombre);
            tvLatitud.setText("Latitud:"+latitud);
            tvLongitud.setText("Longitud:"+longitud);
            tvTipo.setText("Tipo:"+tipo);
        }else{
            Toast.makeText(this, "El usuario no existe en la base de datos", Toast.LENGTH_SHORT).show();
        }
    }

    public void consultarid(View view){

        cursor=BaseD.buscarPorID(String.valueOf(etID.getText()));
        if (cursor.moveToFirst()== true) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                ID = cursor.getString(0);
                nombre = cursor.getString(1);
                latitud = cursor.getString(2);
                longitud = cursor.getString(3);
                tipo = cursor.getString(4);
            } while(cursor.moveToNext());

            tvID.setText("ID:" + ID);
            tvNombre.setText("Nombre:" + nombre);
            tvLatitud.setText("Latitud:"+latitud);
            tvLongitud.setText("Longitud:"+longitud);
            tvTipo.setText("Tipo:"+tipo);
        }else{
            Toast.makeText(this, "El usuario no existe en la base de datos", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminar(View view){
        nombre=String.valueOf(etNombre.getText());
        BaseD.eliminarXnombre(nombre);
        Toast.makeText(MainActivity.this, "El usuario" + nombre +"fue eliminado", Toast.LENGTH_SHORT).show();
    }

}