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
		// updateStatusOnClick(null);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		facebook.authorizeCallback(requestCode, resultCode, data);
	}

	public void updateStatusOnClick(View v) {
		/*
		 * Get existing access_token if any
		 */
		mPrefs = getPreferences(MODE_PRIVATE);
		String access_token = mPrefs.getString("access_token", null);
		long expires = mPrefs.getLong("access_expires", 0);
		if (access_token != null) {
			facebook.setAccessToken(access_token);
		}
		if (expires != 0) {
			facebook.setAccessExpires(expires);
		}

		/*
		 * Only call authorize if the access_token has expired.
		 */
		if (!facebook.isSessionValid()) {

			facebook.authorize(this, new String[] { "publish_stream",
					"publish_actions" }, new DialogListener() {
				@Override
				public void onComplete(Bundle values) {
					SharedPreferences.Editor editor = mPrefs.edit();
					editor.putString("access_token", facebook.getAccessToken());
					editor.putLong("access_expires",
							facebook.getAccessExpires());
					editor.commit();
					sendRequests();
				}

				@Override
				public void onFacebookError(FacebookError error) {
				}

				@Override
				public void onError(DialogError e) {
				}

				@Override
				public void onCancel() {
				}
			});
		} else {
			sendRequests();
		}

		// EditText statusEditText = (EditText)
		// findViewById(R.id.statusEditText);
		// String status = statusEditText.getText().toString();

	}
	
	public void sendRequests() {
		
		Bundle params = new Bundle();

		params.putString("message", "Moja ścieżka w Centrum Nauki Kopernik.");

		String exhibitsIds[] = { "1_1", "1_2", "1_3", "1_4", "1_5", "1_6",
				"1_7", "1_8", "1_9", "1_10" };

		for (String exhibitId : exhibitsIds) {
			params.putString("exhibit",
					"http://wyspakreatywna.home.pl/copernicusExplorer/exhibits/show.php?id="
							+ exhibitId);
			// params.putByteArray("description",
			// "A Freshman College Girl on a scholarship from an ...".getBytes());

			asyncFacebookRunner.request("me/copernicusexplorer:explore",
					params, "POST", new AsyncFacebookRunner.RequestListener() {

						@Override
						public void onMalformedURLException(
								MalformedURLException e, Object state) {
							// TODO Auto-generated method stub
							e.printStackTrace();
						}

						@Override
						public void onIOException(IOException e, Object state) {
							// TODO Auto-generated method stub
							e.printStackTrace();
						}

						@Override
						public void onFileNotFoundException(
								FileNotFoundException e, Object state) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onFacebookError(FacebookError e,
								Object state) {
							Log.d("error", e.toString());

						}

						@Override
						public void onComplete(String response, Object state) {
						}
					}, params);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		params = new Bundle();
		String galleryIds[] = { "1", "2", "3" };

		for (String galleryId : galleryIds) {
			params.putString("gallery",
					"http://wyspakreatywna.home.pl/copernicusExplorer/galleries/show.php?id="
							+ galleryId);

			asyncFacebookRunner.request("me/copernicusexplorer:visit", params,
					"POST", new AsyncFacebookRunner.RequestListener() {

						@Override
						public void onMalformedURLException(
								MalformedURLException e, Object state) {
							// TODO Auto-generated method stub
							e.printStackTrace();
						}

						@Override
						public void onIOException(IOException e, Object state) {
							// TODO Auto-generated method stub
							e.printStackTrace();
						}

						@Override
						public void onFileNotFoundException(
								FileNotFoundException e, Object state) {
							// TODO Auto-generated method stub
							e.printStackTrace();
						}

						@Override
						public void onFacebookError(FacebookError e,
								Object state) {
							Log.d("error", e.toString());

						}

						@Override
						public void onComplete(String response, Object state) {
							Log.d("response", response);
						}
					}, params);

			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		params = new Bundle();
		params.putString("points", "10");

		params.putString("path",
				"http://wyspakreatywna.home.pl/copernicusExplorer/paths/show.php?id=1");

		asyncFacebookRunner.request("me/copernicusexplorer:pass", params,
				"POST", new AsyncFacebookRunner.RequestListener() {

					@Override
					public void onMalformedURLException(
							MalformedURLException e, Object state) {
						// TODO Auto-generated method stub
						e.printStackTrace();
					}

					@Override
					public void onIOException(IOException e, Object state) {
						// TODO Auto-generated method stub
						e.printStackTrace();
					}

					@Override
					public void onFileNotFoundException(
							FileNotFoundException e, Object state) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onFacebookError(FacebookError e, Object state) {
						Log.d("error", e.toString());

					}

					@Override
					public void onComplete(String response, Object state) {
					}
				}, params);

		Intent i = new Intent(getApplicationContext(), KopernikActivity.class);
		i.putExtra("showToast", true);
		startActivity(i);
		
	}
}