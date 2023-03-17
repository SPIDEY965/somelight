package com.somelight.project.api.service;

import com.somelight.project.db.enitity.Article;
import com.somelight.project.db.enitity.User;
import com.somelight.project.db.repository.ArticleRepository;
import com.somelight.project.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public int getUserId(String email){
        User user = userRepository.findByEmail(email).orElse(null);
        return user.getUserId();
    }

    @Override
    public List<Article> getUserArticles(int userId, Pageable pageable){
        List<Article> articles = null;
        articles = articleRepository.findAllByUserId(userId, pageable);
        return articles;
    }
}
