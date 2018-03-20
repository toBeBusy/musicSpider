package spider.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import spider.model.po.SongRangePo;

import java.util.List;

@Mapper
public interface SongRangeDao {

    List<SongRangePo> querySongRangeList();

    int batchInsert(@Param("list") List<SongRangePo> list);
}
