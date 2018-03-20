package spider.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spider.dao.SongRangeDao;
import spider.model.entity.ChartsEntity;
import spider.model.po.SongRangePo;
import spider.service.SongRangeDbService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongRangeDbServiceImpl implements SongRangeDbService {

    @Autowired
    private SongRangeDao songRangeDao;

    @Override
    public int batchInsert(List<ChartsEntity> chartsEntityList) {
        return songRangeDao.batchInsert(this.dataConvert(chartsEntityList));
    }

    private List<SongRangePo> dataConvert(List<ChartsEntity> chartsEntityList){
        List<SongRangePo> poList = new ArrayList<>();
        chartsEntityList.stream().forEach(item -> {
            item.getSongList().stream().forEach(song -> {
                SongRangePo po = new SongRangePo();
                po.setSongName(song.getName());
                po.setSinger(song.getArtists().toString());
                po.setRanking(item.getChartsName());
                po.setSongTime(song.getDuration());
                po.setSort(song.getNo());
                poList.add(po);
            });
        });
        return poList;
    }
}
