package spider.task;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class SpiderTask {

    @Scheduled()
    public void doSpider(){

    }
}
