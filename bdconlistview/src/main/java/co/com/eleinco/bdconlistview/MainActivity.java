package co.com.eleinco.bdconlistview;

import android.app.ActionBar;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    DBManager BaseD ;
    Cursor cursor;
    private ListView lista;
    private SimpleCursorAdapter adapter;

    private EditText tv;
    private ImageButton bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.lista);
        tv = (EditText) findViewById(R.id.editText);
        bt= (ImageButton) findViewById(R.id.imageButton);
        bt.setOnClickListener(this);
        String[] from= new String[] {BaseD.CAVPL_NAME, BaseD.CAVPL_TIPO};
        int[] to= new int[] {android.R.id.text1,android.R.id.text2};
//cursor = BaseD.cargarCursor();
        adapter= new SimpleCursorAdapter(this,android.R.layout.two_line_list_item,cursor,from,to,0);
        lista.setAdapter(adapter);

        DBHelper helper = new DBHelper(this,1);
        SQLiteDatabase db = helper.getWritableDatabase();

        BaseD = new DBManager(this,1);

        //Para agregar registros
        BaseD.insertar("David", "6.78", "76.48", "Persona");
        BaseD.insertar("Toño", "6.56", "76.444", "Persona");
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imageButton){
            tv.getText().toString();
            Cursor c = BaseD.buscarPorNombre(tv.getText().toString());
            adapter.changeCursor(c);
        }
    }
}
