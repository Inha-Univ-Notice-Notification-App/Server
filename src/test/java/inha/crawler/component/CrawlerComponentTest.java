package inha.crawler.component;

import inha.crawler.service.notices.NoticesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CrawlerComponentTest {
    @Autowired
    NoticesService noticesService;

    @Test
    public void 크롤러_출력() {
        //noticesService.getNoticesList();
    }
}
