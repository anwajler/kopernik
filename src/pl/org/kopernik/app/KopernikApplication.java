package pl.org.kopernik.app;


import java.util.ArrayList;

import junit.framework.Assert;
import pl.org.kopernik.db.KopernikDbHelper;
import pl.org.kopernik.json.Exhibit;
import pl.org.kopernik.json.Quize;
import pl.org.kopernik.json.WaterPath;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Path;
import android.util.Log;


public class KopernikApplication extends Application {

	public static WaterPath waterPath;
 

	
    public static SQLiteDatabase db() {
        if (self().db == null) {
                self().db = new KopernikDbHelper(context());
        }
        return self().db.getWritableDatabase();
}

    public static Context context() {
            return self();
    }


    @Override
    public void onCreate() {
            super.onCreate();

            mSelf = this;
            
             waterPath = new WaterPath();

    }

    private static KopernikApplication self() {
            Assert.assertNotNull(mSelf);
            return mSelf;
    }

    private KopernikDbHelper db;
    private static KopernikApplication mSelf;
}