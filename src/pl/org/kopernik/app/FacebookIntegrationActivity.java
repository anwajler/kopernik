package pl.org.kopernik.app;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FacebookIntegrationActivity extends Activity {

    Facebook facebook = new Facebook("360449237332805");
    AsyncFacebookRunner asyncFacebookRunner = new AsyncFacebookRunner(facebook);
    String FILENAME = "AndroidSSO_data";
    private SharedPreferences mPrefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fb_api);  
        //updateStatusOnClick(null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        facebook.authorizeCallback(requestCode, resultCode, data);        
    }
    

    
    public void updateStatusOnClick(View v){
    	/*
         * Get existing access_token if any
         */
        mPrefs = getPreferences(MODE_PRIVATE);
        String access_token = mPrefs.getString("access_token", null);
        long expires = mPrefs.getLong("access_expires", 0);
        if(access_token != null) {
            facebook.setAccessToken(access_token);
        }
        if(expires != 0) {
            facebook.setAccessExpires(expires);
        }
        
        /*
         * Only call authorize if the access_token has expired.
         */
        if(!facebook.isSessionValid()) {

            facebook.authorize(this, new String[] {"publish_stream", "publish_actions"}, new DialogListener() {
                @Override
                public void onComplete(Bundle values) {
                    SharedPreferences.Editor editor = mPrefs.edit();
                    editor.putString("access_token", facebook.getAccessToken());
                    editor.putLong("access_expires", facebook.getAccessExpires());
                    editor.commit();
                }
    
                @Override
                public void onFacebookError(FacebookError error) {}
    
                @Override
                public void onError(DialogError e) {}
    
                @Override
                public void onCancel() {}
            });
        }
        
        //EditText statusEditText = (EditText) findViewById(R.id.statusEditText);
        //String status = statusEditText.getText().toString();
        Bundle params = new Bundle();

        params.putString("message", "Moja ścieżka w Centrum Nauki Kopernik.");
        
        params.putString("exhibit", "http://wyspakreatywna.home.pl/copernicusExplorer/exhibits/show.php?id=1_2");
        //params.putByteArray("description", "A Freshman College Girl on a scholarship from an ...".getBytes());

        asyncFacebookRunner.request("me/copernicusexplorer:explore", params, "POST", new AsyncFacebookRunner.RequestListener() {
			
			@Override
			public void onMalformedURLException(MalformedURLException e, Object state) {
				// TODO Auto-generated method stub
				e.printStackTrace();
			}
			
			@Override
			public void onIOException(IOException e, Object state) {
				// TODO Auto-generated method stub
				e.printStackTrace();
			}
			
			@Override
			public void onFileNotFoundException(FileNotFoundException e, Object state) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFacebookError(FacebookError e, Object state) {
				Log.d("error",e.toString());
				
			}
			
			@Override
			public void onComplete(String response, Object state) {
				Intent i = new Intent(getApplicationContext(), KopernikActivity.class);
				i.putExtra("showToast", true);
				startActivity(i);
			}
		}, params );
        	
        
    	
    }
}