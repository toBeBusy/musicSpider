package spider.Util;

import java.util.List;

import spider.model.entity.SongEntity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtil {
	private static Gson gson = new Gson();
	
	public static List<SongEntity> getObject(String jsonString){
		List<SongEntity> entitys = gson.fromJson(jsonString, new TypeToken<List<SongEntity>>(){}.getType());
		return entitys;
	}
}
