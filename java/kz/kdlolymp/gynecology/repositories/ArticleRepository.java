package kz.kdlolymp.gynecology.repositories;

import kz.kdlolymp.gynecology.entity.Article;
import kz.kdlolymp.gynecology.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Article findById(int id);

    Article findByPlaceNameAndLang(String placeName, String lang);

    List<Article> findAllByLang(String lang);
}
