package inha.crawler.domain.notices;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Notices {
    // PK 값
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // 공지 제목
    @Column(length = 1000, nullable = false)
    private String title;

    // 공지가 올라온 페이지 (ex. 정보통신공학과, 컴퓨터공학과, ...)
    private String page;

    // 공지가 올라온 게시판 (ex. 학부 공지, 장학, ...)
    private String tag;

    // 공지의 url 주소
    private String url;

    // 공지가 올라온 날짜
    private int year;
    private int month;
    private int day;

    // 공지 즐겨찾기 개수
    @Builder.Default
    private int heart = 0;


    @Builder
    public Notices(String title, String page, String tag, String url, int year, int month, int day){
        this.title = title;
        this.page = page;
        this.tag = tag;
        this.url = url;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public void noticeHeartPlus(){
        this.heart += 1;
    }

    public void noticeHeartMinus(){
        if(this.heart == 0){
            return;
        }
        this.heart -= 1;
    }

}
