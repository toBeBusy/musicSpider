package spider.entity;

/**
 * 歌曲实体类
 * @author Administrator
 *
 */
public class SongEntity {
	//排名
	private int num;
	
	//歌曲名
	private String name;
	
	//歌手名
	private String singer;
	
	//时常
	private String time;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
