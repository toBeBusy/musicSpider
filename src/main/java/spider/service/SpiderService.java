package spider.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import spider.Util.HttpUtils;
import spider.entity.ChartsEntity;
import spider.entity.SongEntity;

public class SpiderService {
	
	private static final String chartsUrl = "http://music.163.com/discover/toplist/";
	
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
		while(it.hasNext()){
			Element element = it.next();
			if(checkRegular(element)){
				ChartsEntity entity = new  ChartsEntity();
				entity.setUrl("http://music.163.com" + element.attr("href"));
				entity.setChartsName(element.html());
				chartsEntityList.add(entity);
			}
		}
		return chartsEntityList;
	}
	
	/**
	 * 设置请求头
	 **/
	private void paramSeter(Connection connection){
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
	 * @param href
	 * @return
	 */
	private boolean checkRegular(Element element){
		//排行榜超链接的格式为"/discover/toplist?id=3779629"
		Pattern patt = Pattern. compile("/discover/toplist\\?id={1}[0-9]*");
		Matcher matcher = patt.matcher(element.attr("href"));
		return matcher.matches() && element.getElementsByTag("img").isEmpty();
	}
	
	/**
	 * 爬取某一排行榜下的歌曲
	 * @param chartsEntity
	 */
	public ChartsEntity songSpider(ChartsEntity chartsEntity){
		//获取链接
		Connection connection = Jsoup.connect("http://music.163.com/playlist?id=479363354");
		this.paramSeter(connection);
		String s = null;
		try {
			s = HttpUtils.getAjaxCotnent("http://music.163.com/playlist?id=479363354");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Document document = null;


		
		//上榜歌曲List
		List<SongEntity> songList = new ArrayList<SongEntity>();
		try {
			//获取页面全部元素
			document = connection.get();
			document = Jsoup.parse(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(null != document){
			//获取排行榜tbody
			Element tabElement =  document.getElementById("song-list-pre-cache");

			Elements test = document.select(".m-table m-table-rank");
			
			//			Elements test = tabElement.getElementsByTag("table");
			
			this.test(document);
			
			Element tableElement = document.getElementsByTag("table").get(0).
					getElementsByTag("tbody").get(0);
			//获取歌曲列表
			Elements elements = tableElement.getElementsByTag("tr");
			Iterator<Element> it = elements.iterator();
			while (it.hasNext()) {
				Element songElement = it.next();
				SongEntity entity = this.getSong(songElement);
				songList.add(entity);
			}
		}
		
		//设置返回值
		chartsEntity.setSongList(songList);
		return chartsEntity;
		
	}
	
	/**
	 * 从标签中提取歌曲信息;   烦。。。。
	 * @param args
	 */
	public SongEntity getSong(Element element){
		SongEntity entity = new SongEntity();
		//获取所有单元格
		Elements tdElements = element.getElementsByTag("td");
		
		//tdElements.get(0)第一个单元格。
		String numStr = tdElements.get(0).getElementsByTag("span").get(0).html();
		entity.setNum(Integer.valueOf(numStr));
		
		//tdElements.get(1)第二个单元格。
		String name = tdElements.get(1).getElementsByTag("b").get(0).html();
		entity.setName(name);
		
		//tdElements.get(2)第三个单元格。
		String time = tdElements.get(2).getElementsByTag("span").get(0).html();
		entity.setTime(time);
		
		//tdElements.get(3)第四个单元格。
		String singer = tdElements.get(3).getElementsByTag("a").get(0).html();
		entity.setSinger(singer);
		
		return entity;
	}
	
	
	
	private void test(Document document){
		File file = new File("D://test.html");
		
		try {
			FileOutputStream fi = new FileOutputStream(file);
			BufferedOutputStream bf = new BufferedOutputStream(fi);
			bf.write(document.toString().getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SpiderService service = new SpiderService();
		List<ChartsEntity> entityList = service.ChartsSpider();
		for (ChartsEntity chartsEntity : entityList) {
			ChartsEntity entity = service.songSpider(chartsEntity);
			System.out.println(entity);
		}
		System.out.println(entityList);
	}
}
