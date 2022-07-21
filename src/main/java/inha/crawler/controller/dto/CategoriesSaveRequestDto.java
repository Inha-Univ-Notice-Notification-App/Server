package inha.crawler.controller.dto;

import inha.crawler.domain.categories.Categories;
import lombok.Builder;

//import javax.validation.constraints.NotBlank;

public class CategoriesSaveRequestDto {
    //@NotBlank(message = "페이지를 입력해주세요")
    private String page;

    //@NotBlank(message = "게시판을 입력해주세요")
    private String tag;

    //@NotBlank(message = "url을 입력해주세요")
    private String url;

    //@NotBlank(message = "공지 url의 공통 부분을 입력해주세요")
    private String noticeUrl;



    @Builder
    public CategoriesSaveRequestDto(String page, String tag, String url, String noticeUrl){
        this.page = page;
        this.tag = tag;
        this.url = url;
        this.noticeUrl = noticeUrl;
    }

    public Categories toEntity() {
        return Categories.builder()
                .page(page)
                .tag(tag)
                .url(url)
                .noticeUrl(noticeUrl)
                .build();
    }
}
