package inha.crawler.service.notices;

import inha.crawler.controller.dto.CategoriesListResponseDto;
import inha.crawler.controller.dto.NoticesListResponseDto;
import inha.crawler.controller.dto.NoticesSaveRequestDto;
import inha.crawler.domain.notices.Notices;
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

            if (categoriesListResponseDto.getNoticeUrl().equals("https://www.inha.ac.kr")) {
                updateNoticesList_School(url, categoriesListResponseDto.getPage(), categoriesListResponseDto.getTag(), categoriesListResponseDto.getNoticeUrl());
            }
            else {
                updateNoticesList_department(url, categoriesListResponseDto.getPage(), categoriesListResponseDto.getTag(), categoriesListResponseDto.getNoticeUrl());
            }

        }
    }


    @Transactional // 학교 홈페이지 크롤링
    public void updateNoticesList_School(String url, String page, String tag, String noticeUrl){
        Connection conn = Jsoup.connect(url);
        String attributeValue = "artclLinkView";

        try {
            Document document = conn.get();

            List<String> items = document.getElementsByAttributeValue("class", attributeValue).eachText();
            List<String> dates = document.select("._artclTdRdate").eachText();
            List<String> links = document.getElementsByAttributeValue("class", attributeValue).select("a").eachAttr("href");


            for(int i = 0; i < items.size(); i++) {
                String[] yearMonthDay = dates.get(i).split("\\.");

                int year = Integer.parseInt(yearMonthDay[0]);
                int month = Integer.parseInt(yearMonthDay[1]);
                int day = Integer.parseInt(yearMonthDay[2]);

                List<NoticesListResponseDto> duplicationCheck = noticesRepository.duplicationCheck(items.get(i), page, tag);

                if (duplicationCheck.size() == 0) {
                    NoticesSaveRequestDto noticesSaveRequestDto = NoticesSaveRequestDto.builder()
                            .title(items.get(i))
                            .url(noticeUrl + links.get(i))
                            .page(page)
                            .tag(tag)
                            .year(year)
                            .month(month)
                            .day(day)
                            .build();
                    noticesRepository.save(noticesSaveRequestDto.toEntity());
                }
            }
        } catch (IOException e1) {
            System.out.println("getNoticesList()에서 오류가 발생했습니다.");
        }
    }

    @Transactional //학과 홈페이지 크롤링
    public void updateNoticesList_department(String url, String page, String tag, String noticeUrl){
        Connection conn = Jsoup.connect(url);
        String attributeValue = "artclTable artclHorNum1";

        try {
            Document document = conn.get();

            List<String> items = document.getElementsByAttributeValue("class", attributeValue).select("strong").eachText();
            List<String> dates = document.select("._artclTdRdate").eachText();
            List<String> links = document.getElementsByAttributeValue("class", attributeValue).select("a").eachAttr("href");


            for(int i = 0; i < items.size(); i++) {
                String[] yearMonthDay = dates.get(i).split("\\.");

                int year = Integer.parseInt(yearMonthDay[0]);
                int month = Integer.parseInt(yearMonthDay[1]);
                int day = Integer.parseInt(yearMonthDay[2]);

                List<NoticesListResponseDto> duplicationCheck = noticesRepository.duplicationCheck(items.get(i), page, tag);

                if (duplicationCheck.size() == 0) {
                    NoticesSaveRequestDto noticesSaveRequestDto = NoticesSaveRequestDto.builder()
                            .title(items.get(i))
                            .url(noticeUrl + links.get(i))
                            .page(page)
                            .tag(tag)
                            .year(year)
                            .month(month)
                            .day(day)
                            .build();
                    noticesRepository.save(noticesSaveRequestDto.toEntity());
                }
            }
        } catch (IOException e1) {
            System.out.println("getNoticesList()에서 오류가 발생했습니다.");
        }
    }

    @Transactional(readOnly = true)
    public List<NoticesListResponseDto> findAll() {
        return noticesRepository.findAll().stream()
                .map(NoticesListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Notices heartPlus(Long noticeId){
        Notices notice = noticesRepository.findById(noticeId).orElseThrow(() -> new IllegalArgumentException("해당 id의 공지가 없습니다. id="+noticeId));
        notice.noticeHeartPlus();
        return notice;
    }

    @Transactional
    public void heartMinus(Long noticeId){
        Notices notice = noticesRepository.findById(noticeId).orElseThrow(() -> new IllegalArgumentException("해당 id의 공지가 없습니다. id="+noticeId));
        notice.noticeHeartMinus();
    }

}
