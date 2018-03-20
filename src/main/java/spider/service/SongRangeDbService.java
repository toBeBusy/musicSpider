package spider.service;

import spider.model.entity.ChartsEntity;

import java.util.List;

public interface SongRangeDbService {

    int batchInsert(List<ChartsEntity> chartsEntityList);
}
