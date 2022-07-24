package inha.crawler.domain.categories;

import inha.crawler.controller.dto.CategoriesListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<Categories,Long> {

    @Query(value = "SELECT c FROM Categories c WHERE page=?1 AND tag=?2")
    List<CategoriesListResponseDto> duplicationCheck(String page, String tag);
}
