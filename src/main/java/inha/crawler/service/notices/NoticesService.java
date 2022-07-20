package inha.crawler.service.notices;

import inha.crawler.controller.dto.CategoriesListResponseDto;
import inha.crawler.controller.dto.NoticesSaveRequestDto;
import inha.crawler.domain.notices.NoticesRepository;
import inha.crawler.service.categories.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NoticesService {
    private final NoticesRepository noticesRepository;

    private final CategoriesService categoriesService;

    @Transactional
    public Long save(NoticesSaveRequestDto requestDto) {
        return noticesRepository.save(requestDto.toEntity()).getId();
    }


    @Transactional
    public void updateNoticesList() {
        List<CategoriesListResponseDto> categoriesList = categoriesService.findAll();

        for(CategoriesListResponseDto categoriesListResponseDto : categoriesList){
            final String url = categoriesListResponseDto.getUrl();
            Connection conn = Jsoup.connect(url);
            try {
                Document document = conn.get();

                List<String> items = document.getElementsByAttributeValue("class", "artclTable artclHorNum1").select("a").eachText();
                List<String> dates = document.select("._artclTdRdate").eachText();
                List<String> links = document.getElementsByAttributeValue("class", "artclTable artclHorNum1").select("a").eachAttr("href");

//                System.out.println(items);
//                System.out.println(dates);
//                System.out.println(links);
                for(int i = 0; i < items.size(); i++) {
                    //System.out.println(dates.get(i));
                    String[] yearMonthDay = dates.get(i).split("\\.");

                    int year = Integer.parseInt(yearMonthDay[0]);
                    int month = Integer.parseInt(yearMonthDay[1]);
                    int day = Integer.parseInt(yearMonthDay[2]);

                    System.out.println(yearMonthDay.length);
                    System.out.println(Integer.parseInt(yearMonthDay[2]));

                    NoticesSaveRequestDto noticesSaveRequestDto = NoticesSaveRequestDto.builder()
                            .title(items.get(i))
                            .url(categoriesListResponseDto.getNoticeUrl() + links.get(i))
                            .page(categoriesListResponseDto.getUrl())
                            .tag(categoriesListResponseDto.getTag())
                            .year(year)
                            .month(month)
                            .day(day)
                            .build();

                    noticesRepository.save(noticesSaveRequestDto.toEntity());
                }
//                for (int i = 0; i < items.size(); i++) {
//                    System.out.println("제목 : " + items.get(i) + ", 날짜 : " + dates.get(i) + ", URL : " + links.get(i));
//                }

            } catch (IOException e1) {
                System.out.println("getNoticesList()에서 오류가 발생했습니다.");
            }
        }
    }
}
