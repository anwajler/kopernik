package pl.org.kopernik.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R;
import android.util.Log;

import pl.org.kopernik.app.KopernikApplication;

public class WaterPath {
	
	public Path path;
	String pathJSONString;
	JSONArray pathJSON;

	
	public WaterPath(){
		
		readJSONfromFile();
		try {
			pathJSON = new JSONArray(pathJSONString);
			path = new Path(pathJSON);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		

			
	}
	
	
	private void readJSONfromFile(){
		InputStream is = null;
		try {
			is = KopernikApplication.context().getAssets().open("water_path.json");
			Writer writer = new StringWriter();
			char[] buffer = new char[1024];
		    Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		    int n;
		    while ((n = reader.read(buffer)) != -1) {
		        writer.write(buffer, 0, n);
		    }
		    pathJSONString = writer.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally {
		    try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
