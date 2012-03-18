package pl.org.kopernik.app;

import java.util.ArrayList;

import pl.org.kopernik.json.Exhibit;
import pl.org.kopernik.json.Quize;
import pl.org.kopernik.json.WaterPath;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class KopernikActivity extends Activity {
	Context ctx;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ctx = this;
        
       /* ArrayList<String> answers = new ArrayList<String>();
        answers.add("pierwsza odp");
        answers.add("druga odp");
        answers.add("trzecia odp");
        Quize q = new Quize("przykladowe pytanie", answers, 0);
        Exhibit e = new Exhibit(0, 0, "eksponat 1", "idz prosto do wielkiego kola", "dlugi opis jaka piekna rzecz", q, 0);
        pl.org.kopernik.json.Path p = new pl.org.kopernik.json.Path();
        p.addExhibit(e);
        Log.d("JSON", p.returnAaJSON().toString());*/
    }
    
    public void goToExhibit(View v) {
    	Intent i = new Intent(ctx, PathActivity.class);
    	startActivity(i);
    }
}