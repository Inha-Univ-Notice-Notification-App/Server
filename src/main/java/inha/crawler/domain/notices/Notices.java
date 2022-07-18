package inha.crawler.domain.notices;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
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

    // 즐겨찾기 여부
    private boolean star = false;

    @Builder
    public Notices(String title, String page, String tag, String url){
        this.title = title;
        this.page = page;
        this.tag = tag;
        this.url = url;
    }

    // 즐겨찾기 여부 변경
    public void changeStar(){
        this.star = !this.star;
    }
}
