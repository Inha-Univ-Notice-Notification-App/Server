package inha.crawler.service.notices;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NoticesServiceTest {

    @Autowired
    NoticesService noticesService;
    @Test
    public void 공지목록업데이트(){
        noticesService.updateNoticesList();
    }
}
