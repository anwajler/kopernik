package pl.org.kopernik.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class KopernikActivity extends Activity {
	Context ctx;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ctx = this;
    }
    
    public void goToExhibit(View v) {
    	Intent i = new Intent(ctx, PathActivity.class);
    	startActivity(i);
    }
}