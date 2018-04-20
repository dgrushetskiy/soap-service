package ru.gothmog.soapws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gothmog.soapws.entity.Article;
import ru.gothmog.soapws.repository.ArticleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> getAllArticles() {
        List<Article> articleList = new ArrayList<>();
        articleRepository.findAll().forEach(e ->articleList.add(e));
        return articleList;
    }

    @Override
    public Article getArticleById(long articleId) {
        Article article = articleRepository.findByArticleId(articleId);
        return article;
    }

    @Override
    public synchronized boolean addArticle(Article article) {
        List<Article> articleList = articleRepository.findByTitleAndCategory(article.getTitle(), article.getCategory());
        if (articleList.size() > 0){
            return false;
        } else {
            article = articleRepository.save(article);
            return true;
        }
    }

    @Override
    public void updateArticle(Article article) {
        articleRepository.save(article);
    }

    @Override
    public void deleteArticle(Article article) {
        articleRepository.delete(article);
    }
}
