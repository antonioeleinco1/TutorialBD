package co.com.eleinco.tutorialbd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class BDManagment{

    public static final String DATA_BASE_NAME="BdAVPL";
    public static final String TABLE_NAME="ELEINCOAVPL";
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

    public BDManagment(Context context, int version){
        this.context = context;
        BDHelper ayuda = new BDHelper(context, version);
        db = ayuda.getWritableDatabase();
    }

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

    public ContainersBD getDireccionFavoritaByindex(int i){
        Cursor c = db.query(TABLE_NAME, new String[]{CAVPL_ID, CAVPL_NAME, CAVPL_LATITUD, CAVPL_LONGITUD, CAVPL_TIPO}, null, null, null, null, null);

        if( c.moveToFirst() ){
            c.move(i);
            return new ContainersBD(c.getString(0),c.getString(1), c.getString(2),c.getString(3),c.getString(4));
        }
        return null;
    }

    public int size(){
        Cursor c = db.query(TABLE_NAME, new String[]{CAVPL_ID, CAVPL_NAME, CAVPL_LATITUD, CAVPL_LONGITUD, CAVPL_TIPO}, null, null, null, null, null);
        return c.getCount();
    }


    //prueba
    public Cursor cargarCursor(){
        //query(Table,columns,String selection, String selection args,String Group BY,String Having,String OrderBy)
        String [] columnas= new String[] {CAVPL_ID, CAVPL_NAME, CAVPL_LATITUD, CAVPL_LONGITUD, CAVPL_TIPO};
        return db.query(TABLE_NAME, columnas, null, null, null, null, null);
    }


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

    public void eliminarXnombre(String nombre){
        db.delete(TABLE_NAME, CAVPL_NAME + "=?", new String[]{nombre});
    }

}
