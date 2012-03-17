package pl.org.kopernik.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class KopernikDbHelper extends SQLiteOpenHelper {

       public KopernikDbHelper(Context context, String name, CursorFactory factory,
                       int version) {
               super(context, name, factory, version);
               // TODO Auto-generated constructor stub
       }

   @Override
   public void onCreate(SQLiteDatabase db) {
       db.execSQL(Path.getTableCreateSQL());

   }

       @Override
       public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
               // TODO Auto-generated method stub
       }
}