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
    public String noticesHeartPlus(@PathVariable("id") Long noticeId, HttpServletResponse response){
        noticesService.heartPlus(noticeId);
        try {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('해당 공지에 즐겨찾기 개수를 +1 했습니다'); history.go(-1);</script>");
            out.flush();
        } catch (IOException e){
            System.out.println("noticesHeartPlus 함수의 getWriter 부분에서 오류 발생");
        }
        return "redirect:/";
    }

    @GetMapping("/api/notices/heart/minus/{id}")
    public String noticesHeartMinus(@PathVariable("id") Long noticeId, HttpServletResponse response){
        noticesService.heartMinus(noticeId);
        try {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('해당 공지에 즐겨찾기 개수를 -1 했습니다'); history.go(-1);</script>");
            out.flush();
        } catch (IOException e){
            System.out.println("noticesHeartMinus 함수의 getWriter 부분에서 오류 발생");
        }
        return "redirect:/";
    }

}
