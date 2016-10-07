package spider.entity;

import java.util.List;

/**
 * 排行榜实体类
 * @author Administrator
 *
 */
public class ChartsEntity {
	/**
	 * 排行榜名称
	 */
	private String chartsName;
	
	/**
	 * 排行榜链接
	 */
	private String url;
	
	/**
	 * 上榜歌曲
	 */
	private List<SongEntity> songList;

	public String getChartsName() {
		return chartsName;
	}

	public void setChartsName(String chartsName) {
		this.chartsName = chartsName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "ChartsEntity [chartsName=" + chartsName + ", url=" + url + "]";
	}

	/**
	 * @return the songList
	 */
	public List<SongEntity> getSongList() {
		return songList;
	}

	/**
	 * @param songList the songList to set
	 */
	public void setSongList(List<SongEntity> songList) {
		this.songList = songList;
	}
	
	
}
