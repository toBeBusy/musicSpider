package spider.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * excle模板类
 * @author Administrator
 *
 */
public class ExcleEntity {
	
	private List<String> titles;
	
	public ExcleEntity(){
		titles = new ArrayList<String>();
		titles.add("排名");
		titles.add("歌曲名");
		titles.add("时长");
		titles.add("歌手");
	}

	/**
	 * @return the titles
	 */
	public List<String> getTitles() {
		return titles;
	}

	/**
	 * @param titles the titles to set
	 */
	public void setTitles(List<String> titles) {
		this.titles = titles;
	}
	
}
