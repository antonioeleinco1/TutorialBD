package co.com.eleinco.bdconlistview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by to-bl on 8/08/2016.
 */
public class DBHelper extends SQLiteOpenHelper{

    public DBHelper(Context context,int version) {
        super(context,"BDconLv.sqlite",null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(DBManager.crearTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
