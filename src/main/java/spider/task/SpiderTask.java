package spider.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import spider.model.entity.ChartsEntity;
import spider.service.impl.AbstractSpiderService;
import spider.service.impl.SongRangeDbServiceImpl;

import java.util.List;

@EnableScheduling
@Component
public class SpiderTask {


    @Autowired
    private SongRangeDbServiceImpl songRangeDbService;

    @Autowired
    @Qualifier("spiderSingleThreadService")
    private AbstractSpiderService spiderService;

    @Scheduled(cron="0 0/5 * * * ? ")
    public void doSpider(){
        List<ChartsEntity> list = spiderService.ChartsSpider();
         System.out.println(songRangeDbService.batchInsert(list) + "");
    }
}
