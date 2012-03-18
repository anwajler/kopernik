package pl.org.kopernik.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class KopernikActivity extends Activity {
	Context ctx;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ctx = this;

		Intent i = getIntent();
		if (i != null) {
			Bundle extras = i.getExtras();
			if (extras != null) {
				if (extras.containsKey("showToast")) {
					Toast.makeText(
							getApplicationContext(),
							"Informacja o przebytej ścieżce została opublikowana na Facebooku!",
							Toast.LENGTH_LONG).show();
				}
			}
		}
	}

	public void goToExhibit(View v) {
		Intent i = new Intent(ctx, PathActivity.class);
		startActivity(i);
	}
}