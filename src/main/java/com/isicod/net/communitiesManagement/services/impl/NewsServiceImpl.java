package com.isicod.net.communitiesManagement.services.impl;

import com.isicod.net.communitiesManagement.models.News;
import com.isicod.net.communitiesManagement.repositories.NewsRepository;
import com.isicod.net.communitiesManagement.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public void saveNews(News news) {
        news.setValidation(false);
        newsRepository.save(news);
    }

    @Override
    public List<News> getNewsValidation() {
        List<News> news=  newsRepository.findByValidation(true);
        return news;
    }

    @Override
    public News changeValidationToTrue(Long idNews) {
        News news=newsRepository.findById(idNews).get();
        news.setValidation(true);
      return  newsRepository.save(news);
    }
}
