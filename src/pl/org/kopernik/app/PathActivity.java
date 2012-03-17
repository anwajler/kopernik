package pl.org.kopernik.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PathActivity extends Activity {
	Context ctx;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.path_description);
        ctx = this;
    }
    
    public void beginPath(View v) {
    	Intent i = new Intent(ctx, PathBeginActivity.class);
    	startActivity(i);    	
    }
}