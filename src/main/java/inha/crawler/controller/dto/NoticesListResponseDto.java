package inha.crawler.controller.dto;

import inha.crawler.domain.notices.Notices;
import lombok.Getter;

import javax.persistence.Column;

@Getter
public class NoticesListResponseDto {
    private Long id;
    private String title;
    private String page;
    private String tag;
    private String url;
    private int year;
    private int month;
    private int day;

    public NoticesListResponseDto(Notices entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.page = entity.getPage();
        this.tag = entity.getTag();
        this.url = entity.getUrl();
        this.year = entity.getYear();
        this.month = entity.getMonth();
        this.day = entity.getDay();
    }
}
