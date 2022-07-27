package inha.crawler.controller;

import inha.crawler.controller.dto.NoticesListResponseDto;
import inha.crawler.controller.dto.NoticesSaveRequestDto;
import inha.crawler.domain.notices.Notices;
import inha.crawler.service.notices.NoticesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class NoticesApiController {

    private final NoticesService noticesService;

    @PostMapping("/api/notices")
    public void save() {
        noticesService.updateNoticesList();
    }

    @GetMapping("/api/notices/all")
    public ResponseEntity<List<NoticesListResponseDto>> notices_all(){
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return new ResponseEntity<List<NoticesListResponseDto>>(noticesService.findAll(),header, HttpStatus.OK);
    }
}
