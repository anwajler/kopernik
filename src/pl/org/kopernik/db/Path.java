package pl.org.kopernik.db;

import pl.org.kopernik.app.KopernikApplication;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

public class Path {
	static String tableName = "path";

	// attributes
	int id;
	String name;	
	String description;
	String map_uri;


	public static String getTableCreateSQL() {		
		return new String("CREATE TABLE " + tableName + " (" +
            "id INTEGER, " +
            "name TEXT, " +            
            "description TEXT, " +
            "map_uri TEXT);");						
	}
	
	public Path(JSONObject pathObject) throws JSONException {			
		id = pathObject.getInt("id");
		name = pathObject.getString("name");
		description = pathObject.getString("description");
		map_uri = pathObject.getString("map_uri");
		insert_or_update();
	}
	
	public Path(int id, String name, String description, String map_uri, boolean insert) throws JSONException {					
		this.id = id;
		this.name = name;	
		this.description = description;
		this.map_uri = map_uri;

		if(insert == true) {
			insert_or_update();
		}
	}	
	
	public long insert_or_update() {
		String[] columns = {"id"};
		Log.d("path id", id+"");
		String[] selectionArgs = {Integer.toString(id)};
		Cursor c = KopernikApplication.db().query(tableName, columns, "id = ?", selectionArgs, null, null, null);		
		ContentValues contentValues = getContentValues();
		long recordId;
		if(c.getCount() == 0){
			// insert			
			recordId = KopernikApplication.db().insert(tableName, null, contentValues);
		} else {
			// update
			recordId = KopernikApplication.db().update(tableName, contentValues, "id = ?", selectionArgs);
		}
		c.close();
		return recordId;
	}
	
	public ContentValues getContentValues() {		
		ContentValues contentValues = new ContentValues();
		contentValues.put("id", id);
		contentValues.put("name", description);
		contentValues.put("description", description);
		contentValues.put("map_uri", map_uri);

		return contentValues;
	}		
	
	public static void addAll(String jsonContent) throws JSONException{
		JSONArray postArray = new JSONArray(jsonContent);
		Log.d("db.path", Integer.toString(postArray.length()));
		for(int i = 0; i < postArray.length(); i++) {
			JSONObject pathObject = postArray.getJSONObject(i).getJSONObject("post");
			pathObject.toString();
			Log.d("db.path", pathObject.toString());
			new Path(pathObject);
		}
	}
	
	public static ArrayList<Path> all() throws JSONException {
		return getAll("created_at desc");
	}

	private static ArrayList<Path> getAll(String orderedBy)
			throws JSONException {
		Cursor c = KopernikApplication.db().query(tableName, null, null, null,
				null, null, orderedBy);
		ArrayList<Path> result = new ArrayList<Path>();
		c.moveToFirst();
		for (; c.isAfterLast() == false;) {

			int id = c.getInt(0);
			String name = c.getString(1);
			String description = c.getString(2);
			String map_uri = c.getString(3);


			result.add(new Path(id, name, description, map_uri, false));
			c.moveToNext();
		}
		Log.d("mobi.apparty", result.size() + "");
		return result;
	}
	
	public static Path find(int path_id) throws JSONException {
		String[] selectionArgs = { Integer.toString(path_id) };
		Cursor c = KopernikApplication.db().query(tableName, null, "id = ?",
				selectionArgs, null, null, null);
		if (c.getCount() == 1) {
			c.moveToFirst();
			String name = c.getString(1);
			String description = c.getString(2);
			String map_uri = c.getString(3);
			String picture_url = c.getString(5);			
			return new Path(path_id, name, description, map_uri, false);
		} else {
			return null;
		}
	}

	
}