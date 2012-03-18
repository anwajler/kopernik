package pl.org.kopernik.app;

import pl.org.kopernik.json.Exhibit;
import pl.org.kopernik.json.Path;
import pl.org.kopernik.json.Quize;
import pl.org.kopernik.json.WaterPath;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class ExhibitActivity extends Activity {
	Context ctx;
	WaterPath waterPath = KopernikApplication.waterPath;
	TextView question;
	Exhibit exhibit;
	int exhibitSize;
	int exhibitbNumber;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exhibit);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        exhibitbNumber = (Integer) b.get("exhibitNumber");
        exhibit = waterPath.path.exhibits.get(exhibitbNumber);
        exhibitSize = waterPath.path.exhibits.size();
        
        question = (TextView) findViewById(R.id.question);
        question.setText(exhibit.question.question_text);
        ctx = this;
    }
    
    private boolean isLast(){
    	return (exhibitSize - 1) == exhibitbNumber ? true : false;
    }
    
    public void goFurther(View v) {
    	if(isLast()){
    		Intent i = new Intent(ctx, PathSummaryActivity.class);
    		startActivity(i);
    		Log.d("goFurther", KopernikApplication.waterPath.path.exhibits.get(0).name);
    	} else{
    	   	Intent i = new Intent(ctx, ExhibitActivity.class);
        	i.putExtra("exhibitNumber", ++exhibitbNumber);
        	startActivity(i); 
    	}
    }
    
    public void goToDetails(View v) {
    	Intent i = new Intent(ctx, ExhibitDetailsActivity.class);
    	startActivity(i);    	
    }    
    
    public void exhibitList(View v) {
    	String items[] = {"item1", "item2"};
    	new AlertDialog.Builder(this)
        .setSingleChoiceItems(items, 0, null)
        .setPositiveButton(R.string.ok_button_label, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
                int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                // Do something useful withe the position of the selected radio button
            }
        })
        .show();    	
    }
    
    public void exitPath(View v) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("Czy na pewno chcesz wyjść ze ścieżki?")
    	       .setCancelable(false)
    	       .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	                startActivity(new Intent(ctx, KopernikActivity.class));
    	           }
    	       })
    	       .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	                dialog.cancel();
    	           }
    	       });
    	AlertDialog alert = builder.create();
    	alert.show();
    }    
}