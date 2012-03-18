package pl.org.kopernik.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Quize {
	public String question_text;
	public ArrayList<String> answers;
	public int valid_answer;

	public Quize(String question_text, ArrayList<String> answers, int valid_answer){
		this.question_text = question_text;
		this.answers = answers;
		this.valid_answer = valid_answer;
	}
	
	public Quize(JSONObject quizeObject) {
		try {
			question_text = quizeObject.getString("question_text");
			answers = new ArrayList<String>();
			JSONArray answersJSONArray = quizeObject.getJSONArray("answers");
			for (int i = 0; i < answersJSONArray.length(); ++i){
				answers.add(answersJSONArray.getString(i));
			}
			valid_answer = quizeObject.getInt("valid_answer");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	JSONObject returnAsJSON(){
		JSONObject quizeJSONObject = new JSONObject();
		try {
			quizeJSONObject.put("question_text", question_text);
			quizeJSONObject.put("answers", new JSONArray(answers));
			quizeJSONObject.put("valid_answer", valid_answer);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return quizeJSONObject;

	}
}
