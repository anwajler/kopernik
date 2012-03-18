package pl.org.kopernik.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PathActivity extends Activity {
	Context ctx;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.path_description);
        ctx = this;
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String path = (String) extras.get("path");
        Button startButton =  (Button) findViewById(R.id.pathInfoStartButton);
        startButton.setClickable(false);
        TextView description = (TextView) findViewById(R.id.PathInfoDescription);
        TextView header = (TextView) findViewById(R.id.header);
        if(path.equals("water")){
        	startButton.setClickable(true);
        	header.setText(R.string.welcomeWaterPathName);
        	description.setText(R.string.water_path_description);
        } else if (path.equals("fire")){
        	header.setText(R.string.welcomeFirePathName);
        	description.setText(R.string.fire_path_description);
        } else if (path.equals("earth")){
        	header.setText(R.string.welcomeEarthPathName);
        	description.setText(R.string.earth_path_description);
        } else if (path.equals("air")){
        	header.setText(R.string.welcomeAirPathName);
        	description.setText(R.string.air_path_description);

        }
        	

        	
        
        	
        
        
    }
    
    public void beginPath(View v) {
    	Intent i = new Intent(ctx, PathBeginActivity.class);
    	startActivity(i);    	
    }
}