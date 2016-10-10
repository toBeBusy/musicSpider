package spider.entity;

public class Album {
	
	private int id;
	
	private String name;
	
	private String picUrl;
	
	private String pic_str;
	
	private long pic;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the picUrl
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * @param picUrl the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	/**
	 * @return the pic_str
	 */
	public String getPic_str() {
		return pic_str;
	}

	/**
	 * @param pic_str the pic_str to set
	 */
	public void setPic_str(String pic_str) {
		this.pic_str = pic_str;
	}

	/**
	 * @return the pic
	 */
	public long getPic() {
		return pic;
	}

	/**
	 * @param pic the pic to set
	 */
	public void setPic(long pic) {
		this.pic = pic;
	}
	
	
}
