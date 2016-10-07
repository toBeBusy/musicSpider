package spider.service;

import java.io.File;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import spider.entity.ExcleEntity;

/**
 * 
 * @author Administrator
 *
 */
public class ExcleProduceService {
	
	/**
	 * excle地址
	 */
	private String url = "d:/test.xls";
	
	/**
	 * 创建excle的方法。
	 * @param excleEntity
	 */
	@SuppressWarnings("resource")
	public void createXls(ExcleEntity excleEntity) {
		//
		HSSFWorkbook workBook = new HSSFWorkbook();
		//创建一个sheet
		workBook.createSheet("第一个sheet");
		try {
			workBook.write(new File(url));
		} catch (IOException e) {
			System.out.println("创建文件失败。");
			e.printStackTrace();
		}
	}
	
}
