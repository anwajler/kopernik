package pl.org.kopernik.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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