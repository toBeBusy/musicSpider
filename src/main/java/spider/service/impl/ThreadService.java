package spider.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class ThreadService {

    @Autowired
	ThreadPoolTaskExecutor executor;
	
	Log logger = LogFactory.getLog(ThreadService.class);
	

	/**
	 * @param executor the executor to set
	 */
	public void setExecutor(ThreadPoolTaskExecutor executor) {
		this.executor = executor;
	}

	public void doTask(Runnable runnable){
		executor.execute(runnable, 10000);
	}
}
