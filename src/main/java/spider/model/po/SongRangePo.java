package spider.model.po;

public class SongRangePo {
    private long id;

    private int sort;

    private String songName;

    private long songTime;

    private String singer;

    private String ranking;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public long getSongTime() {
        return songTime;
    }

    public void setSongTime(long songTime) {
        this.songTime = songTime;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    @Override
    public String toString() {
        return "SongRangePo{" +
                "id=" + id +
                ", sort=" + sort +
                ", songName='" + songName + '\'' +
                ", songTime=" + songTime +
                ", singer='" + singer + '\'' +
                ", ranking='" + ranking + '\'' +
                '}';
    }
}
