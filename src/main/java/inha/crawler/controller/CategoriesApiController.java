package inha.crawler.controller;

import inha.crawler.controller.dto.CategoriesSaveRequestDto;
import inha.crawler.service.categories.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CategoriesApiController {
    private final CategoriesService categoriesService;

    @PostMapping("/api/categories")
    public void save(@RequestBody CategoriesSaveRequestDto requestDto){
        categoriesService.save(requestDto);
    }
}
