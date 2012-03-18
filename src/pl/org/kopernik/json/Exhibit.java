package pl.org.kopernik.json;

import org.json.JSONException;
import org.json.JSONObject;

public class Exhibit {
	public int path_id;
	public int id;
	public String name;
	public String description;
	public String info;
	public Quize question;
	public int gallery;
	
	public Exhibit(int path_id, int id, String name,
			String description, String info, Quize question, int gallery){
		
		this.path_id = path_id;
		this.id =id;
		this.name = name;
		this.description = description;
		this.info = info;
		this.question = question;
		this.gallery = gallery;
		
	}
	
	
	public Exhibit(JSONObject exhibit_JSON) {
		try {
			path_id = exhibit_JSON.getInt("path_id");
			id = exhibit_JSON.getInt("id");
			name = exhibit_JSON.getString("name");
			description = exhibit_JSON.getString("description");
			info = exhibit_JSON.getString("info") ;
			question = new Quize(exhibit_JSON.getJSONObject("question"));
			gallery = exhibit_JSON.getInt("gallery");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public JSONObject returnAsJSON(){
		JSONObject exhibitJSONObject = new JSONObject();
		
		try {
			exhibitJSONObject.put("path_id",path_id);
			exhibitJSONObject.put("id",id);
			exhibitJSONObject.put("name",name);
			exhibitJSONObject.put("description",description);
			exhibitJSONObject.put("info",info);
			exhibitJSONObject.put("question", question.returnAsJSON());
			exhibitJSONObject.put("gallery", gallery);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return exhibitJSONObject;
		
	}
	

}
