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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

    @GetMapping("/api/notices/heart/plus/{id}")
    public ResponseEntity<Notices> noticesHeartPlus(@PathVariable("id") Long noticeId, HttpServletResponse response){
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return new ResponseEntity<Notices>(noticesService.heartPlus(noticeId),header,HttpStatus.OK);
    }

    @GetMapping("/api/notices/heart/minus/{id}")
    public ResponseEntity<Notices> noticesHeartMinus(@PathVariable("id") Long noticeId, HttpServletResponse response){
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return new ResponseEntity<Notices>(noticesService.heartMinus(noticeId),header,HttpStatus.OK);
    }

}
