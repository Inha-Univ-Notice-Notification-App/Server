package inha.crawler.controller;

import inha.crawler.controller.dto.NoticesSaveRequestDto;
import inha.crawler.service.notices.NoticesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NoticesApiController {

    private final NoticesService noticesService;

    @PostMapping("/api/notices")
    public void save() {
        noticesService.updateNoticesList();
    }
}
