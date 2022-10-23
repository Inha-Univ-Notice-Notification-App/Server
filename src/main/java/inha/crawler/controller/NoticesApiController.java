package inha.crawler.controller;

import inha.crawler.controller.dto.NoticesListResponseDto;
import inha.crawler.controller.dto.NoticesSaveRequestDto;
import inha.crawler.domain.notices.Notices;
import inha.crawler.service.notices.NoticesService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(description = "공지 정보를 POST 방식으로 넘겨주면 직접 추가")
    @PostMapping("/api/notices")
    public void save() {
        noticesService.updateNoticesList();
    }

    @Operation(description = "공지 전체 조회")
    @GetMapping("/api/notices/all")
    public ResponseEntity<List<NoticesListResponseDto>> notices_all(){
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return new ResponseEntity<List<NoticesListResponseDto>>(noticesService.findAll(),header, HttpStatus.OK);
    }

    @Operation(description = "공지 id를 넘겨주면 해당 공지 좋아요 개수 +1")
    @GetMapping("/api/notices/heart/plus/{id}")
    public ResponseEntity<Notices> noticesHeartPlus(@PathVariable("id") Long noticeId, HttpServletResponse response){
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return new ResponseEntity<Notices>(noticesService.heartPlus(noticeId),header,HttpStatus.OK);
    }

    @Operation(description = "공지 id를 넘겨주면 해당 공지 좋아요 개수 -1")
    @GetMapping("/api/notices/heart/minus/{id}")
    public ResponseEntity<Notices> noticesHeartMinus(@PathVariable("id") Long noticeId, HttpServletResponse response){
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return new ResponseEntity<Notices>(noticesService.heartMinus(noticeId),header,HttpStatus.OK);
    }

}
