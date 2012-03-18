package pl.org.kopernik.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Path {
	
	public ArrayList<Exhibit> exhibits;
	
	public Path(){
		exhibits = new ArrayList<Exhibit>();
		
	}
	
	public Path(JSONArray path){
		exhibits = new ArrayList<Exhibit>();
		for(int i=0; i < path.length(); ++i ){
			try {
				JSONObject exhibit_JSON = path.getJSONObject(i);
				Exhibit exhibit = new Exhibit(exhibit_JSON);
				exhibits.add(exhibit);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}
		
	}
	
	public void addExhibit(Exhibit exhibit){
		exhibits.add(exhibit);
	}
	
	public JSONArray returnAaJSON(){
		JSONArray exhibitsJSONArray = new JSONArray();
		for (Exhibit exhibit : exhibits){
			exhibitsJSONArray.put(exhibit.returnAsJSON());
		}
		return exhibitsJSONArray;
	}
	

}
