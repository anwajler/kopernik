package pl.org.kopernik.app;


import junit.framework.Assert;
import pl.org.kopernik.db.KopernikDbHelper;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class KopernikApplication extends Application {


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
    }

    private static KopernikApplication self() {
            Assert.assertNotNull(mSelf);
            return mSelf;
    }

    private KopernikDbHelper db;
    private static KopernikApplication mSelf;
}