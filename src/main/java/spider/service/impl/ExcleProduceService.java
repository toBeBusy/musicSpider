package spider.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import spider.model.entity.Artist;
import spider.model.entity.ChartsEntity;
import spider.model.entity.ExcleEntity;
import spider.model.entity.SongEntity;


/**
 * 
 * @author Administrator
 * 
 */
public class ExcleProduceService {

	/**
	 * excle地址
	 */
	private String url = "/home/qiupeng1990/网易云音乐排行榜.xls";

	/**
	 * 创建excle的方法。
	 * 
	 * @param excleEntity
	 */
	private void createTitle(ExcleEntity excleEntity, HSSFWorkbook workBook, int num) {
		HSSFRow row = workBook.getSheetAt(num).createRow(0);
		int i = 0;
		for (String title : excleEntity.getTitles()) {
			HSSFCellStyle cellStyle =  workBook.createCellStyle();
			HSSFFont font = workBook.createFont(); 
			font.setBold(true);
			cellStyle.setFont(font);
			row.setRowStyle(cellStyle);
			row.createCell(i).setCellValue(title);
			i++;
		}
	}

	/**
	 * 创建歌曲条目
	 * 
	 * @param excleEntity
	 * @param workBook
	 */
	private void createSongRow(SongEntity songEntity, HSSFWorkbook workBook,
			int sheetNum,int rowNum) {
		HSSFRow row = workBook.getSheetAt(sheetNum).createRow(rowNum);

		row.createCell(0).setCellValue(rowNum);
		row.createCell(1).setCellValue(songEntity.getName());
		row.createCell(2).setCellValue(this.getTime(songEntity.getDuration()));
		row.createCell(3).setCellValue(this.getSinger(songEntity.getArtists()));
	}

	/**
	 * 计算时间
	 * 
	 * @param time
	 * @return
	 */
	private String getTime(int time) {
		time = time / 1000;
		return time / 60 + "分" + time % 60 + "秒";
	}

	private String getSinger(List<Artist> artists) {
		StringBuilder sb = new StringBuilder();
		for (Artist artist : artists) {
			sb.append(artist.getName() + ";");
		}
		return sb.toString().substring(0, sb.length()-1);
	}

	/**
	 * 创建excle
	 */
	public void createRegularSheet(List<ChartsEntity> chartsList) {
		HSSFWorkbook workBook = null;
		File file = new File(url);
		// 获取excle文件
		workBook = new HSSFWorkbook();
			
		for(int i = 0; i < chartsList.size(); i++){
			ChartsEntity entity = chartsList.get(i);
			// 创建sheet
			workBook.createSheet(entity.getChartsName());
			// 第一行
			this.createTitle(new ExcleEntity(), workBook, i);
			List<SongEntity> songList = entity.getSongList();
			// 塞值
			for (int j = 0; j < songList.size(); j++) {
				this.createSongRow(songList.get(j), workBook, i, j + 1);
			}
		}
		try {
			//生成文件
			workBook.write(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
