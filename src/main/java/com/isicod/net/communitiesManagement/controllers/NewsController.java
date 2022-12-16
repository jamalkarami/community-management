package com.isicod.net.communitiesManagement.controllers;

import com.isicod.net.communitiesManagement.models.News;
import com.isicod.net.communitiesManagement.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("apis/news/")
@CrossOrigin("*")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @PostMapping("saveNews")
    public void saveNews(@RequestBody News news){
        newsService.saveNews(news);
    }

    @GetMapping("getTrueValidation")
    public List<News> getTrueValidation(){
        return newsService.getNewsValidation();
    }

    @PutMapping("changeValidationToTrue/{idNews}")
    public News changeValidationToTrue(@PathVariable Long idNews){
        return newsService.changeValidationToTrue(idNews);
    }
}
