package spider.service.impl;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spider.Util.JsonUtil;
import spider.model.entity.ChartsEntity;
import spider.model.entity.SongEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("spiderSingleThreadService")
public class SpiderSingleThreadService extends AbstractSpiderService{

    @Override
    public List<ChartsEntity> spiderFromWebService(Iterator<Element> it, List<ChartsEntity> chartsEntityList) {
        while(it.hasNext()){
            Element element = it.next();
            if(checkRegular(element)){
                ChartsEntity entity = new  ChartsEntity();
                entity.setUrl("http://music.163.com" + element.attr("href"));
                entity.setChartsName(element.html());
                //爬取排行榜歌曲信息
                this.songSpider(entity);
                chartsEntityList.add(entity);
            }
        }
        return chartsEntityList;
    }

    /**
     * 爬取某一排行榜下的歌曲
     * @param chartsEntity
     */
    public ChartsEntity songSpider(ChartsEntity chartsEntity){
        //获取链接
        Connection connection = Jsoup.connect(chartsEntity.getUrl());
        this.paramSeter(connection);
        Document document = null;

        //上榜歌曲List
        List<SongEntity> songList = new ArrayList<SongEntity>();
        try {
            //获取页面全部元素
            document = connection.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(null != document){
            //获取歌曲信息
            Element textareaElement = document.getElementsByTag("textarea").get(0);
            songList = JsonUtil.getObject(textareaElement.html());
        }
        //设置返回值
        chartsEntity.setSongList(songList);
        return chartsEntity;
    }
}
