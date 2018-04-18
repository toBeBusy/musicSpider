package spider.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.springframework.stereotype.Service;
import spider.Util.JsonUtil;
import spider.model.entity.ChartsEntity;
import spider.model.entity.SongEntity;
import spider.service.SpiderService;

public abstract class AbstractSpiderService implements SpiderService {
	
	private String chartsUrl = "http://music.163.com/discover/toplist/";

	private static Log logger = LogFactory.getLog(ThreadService.class);

    public abstract List<ChartsEntity> spiderFromWebService(Iterator<Element> it,List<ChartsEntity> chartsEntityList);
	
	/**
	 * @return the chartsUrl
	 */
	public void setChartsUrl(String chartsUrl) {
		this.chartsUrl = chartsUrl;
	}
	/**
	 * 爬取排行榜实体类
	 */
	public List<ChartsEntity> ChartsSpider(){
		//爬取结果
		List<ChartsEntity> chartsEntityList = new ArrayList<ChartsEntity>();
		//获取链接
		Connection connection = Jsoup.connect(chartsUrl);
		//设置链接参数
		this.paramSeter(connection);
		
		Document document = null;
		try {
			//获取返回的html文件
			document = connection.get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//获取所有的超链接
		Elements elements = document.getElementsByTag("a");
		Iterator<Element> it = elements.iterator();

		spiderFromWebService(it, chartsEntityList);
		
		return chartsEntityList;
	}
	
	/**
	 * 设置请求头
	 **/
	protected void paramSeter(Connection connection){
		connection.header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		connection.header("Accept-Encoding", "gzip, deflate, sdch");
		connection.header("Accept-Language", "zh-CN,zh;q=0.8");
		connection.header("Connection", "keep-alive");
		connection.header("Host", "music.163.com");
		connection.header("Referer", "http://music.163.com/");
		connection.header("Upgrade-Insecure-Requests", "1");
		connection.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36");
		connection.cookie("JSESSIONID-WYYY", "994e65d00d8b0c36ce913ca37bab10ad3869f40f5a43fe90e8515ca3a013f570a22dc361c38f16f4ecb7e03ff942ec57ecdc260f938bc1d49d732caacfb7772c2b19686a87b8b33d7facccf48d224da5b312b60fa6eb57f461341f67390f6361278f26786e9f3667e1800fec1372804008ad9c1584e9cdd3a5bdb376e573fdc15659e288%3A1475301121073");
		connection.cookie("_iuqxldmzr_","25");
		connection.cookie("__utma","94650624.1768368046.1475299324.1475299324.1475300885.2");
		connection.cookie("__utmb","94650624.4.10.1475300885");
		connection.cookie("__utmc","94650624");
		connection.cookie("__utmz","94650624.1475300885.2.2.utmcsr=baidu|utmccn=(organic)|utmcmd=organic");
		connection.cookie("_ntes_nnid","a96d908bf2183997b0f770db41c95565,1475300954822");
		connection.cookie("_ntes_nuid","a96d908bf2183997b0f770db41c95565");
	}
	
	/**
	 * 判断该链接是否为排行榜超链接
	 * @param element
	 * @return
	 */
	protected boolean checkRegular(Element element){
		//排行榜超链接的格式为"/discover/toplist?id=3779629"
		Pattern patt = Pattern. compile("/discover/toplist\\?id={1}[0-9]*");
		Matcher matcher = patt.matcher(element.attr("href"));
		return matcher.matches() && element.getElementsByTag("img").isEmpty();
	}

    class SongSpiderTask implements Runnable{
		private ChartsEntity chartsEntity;
		
		private CountDownLatch countDownLatch;
		
		/**
		 * @return the entity
		 */
		public ChartsEntity getChartsEntity() {
			return chartsEntity;
		}

		/**
		 * @param entity the entity to set
		 */
		public void setChartsEntity(ChartsEntity entity) {
			this.chartsEntity = entity;
		}
		
		/**
		 * @return the countDownLatch
		 */
		public CountDownLatch getCountDownLatch() {
			return countDownLatch;
		}

		/**
		 * @param countDownLatch the countDownLatch to set
		 */
		public void setCountDownLatch(CountDownLatch countDownLatch) {
			this.countDownLatch = countDownLatch;
		}

		public SongSpiderTask(ChartsEntity chartsEntity, CountDownLatch countDownLatch){
			this.chartsEntity = chartsEntity;
			this.countDownLatch = countDownLatch;
		}

		public void run() {
			//获取链接
			Connection connection = Jsoup.connect(chartsEntity.getUrl());
			paramSeter(connection);
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
			
			countDownLatch.countDown();
		}
		
	}

}
