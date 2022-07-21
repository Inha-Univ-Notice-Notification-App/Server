package inha.crawler.service.notices;

import inha.crawler.controller.dto.CategoriesListResponseDto;
import inha.crawler.controller.dto.NoticesListResponseDto;
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
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoticesService {
    private final NoticesRepository noticesRepository;

    private final CategoriesService categoriesService;


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

                for(int i = 0; i < items.size(); i++) {
                    String[] yearMonthDay = dates.get(i).split("\\.");

                    int year = Integer.parseInt(yearMonthDay[0]);
                    int month = Integer.parseInt(yearMonthDay[1]);
                    int day = Integer.parseInt(yearMonthDay[2]);

                    System.out.println(yearMonthDay.length);
                    System.out.println(Integer.parseInt(yearMonthDay[2]));

                    NoticesSaveRequestDto noticesSaveRequestDto = NoticesSaveRequestDto.builder()
                            .title(items.get(i))
                            .url(categoriesListResponseDto.getNoticeUrl() + links.get(i))
                            .page(categoriesListResponseDto.getPage())
                            .tag(categoriesListResponseDto.getTag())
                            .year(year)
                            .month(month)
                            .day(day)
                            .star(false)
                            .build();

                    noticesRepository.save(noticesSaveRequestDto.toEntity());
                }
            } catch (IOException e1) {
                System.out.println("getNoticesList()에서 오류가 발생했습니다.");
            }
        }
    }

    @Transactional(readOnly = true)
    public List<NoticesListResponseDto> findAll() {
        return noticesRepository.findAll().stream()
                .map(NoticesListResponseDto::new)
                .collect(Collectors.toList());
    }

}
