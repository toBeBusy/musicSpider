package spider.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ThreadService {
	
	ExecutorService executorService;
	
	Log logger = LogFactory.getLog(ThreadService.class);

	public void test(){
		ExecutorService executorService = Executors.newCachedThreadPool();
	}
}
