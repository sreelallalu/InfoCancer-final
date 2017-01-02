package info.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SLR on 8/31/2016.
 */

public class DatabaseDb{
    private SQLiteDatabase sqLiteDatabase;
    private static final int DATABASE_VERSION =1;
    private static String DATABASE_NAME ="";

    private Context mContext;
    private static final String DATABASE_TABLE1 ="table1";

    //column name
    private static final String ID_1="id_1";
    private static final String Column_1="column_1";
    private static final String Column_2="column_2";
    private static final String Column_3="column_3";
    private Datab myHelper;
    private SQLiteDatabase myDatabase;
public class Datab extends SQLiteOpenHelper {

    ////
    public Datab(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }
    public void onCreate(SQLiteDatabase db) {

        String table1="CREATE TABLE " + DATABASE_TABLE1 + "("
                + ID_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Column_1 + " TEXT ,"
                + Column_2 + " TEXT ,"
                + Column_3 + " TEXT "
                +")";
        db.execSQL(table1);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE1);
        onCreate(db);
    }


}
    public DatabaseDb open() throws SQLException {
        myHelper = new Datab(mContext);
        myDatabase = myHelper.getWritableDatabase();
        return this;
    }
    public DatabaseDb(Context content)
    {
     mContext=content;
    }

    public void close(){
        myHelper.close();
    }


       public long addVAlue(String a1, String a11, String a111)
       {
           ContentValues cv = new ContentValues();
           cv.put(Column_1,a1);
           cv.put(Column_2,a11);
           cv.put(Column_3,a111);
           return myDatabase.insert(DATABASE_TABLE1, null, cv);
       }







     }






