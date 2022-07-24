package inha.crawler.service.categories;

import inha.crawler.controller.dto.CategoriesListResponseDto;
import inha.crawler.controller.dto.CategoriesSaveRequestDto;
import inha.crawler.domain.categories.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class CategoriesService {
    private final CategoriesRepository categoriesRepository;

    @Transactional
    public void save(CategoriesSaveRequestDto requestDto) {
        List<CategoriesListResponseDto> duplicationCheck = categoriesRepository.duplicationCheck(requestDto.getPage(), requestDto.getTag());
        if (duplicationCheck.size() == 0) {
            categoriesRepository.save(requestDto.toEntity()).getId();
        }
    }

    @Transactional(readOnly = true)
    public List<CategoriesListResponseDto> findAll() {
        return categoriesRepository.findAll().stream()
                .map(CategoriesListResponseDto::new)
                .collect(Collectors.toList());
    }
}
