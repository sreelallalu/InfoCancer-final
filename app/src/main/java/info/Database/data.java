package info.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class data {
public static final String KEY_ROWID ="_id";
public static final String KEY_NAME ="p_name";
public static final String KEY_NICK ="p_nick";
public static final String KEY_ADRR ="p_adrr";
public static final String KEY_PHN ="p_phn";

private static final String DATABASE_NAME ="CourseGradedb";
private static final String DATABASE_TABLE ="courseTable";
private static final int DATABASE_VERSION =1;
private DbHelper myHelper;
private final Context myContext;
private SQLiteDatabase myDatabase;
	private static final String DATABASE_TABLE1 ="table1";

	//column name
	private static final String ID_1="id_1";
	private static final String Column_1="column_1";
	private static final String Column_2="column_2";
	private static final String Column_3="column_3";

private static class DbHelper extends SQLiteOpenHelper {


	public DbHelper(Context context) {
super(context, DATABASE_NAME, null, DATABASE_VERSION);
//TODO Auto-generated constructor stub
}
@Override
  public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub
   db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
   KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                      KEY_NAME + " TEXT NOT NULL, " +
                       KEY_NICK + " TEXT NOT NULL," +
                      KEY_ADRR + " TEXT NOT NULL, " +
                        KEY_PHN + " TEXT NOT NULL);"
     );
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
// TODO Auto-generated method stub
db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
onCreate(db);
}
}
public data(Context c){
myContext = c;
}
public data open() throws SQLException {
myHelper = new DbHelper(myContext);
myDatabase = myHelper.getWritableDatabase();
return this;
}
public void close(){
myHelper.close();
}

public long createEntry(DatabaseItems item) {
// TODO Auto-generated method stub
ContentValues cv = new ContentValues();
cv.put(Column_1, item._Title);
cv.put(Column_2, item._Stringcontent);
cv.put(Column_3, item._Url);


return myDatabase.insert(DATABASE_TABLE1, null, cv);
}
public String getData1() {
// TODO Auto-generated method stub
String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_NICK,KEY_ADRR,KEY_PHN};
Cursor c = myDatabase.query(DATABASE_TABLE, columns, null, null, null, null,
null);
String result = "";
int irow = c.getColumnIndex(KEY_ROWID);
int iName = c.getColumnIndex(KEY_NAME);

for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
result = result + c.getString(irow) + " "+ c.getString(iName) +"\n";
}
return result;
}public String getData2() {
	// TODO Auto-generated method stub
	String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_NICK,KEY_ADRR,KEY_PHN};
	Cursor c = myDatabase.query(DATABASE_TABLE, columns, null, null, null, null,
	null);
	String result1 = "";

	int iphn = c.getColumnIndex(KEY_PHN);

	for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
	result1 = result1 +":"+ c.getString(iphn)+" " +"\n";
	}
	return result1;
	}

public String getName(long l) throws SQLException {
// TODO Auto-generated method stub
String[] columns = new String[]{ KEY_ROWID, KEY_NAME,KEY_NICK,KEY_ADRR,KEY_PHN};
Cursor c = myDatabase.query(DATABASE_TABLE, columns, KEY_PHN + "=" + l,
null, null, null, null);
if (c != null){
c.moveToFirst();
String name = c.getString(1);
return name;
}
return null;
}
public String getNick(long l) throws SQLException {
// TODO Auto-generated method stub
String[] columns = new String[]{ KEY_ROWID, KEY_NAME,KEY_NICK,KEY_ADRR,KEY_PHN};
Cursor c = myDatabase.query(DATABASE_TABLE, columns, KEY_PHN + "=" + l,
null, null, null, null);
if (c != null){
c.moveToFirst();
String nick = c.getString(2);
return nick;
}
return null;
}
public String getAdrr(long l) throws SQLException {
	// TODO Auto-generated method stub
	String[] columns = new String[]{ KEY_ROWID, KEY_NAME,KEY_NICK,KEY_ADRR,KEY_PHN};
	Cursor c = myDatabase.query(DATABASE_TABLE, columns, KEY_PHN + "=" + l,
	null, null, null, null);
	if (c != null){
	c.moveToFirst();
	String adrr = c.getString(3);
	return adrr;
	}
	return null;
	}
public String getPhn(long l) throws SQLException {
	// TODO Auto-generated method stub
	String[] columns = new String[]{ KEY_ROWID, KEY_NAME,KEY_NICK,KEY_ADRR,KEY_PHN};
	Cursor c = myDatabase.query(DATABASE_TABLE, columns, KEY_PHN + "=" + l,
	null, null, null, null);
	if (c != null){
	c.moveToFirst();
	String phn = c.getString(4);
	return phn;
	}
	return null;
	}
public void updateEntry(long lRow, String mName, String mNick , String mAdrr, String mPhn) throws
		SQLException {
// TODO Auto-generated method stub
ContentValues cvUpdate = new ContentValues();
cvUpdate.put(KEY_NAME, mName);
cvUpdate.put(KEY_NICK, mNick);
cvUpdate.put(KEY_ADRR, mAdrr);
cvUpdate.put(KEY_PHN, mPhn);

myDatabase.update(DATABASE_TABLE, cvUpdate, KEY_PHN+ "=" + lRow, null);
}
public void deleteEntry(long lRow1) throws SQLException {
// TODO Auto-generated method stub
myDatabase.delete(DATABASE_TABLE,KEY_PHN + "=" + lRow1, null);
}
}