package co.com.eleinco.tutorialbd;


public class ContainersBD {

    private String ID;
    private String nombre;
    private String latitud;
    private String longitud;
    private String tipo;

    public ContainersBD(String ID, String nombre, String latitud, String longitud, String tipo){
        this.ID = ID;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tipo = tipo;
    }

    public String getID(){  return ID; }

    public String getNombre(){
        return nombre;
    }

    public String getLongitud(){
        return longitud;
    }

    public String getLatitud(){
        return latitud;
    }

    public String getTipo(){
        return tipo;
    }
}
