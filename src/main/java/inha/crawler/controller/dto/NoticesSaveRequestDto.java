package inha.crawler.controller.dto;

import inha.crawler.domain.notices.Notices;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticesSaveRequestDto {
    //@NotBlank(message = "제목이 없습니다")
    private String title;

    //@NotBlank(message = "공지가 어느 페이지인지 알려주세요")
    private String page;

    //@NotBlank(message = "공지가 어느 게시판에 올라왔는지 알려주세요")
    private String tag;

    //@NotBlank(message = "공지의 URL을 알려주세요")
    private String url;

    //@NotBlank(message = "공지가 올라온 연도가 입력되지 않았습니다")
    private int year;

    //@NotBlank(message = "공지가 올라온 달이 입력되지 않았습니다")
    private int month;

    //@NotBlank(message = "공지가 올라온 날짜가 입력되지 않았습니다")
    private int day;

    @Builder.Default
    private int heart = 0;

    @Builder
    public NoticesSaveRequestDto(String title, String page, String tag, String url, int year, int month, int day) {
        this.title = title;
        this.page = page;
        this.tag = tag;
        this.url = url;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Notices toEntity() {
        return Notices.builder()
                .title(title)
                .page(page)
                .tag(tag)
                .url(url)
                .year(year)
                .month(month)
                .day(day)
                .build();
    }
}
