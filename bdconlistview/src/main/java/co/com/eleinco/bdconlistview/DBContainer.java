package co.com.eleinco.bdconlistview;

/**
 * Created by to-bl on 8/08/2016.
 */
public class DBContainer {

    private String ID;
    private String nombre;
    private String latitud;
    private String longitud;
    private String tipo;

    public DBContainer(String ID, String nombre, String latitud, String longitud, String tipo){
        this.ID = ID;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tipo = tipo;
    }

    public String getID(){ return ID;  }

    public String getNombre(){
        return nombre;
    }

    public String getLatitud(){
        return latitud;
    }

    public String getLongitud(){
        return longitud;
    }

    public String getTipo(){
        return tipo;
    }
}
