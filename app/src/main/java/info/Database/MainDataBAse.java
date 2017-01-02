package info.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;


/**
 * Created by SLR on 9/19/2016.
 */
public class MainDataBAse {

    private DbHelper myHelper;
    private final Context myContext;
    private SQLiteDatabase myDatabase;

    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, DATABASE_C.DATABASENAME, null, DATABASE_C.DATABSEVERSION);
//TODO Auto-generated constructor stub

        }
        @Override
        public void onCreate(SQLiteDatabase db) {

// TODO Auto-generated method stub
     db.execSQL("CREATE TABLE " + DATABASE_C.TABLE_NAME.T_USER + " (" +
                    DATABASE_C.COLUMN_NAME_USER.U_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DATABASE_C.COLUMN_NAME_USER.U_name + " TEXT NOT NULL, " +
                    DATABASE_C.COLUMN_NAME_USER.U_email + " TEXT NOT NULL," +
                    DATABASE_C.COLUMN_NAME_USER.U_pass + " TEXT NOT NULL," +
                    DATABASE_C.COLUMN_NAME_USER.U_userid + " TEXT NOT NULL," +
                   DATABASE_C.COLUMN_NAME_USER.U_phone + " TEXT NOT NULL," +
                     DATABASE_C.COLUMN_NAME_USER.U_age + " TEXT NOT NULL," +
                    DATABASE_C.COLUMN_NAME_USER.U_url + " TEXT NOT NULL," +
                   DATABASE_C.COLUMN_NAME_USER.U_PPP+ " TEXT NOT NULL);"
            );

      for(int i=1;i<=13;i++) {
          db.execSQL("CREATE TABLE " + DATABASE_C.TABLE_NAME.T_IMGAGE+i + " (" +
                  DATABASE_C.COLUMN_NAME_IMAGE.I_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                  DATABASE_C.COLUMN_NAME_IMAGE.I_title + " TEXT NOT NULL," +
                  DATABASE_C.COLUMN_NAME_IMAGE.I_text + " TEXT NOT NULL," +
                  DATABASE_C.COLUMN_NAME_IMAGE.I_url + " TEXT NOT NULL);"
          );
      }


        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
        for(int i=1;i<=13;i++)
            {
                db.execSQL("DROP TABLE IF EXISTS " +DATABASE_C.TABLE_NAME.T_IMGAGE+i);
            }
       db.execSQL("DROP TABLE IF EXISTS " +  DATABASE_C.TABLE_NAME.T_USER);
            onCreate(db);
        }
    }
    public MainDataBAse(Context c){
        myContext = c;
    }


    public MainDataBAse open() throws SQLException {
        myHelper = new DbHelper(myContext);
        myDatabase = myHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        myHelper.close();
    }


 public long createuserTable(String tablename, UserDbItem item, String PPP)throws Exception
 {     ContentValues cve = new ContentValues();
        cve.put(DATABASE_C.COLUMN_NAME_USER.U_name,item.get_name());
       cve.put(DATABASE_C.COLUMN_NAME_USER.U_email, item.get_email());
     cve.put(DATABASE_C.COLUMN_NAME_USER.U_pass,item.get_pass());
     cve.put(DATABASE_C.COLUMN_NAME_USER.U_userid, item.get_userid());
     cve.put(DATABASE_C.COLUMN_NAME_USER.U_phone, item.get_phone());
     cve.put(DATABASE_C.COLUMN_NAME_USER.U_age, item.get_age());
     cve.put(DATABASE_C.COLUMN_NAME_USER.U_url, item.get_url());
     cve.put(DATABASE_C.COLUMN_NAME_USER.U_PPP,PPP);
     return myDatabase.insert(tablename, null, cve);

 }
    public long updateuserTable(String tablename, UserDbItem item, long l, String PPP)throws Exception
    {     ContentValues cve = new ContentValues();
        cve.put(DATABASE_C.COLUMN_NAME_USER.U_name,item.get_name());
        cve.put(DATABASE_C.COLUMN_NAME_USER.U_email, item.get_email());
        cve.put(DATABASE_C.COLUMN_NAME_USER.U_pass,item.get_pass());
        cve.put(DATABASE_C.COLUMN_NAME_USER.U_userid, item.get_userid());
        cve.put(DATABASE_C.COLUMN_NAME_USER.U_phone, item.get_phone());
        cve.put(DATABASE_C.COLUMN_NAME_USER.U_age, item.get_age());
        cve.put(DATABASE_C.COLUMN_NAME_USER.U_url, item.get_url());
        cve.put(DATABASE_C.COLUMN_NAME_USER.U_PPP, PPP);
        return myDatabase.update(tablename,cve,DATABASE_C.COLUMN_NAME_USER.U_id+"="+l,null);

    }



    public long createImageTable(DatabaseItems item, String tablename) {
// TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(DATABASE_C.COLUMN_NAME_IMAGE.I_title, item._Title);
        cv.put(DATABASE_C.COLUMN_NAME_IMAGE.I_text, item._Stringcontent);
        cv.put(DATABASE_C.COLUMN_NAME_IMAGE.I_url, item._Url);
        return myDatabase.insert(tablename, null, cv);
    }









    public String getNameUser(String tablename , String Columnname, long l)throws SQLException
    {
        String[] columns = new String[]{
                DATABASE_C.COLUMN_NAME_USER.U_id,
                DATABASE_C.COLUMN_NAME_USER.U_name,
                DATABASE_C.COLUMN_NAME_USER.U_email,
                DATABASE_C.COLUMN_NAME_USER.U_pass,
                DATABASE_C.COLUMN_NAME_USER.U_userid,
                DATABASE_C.COLUMN_NAME_USER.U_phone,
                DATABASE_C.COLUMN_NAME_USER.U_age,
                DATABASE_C.COLUMN_NAME_USER.U_url,
                DATABASE_C.COLUMN_NAME_USER.U_PPP};
        Cursor c = myDatabase.query(tablename, columns,DATABASE_C.COLUMN_NAME_USER.U_id+"="+l,null,null,null,null);
        String uname ="";
        while(c.moveToNext()){
             uname = c.getString(c.getColumnIndex(Columnname));
            Log.e("uname",uname);
        }
            return uname;

    }



    public String[] getImageTable(String tablename, String Columnname)throws SQLException
    {

        String[] columns = new String[]{DATABASE_C.COLUMN_NAME_IMAGE.I_id, DATABASE_C.COLUMN_NAME_IMAGE.I_title, DATABASE_C.COLUMN_NAME_IMAGE.I_text,DATABASE_C.COLUMN_NAME_IMAGE.I_url};
        Cursor c = myDatabase.query(tablename, columns, null, null, null, null,null);

        String result = "";
        String[] array = new String[c.getCount()];
        int i = 0;
        while(c.moveToNext()){

            String uname = c.getString(c.getColumnIndex(Columnname));
            array[i] = uname;
            Log.e("uname",uname);
            i++;
        }
        return array;
    }



    public boolean check(String tablename){
        boolean b=false;
        Cursor cursor = myDatabase.rawQuery("SELECT count(*) FROM "+tablename,null);
        cursor.moveToFirst();
        if (cursor.getInt(0) > 0)
        {
            b=false;
        }if(cursor.getInt(0)==0)
        {
            b=true;
        }
        cursor.close();
        return b;
    }
    public void exportDB(String db_name){
        String currentDBPath = null;
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/Android/data/"+ myContext.getPackageName()+"";
        File sd = new File(file_path);

        boolean success = true;
        if (!sd.exists()) {
            success = sd.mkdir();
        }
        if (success) {

            File data = Environment.getDataDirectory();
            FileChannel source=null;
            FileChannel destination=null;
            currentDBPath = "/data/"+ myContext.getPackageName() +"/databases/"+db_name;
            String backupDBPath = db_name;
            File currentDB = new File(data, currentDBPath);
            File backupDB = new File(sd, backupDBPath);
            try {
                source = new FileInputStream(currentDB).getChannel();
                destination = new FileOutputStream(backupDB).getChannel();
                destination.transferFrom(source, 0, source.size());
                source.close();
                destination.close();

            } catch(IOException e) {
                e.printStackTrace();
            }
        }

    }







}
