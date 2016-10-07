package spider.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpUtils {

	public static String getAjaxCotnent(String url) throws IOException {  
        Runtime rt = Runtime.getRuntime();
        
        String[] strArray = new String[5];
        strArray[0] = "cmd.exe";
        strArray[1] = "/C";
        strArray[2] = "phantomjs.exe";
        strArray[3] = "D:/phantomjs/codes.js";
        strArray[4] = url;
        
        Process p = rt.exec(strArray);//这里我的codes.js是保存在c盘下面的phantomjs目录
        InputStream is = p.getInputStream();  
        BufferedReader br = new BufferedReader(new InputStreamReader(is));  
        StringBuffer sbf = new StringBuffer();  
        String tmp = "";  
        while((tmp = br.readLine())!=null){  
            sbf.append(tmp);  
        }  
        System.out.println(sbf.toString());  
        return sbf.toString();  
    } 
	
	public static void main(String[] args) throws IOException {  
        System.out.println(getAjaxCotnent("http://music.163.com/#/discover/toplist"));
    }  
}
