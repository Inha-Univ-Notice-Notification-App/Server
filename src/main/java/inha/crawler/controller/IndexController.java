package inha.crawler.controller;

import inha.crawler.controller.dto.CategoriesSaveRequestDto;
import inha.crawler.service.categories.CategoriesService;
import inha.crawler.service.notices.NoticesService;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
@Controller
public class IndexController {

    public final NoticesService noticesService;
    private final CategoriesService categoriesService;


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/category/new")
    public String categorySave(){
        return "category-save";
    }

    @PostMapping("/category/new")
    public String categorySave(CategoriesSaveRequestDto requestDto) {
        categoriesService.save(requestDto);
        return "redirect:/";
    }

    @GetMapping("/crawling")
    public String crawling(HttpServletResponse response){
        noticesService.updateNoticesList();

        try {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('크롤링이 완료되었습니다.'); history.go(-1);</script>");
            out.flush();
        } catch (IOException e){
            System.out.println("crawling 함수의 getWriter 부분에서 오류 발생");
        }
        return "redirect:/";
    }

    @GetMapping("/category/list")
    public String categoriesList(Model model){
        model.addAttribute("categories", categoriesService.findAll());
        return "categories-list";
    }

    @GetMapping("/notice/list")
    public String noticesList(Model model) {
        model.addAttribute("notices",noticesService.findAll());
        return "notices-list";
    }
}
