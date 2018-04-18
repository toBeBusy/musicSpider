package spider.service;

import org.jsoup.nodes.Element;
import spider.model.entity.ChartsEntity;

import java.util.Iterator;
import java.util.List;

public interface SpiderService {

    List<ChartsEntity> spiderFromWebService(Iterator<Element> it, List<ChartsEntity> chartsEntityList);
}
