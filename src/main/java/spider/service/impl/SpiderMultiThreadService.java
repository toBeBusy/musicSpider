package spider.service.impl;

import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spider.model.entity.ChartsEntity;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Service("spiderMultiThreadService")
public class SpiderMultiThreadService extends AbstractSpiderService{

    @Autowired
    private ThreadService threadService;

    /**
     * 多线程版爬虫，用CountDownLatch控制
     * @param it
     * @param chartsEntityList
     */
    @Override
    public List<ChartsEntity> spiderFromWebService(Iterator<Element> it, List<ChartsEntity> chartsEntityList) {
        while(it.hasNext()){
            Element element = it.next();
            if(checkRegular(element)){
                ChartsEntity entity = new  ChartsEntity();
                entity.setUrl("http://music.163.com" + element.attr("href"));
                entity.setChartsName(element.html());
                //爬取排行榜歌曲信息
                chartsEntityList.add(entity);
            }
        }
        //爬虫线程条数，要等所有的爬虫爬完才继续。
        CountDownLatch countDownLatch = new CountDownLatch(chartsEntityList.size());
        for(ChartsEntity entity :chartsEntityList){
            threadService.doTask(new SongSpiderTask(entity, countDownLatch));
        }
        //等所有的爬虫都爬完
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return chartsEntityList;
    }
}
