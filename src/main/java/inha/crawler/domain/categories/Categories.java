package inha.crawler.domain.categories;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
//@Table(name="categories")
@Entity
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String page;
    private String tag;
    private String url;
    private String noticeUrl;

    @Builder
    public Categories(String page, String tag, String url, String noticeUrl){

        this.page = page;
        this.tag = tag;
        this.url = url;
        this.noticeUrl = noticeUrl;
    }
}
