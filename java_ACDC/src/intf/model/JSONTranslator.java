package intf.model;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import main.Post;

public class JSONTranslator {

	public static Post getPostFromJSON(String postJSON){
		Gson gson = new Gson();
		
		Type type = new TypeToken<Map<String, String>>(){}.getType();
		Map<String, String> postMap = gson.fromJson(postJSON, type);

		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		return new Post(postMap.get("title"), date, postMap.get("categories"), postMap.get("content"), postMap.get("author"));
	}
	
	public static List<String> getStringListFromJSON(String listJSON){
		Gson gson = new Gson();
		
		Type type = new TypeToken<List<String>>(){}.getType();
		List<String> list = gson.fromJson(listJSON, type);

		return list;
	}
	
}
