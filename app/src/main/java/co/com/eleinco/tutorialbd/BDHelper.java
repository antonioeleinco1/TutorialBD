package co.com.eleinco.tutorialbd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by to-bl on 4/08/2016.
 */
public class BDHelper extends SQLiteOpenHelper {
    //llamamos al constructor
    public BDHelper(Context context, int version) {
        super(context, "BdAVPL.sqlite", null, version);
    }

    //Se crea la BD
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BDManagment.crearTabla);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
