package co.com.eleinco.bdconlistview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by to-bl on 8/08/2016.
 */
public class DBManager {

    public static final String DATA_BASE_NAME="BdconLv";
    public static final String TABLE_NAME="Vigilados";
    public static final String CAVPL_ID="_id";
    public static final String CAVPL_NAME="Nombre";
    public static final String CAVPL_LATITUD="Latitud";
    public static final String CAVPL_LONGITUD="Longitud";
    public static final String CAVPL_TIPO="Tipo";
    public static final int DATA_VERSION=1;
    public static final String crearTabla= "create table " +TABLE_NAME+" ("+
            CAVPL_ID+" integer primary key autoincrement,"+
            CAVPL_NAME+" text,"+
            CAVPL_LATITUD+" text,"+
            CAVPL_LONGITUD+" text,"+
            CAVPL_TIPO+" text)";

    Context context;
    SQLiteDatabase db;

    public DBManager(Context context,int version){

        this.context = context;
        DBHelper helper = new DBHelper(context,version);
        db= helper.getWritableDatabase();
    }


    //Los metodos generarContentValues e insertar se utilizan para ingresar nuevas filas a la tabla
    public ContentValues generarContentValues(String nombre, String latitud, String longitud, String tipo){
        ContentValues valores = new ContentValues();
        valores.put(CAVPL_NAME, nombre);
        valores.put(CAVPL_LATITUD, latitud);
        valores.put(CAVPL_LONGITUD, longitud);
        valores.put(CAVPL_TIPO, tipo);
        return valores;
    }

    public void insertar(String nombre, String latitud, String longitud, String tipo){
        db.insert(TABLE_NAME, null, generarContentValues(nombre, latitud, longitud, tipo));
    }


    //METODOS PARA ELIMINAR DE LA BD
    public void eliminarXid(String id){
        db.delete(TABLE_NAME, CAVPL_ID + "=?", new String[]{id});
    }
    public void eliminarXnombre(String nombre){
        db.delete(TABLE_NAME, CAVPL_NAME + "=?", new String[]{nombre});
    }

    //Se crea el cursor que es utilizado para buscar en la BD
    public Cursor cargarCursor(){
//query(Table,columns,Stringselection, Stringselectionargs,StringGroupBY,StringHaving,StringOrderBy)
        String[] columnas= new String[] {CAVPL_ID, CAVPL_NAME, CAVPL_LONGITUD, CAVPL_LATITUD, CAVPL_TIPO};
        return db.query(TABLE_NAME, columnas, null, null, null, null, null);
    }


    //METODOS PARA BUSCAR POR NOMBRE Y POR ID

    public Cursor buscarPorNombre(String nombre){
        String[] columnas = new String[] {CAVPL_ID, CAVPL_NAME, CAVPL_LATITUD, CAVPL_LONGITUD, CAVPL_TIPO};
        return db.query(TABLE_NAME,columnas,CAVPL_NAME + "=?", new String[]
                {nombre},null,null,null);
    }

    public Cursor buscarPorID(String id){
        String[] columnas = new String[] {CAVPL_ID, CAVPL_NAME, CAVPL_LATITUD, CAVPL_LONGITUD, CAVPL_TIPO};
        return db.query(TABLE_NAME,columnas,CAVPL_ID + "=?", new String[]
                {id},null,null,null);
    }
}
