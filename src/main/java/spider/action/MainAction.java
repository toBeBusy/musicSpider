package spider.action;

import java.util.List;

import spider.model.entity.ChartsEntity;
import spider.service.ExcleProduceService;
import spider.service.SpiderService;

public class MainAction {
	
	private ExcleProduceService excleProduceService;
	
	private SpiderService spiderService;

	/**
	 * @param excleProduceService the excleProduceService to set
	 */
	public void setExcleProduceService(ExcleProduceService excleProduceService) {
		this.excleProduceService = excleProduceService;
	}

	/**
	 * @param spiderService the spiderService to set
	 */
	public void setSpiderService(SpiderService spiderService) {
		this.spiderService = spiderService;
	}
	
	public void spider(){
		 List<ChartsEntity> chartsList = spiderService.ChartsSpider();
		 excleProduceService.createRegularSheet(chartsList);
	}
	
//	public static void main(String[] args) {
//		ApplicationContext ac = new FileSystemXmlApplicationContext("src/main/resources/spring.xml");
//		MainAction mainAction = (MainAction) ac.getBean("mainAction");
//		mainAction.spider();
//	}
}
