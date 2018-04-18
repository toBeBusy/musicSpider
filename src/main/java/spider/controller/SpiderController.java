package spider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spider.dao.SongRangeDao;
import spider.model.entity.ChartsEntity;
import spider.model.po.SongRangePo;
import spider.service.impl.AbstractSpiderService;
import spider.service.impl.SongRangeDbServiceImpl;

import java.util.List;

@RestController
public class SpiderController {

    @Autowired
    private SongRangeDbServiceImpl songRangeDbService;

    @Autowired
    @Qualifier("spiderSingleThreadService")
    private AbstractSpiderService spiderService;

    @RequestMapping("/test")
    public String test(){
        List<ChartsEntity> list = spiderService.ChartsSpider();
        return songRangeDbService.batchInsert(list) + "";
    }
}
