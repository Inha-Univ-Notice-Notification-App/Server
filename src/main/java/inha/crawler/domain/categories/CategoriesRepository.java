package inha.crawler.domain.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoriesRepository extends JpaRepository<Categories,Long> {
//    boolean existsByPage(String title);
//    boolean existsByTag(String title);

//    @Query("delete from Categories where id NOT IN (SELECT * from (SELECT MIN(id) FROM Categories GROUP BY page,tag,url,noticeUrl) AS tempTable)")
//    void delete();
}
