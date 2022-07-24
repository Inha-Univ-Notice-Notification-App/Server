package inha.crawler.domain.notices;

import inha.crawler.controller.dto.NoticesListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticesRepository extends JpaRepository<Notices,Long> {
    //@Query


    //@Override
    //boolean existsByTitle(Long aLong);

//    boolean existsByTitle(String title);

    @Query(value = "SELECT n FROM Notices n WHERE title=?1 AND page=?2 AND tag=?3")
    List<NoticesListResponseDto> duplicationCheck(String title, String page, String tag);
}
