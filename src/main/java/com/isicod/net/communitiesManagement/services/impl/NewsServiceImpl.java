package com.isicod.net.communitiesManagement.services.impl;

import com.isicod.net.communitiesManagement.dto.NewsDto;
import com.isicod.net.communitiesManagement.models.News;
import com.isicod.net.communitiesManagement.models.Photos;
import com.isicod.net.communitiesManagement.repositories.NewsRepository;
import com.isicod.net.communitiesManagement.repositories.PhotosRepository;
import com.isicod.net.communitiesManagement.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Value("${outPath}")
    String outPath;

    @Value("${slash}")
    String slash;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private PhotosRepository photosRepository;

    @Override
    public void saveNews(NewsDto news) {
        News news1 = new News();
        news1.setTitle(news.getTitle());
        news1.setDescription(news.getDescription());
        news1.setValidation(false);
        news1.setUsers(news.getUsers());
        News savedNews = newsRepository.save(news1);

        if(news.getFiles()!=null){
            if (!Files.exists(Paths.get(outPath))) {
                new File(outPath).mkdir();
            }
            int i=0;
            for(MultipartFile file:news.getFiles()){

                Photos photosReclamation=new Photos();
                photosReclamation.setChemain("reclamation-"+ savedNews.getId()+ "-" + i+"-" + file.getOriginalFilename());
                photosReclamation.setNews(savedNews);
                photosRepository.save(photosReclamation);

                byte[] data= new byte[0];
                try {
                    data = file.getBytes();
                    Path path= Paths.get(outPath+slash+"news-"+ savedNews.getId()+ "-" + i+"-" + file.getOriginalFilename());
                    Files.write(path, data);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                i++;
            }
            newsRepository.save(savedNews);
        }
    }

    @Override
    public List<News> getNewsValidation() {
        List<News> news=  newsRepository.findByValidationTrue();
        return news;
    }

    @Override
    public List<News> getNewsApprovation() {
        List<News> news=  newsRepository.findByValidation(false);
        return news;
    }

    @Override
    public News changeValidationToTrue(Long idNews) {
        News news=newsRepository.findById(idNews).get();
        news.setValidation(true);
      return  newsRepository.save(news);
    }
}
