package inha.crawler.controller.dto;

import inha.crawler.domain.categories.Categories;
import lombok.Getter;

@Getter
public class CategoriesListResponseDto {
    private Long id;
    private String page;
    private String tag;
    private String url;
    private String noticeUrl;


    public CategoriesListResponseDto(Categories entity) {
        this.id = entity.getId();
        this.page = entity.getPage();
        this.tag = entity.getTag();
        this.url = entity.getUrl();
        this.noticeUrl = entity.getNoticeUrl();
    }

    public boolean isSchoolCategory() {
        return this.getNoticeUrl().equals("https://www.inha.ac.kr");
    }
}
