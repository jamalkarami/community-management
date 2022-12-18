package com.isicod.net.communitiesManagement.controllers;

import com.isicod.net.communitiesManagement.dto.NewsDto;
import com.isicod.net.communitiesManagement.models.News;
import com.isicod.net.communitiesManagement.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("apis/news/")
@CrossOrigin("*")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @PostMapping("saveNews")
    public void saveNews(@ModelAttribute NewsDto news) {
        newsService.saveNews(news);
    }

    @GetMapping("getTrueValidation")
    public List<News> getTrueValidation() {
        return newsService.getNewsValidation();
    }

    @GetMapping("news-approvation")
    public List<News> getNewsApprovation() {
        return newsService.getNewsApprovation();
    }

 /*   @GetMapping("donwloadphoto")
    public ResponseEntity<InputStreamResource> downloadReclamationFile(@RequestParam String url) throws IOException {
       *//* if (url == null) {
            throw new IOException();
        }
        //File file = newsService.downloadReclamationFile(url);
        InputStreamResource resource = new InputStreamResource(new
                FileInputStream(file));
        return ResponseEntity.ok().body(resource);
*//*
    }*/

    @PutMapping("changeValidationToTrue/{idNews}")
    public News changeValidationToTrue(@PathVariable Long idNews) {
        return newsService.changeValidationToTrue(idNews);
    }
}
