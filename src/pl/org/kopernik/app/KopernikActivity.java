package pl.org.kopernik.app;

import android.app.Activity;
import android.os.Bundle;

public class KopernikActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}