package spider.model.entity;

import java.util.List;

/**
 * 歌曲实体类
 * @author Administrator
 *
 */
public class SongEntity {
	
	private int score;
	
	private int copyrightId;

	private List<String> transNames;

	private String commentThreadId;
	
	private int fee;

	private int ftype;
	
	private int no;

	private int type;

	private int duration;

	private int status;

	private Privilege privilege;

	private int djid;
	
	private Album album;
	
	private List<Artist> artists;

	private List<Object> alias;

	private String name;
	
	private int id;

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return the copyrightId
	 */
	public int getCopyrightId() {
		return copyrightId;
	}

	/**
	 * @param copyrightId the copyrightId to set
	 */
	public void setCopyrightId(int copyrightId) {
		this.copyrightId = copyrightId;
	}

	/**
	 * @return the transNames
	 */
	public List<String> getTransNames() {
		return transNames;
	}

	/**
	 * @param transNames the transNames to set
	 */
	public void setTransNames(List<String> transNames) {
		this.transNames = transNames;
	}

	/**
	 * @return the commentThreadId
	 */
	public String getCommentThreadId() {
		return commentThreadId;
	}

	/**
	 * @param commentThreadId the commentThreadId to set
	 */
	public void setCommentThreadId(String commentThreadId) {
		this.commentThreadId = commentThreadId;
	}

	/**
	 * @return the fee
	 */
	public int getFee() {
		return fee;
	}

	/**
	 * @param fee the fee to set
	 */
	public void setFee(int fee) {
		this.fee = fee;
	}

	/**
	 * @return the ftype
	 */
	public int getFtype() {
		return ftype;
	}

	/**
	 * @param ftype the ftype to set
	 */
	public void setFtype(int ftype) {
		this.ftype = ftype;
	}

	/**
	 * @return the no
	 */
	public int getNo() {
		return no;
	}

	/**
	 * @param no the no to set
	 */
	public void setNo(int no) {
		this.no = no;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the privilege
	 */
	public Privilege getPrivilege() {
		return privilege;
	}

	/**
	 * @param privilege the privilege to set
	 */
	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	/**
	 * @return the djid
	 */
	public int getDjid() {
		return djid;
	}

	/**
	 * @param djid the djid to set
	 */
	public void setDjid(int djid) {
		this.djid = djid;
	}

	/**
	 * @return the album
	 */
	public Album getAlbum() {
		return album;
	}

	/**
	 * @param album the album to set
	 */
	public void setAlbum(Album album) {
		this.album = album;
	}

	/**
	 * @return the artists
	 */
	public List<Artist> getArtists() {
		return artists;
	}

	/**
	 * @param artists the artists to set
	 */
	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}


	/**
	 * @return the alias
	 */
	public List<Object> getAlias() {
		return alias;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(List<Object> alias) {
		this.alias = alias;
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
	
	
	
}
