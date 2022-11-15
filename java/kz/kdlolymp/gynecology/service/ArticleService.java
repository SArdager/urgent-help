package kz.kdlolymp.gynecology.service;

import kz.kdlolymp.gynecology.entity.Article;
import kz.kdlolymp.gynecology.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Article getArticleById(int id){
       return articleRepository.findById(id);
    }
    public List<Article> getArticlesByLang(String lang){
        return articleRepository.findAllByLang(lang);
    }
    public Article getArticleByPlaceNameAndLang(String placeName, String lang){
        return articleRepository.findByPlaceNameAndLang(placeName, lang);
    }

    public boolean saveArticle(Article article){
        articleRepository.save(article);
        return true;
    }

}
