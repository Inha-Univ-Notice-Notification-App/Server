package inha.crawler.controller;

import inha.crawler.controller.dto.CategoriesSaveRequestDto;
import inha.crawler.service.categories.CategoriesService;
import inha.crawler.service.notices.NoticesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String crawling(){
        noticesService.updateNoticesList();

        return "redirect:/";
    }
}
