package com.somelight.project.api.service;

import com.somelight.project.db.enitity.Article;
import com.somelight.project.db.repository.ArticleRepository;
import com.somelight.project.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public Page<Article> findArticles(Pageable pageable) {
        Page<Article> articles = null;
        articles = articleRepository.findAll(pageable);
        return articles;
    }

//    public List<Article> getUserArticles(int userId, Pageable pageable){
//        List<Article> articles = null;
//        articles = articleRepository.findAllByUserId(userId, pageable);
//        return articles;
//    }
    @Override
    public Article getArticleByArticleId(int articleId) {
        Article article = null;
        article = articleRepository.findByArticleId(articleId).orElseThrow();
        return article;
    }

    @Override
    public Article createArticle(int userId, String content, int result) {
        LocalDateTime nowDate = LocalDateTime.now();
        Article article = Article.builder()
                .userId(userId)
                .content(content)
                .result(result)
                .isChanged(false)
                .createdDate(nowDate)
                .isExposure(false)
                .redCount(0)
                .greenCount(0)
                .build();
        articleRepository.save(article);
        return article;
    }

    @Override
    @Transactional
    public void deleteArticle(int articleId) {
        Article article = articleRepository.findByArticleId(articleId).orElseThrow();
        articleRepository.delete(article);
    }
}