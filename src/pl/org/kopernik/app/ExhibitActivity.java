package pl.org.kopernik.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class ExhibitActivity extends Activity {
	Context ctx;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exhibit);
        ctx = this;
    }
    
    public void goFurther(View v) {
    	Intent i = new Intent(ctx, PathSummaryActivity.class);
    	startActivity(i);    	
    }
    
    public void goToDetails(View v) {
    	Intent i = new Intent(ctx, ExhibitDetailsActivity.class);
    	startActivity(i);    	
    }    
}