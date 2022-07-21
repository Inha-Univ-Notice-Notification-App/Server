package inha.crawler.domain.notices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoticesRepository extends JpaRepository<Notices,Long> {
    //@Query


    //@Override
    //boolean existsByTitle(Long aLong);

//    boolean existsByTitle(String title);

//    @Query()
//    boolean duplicationCheck(String title, String page, String tag);
}
