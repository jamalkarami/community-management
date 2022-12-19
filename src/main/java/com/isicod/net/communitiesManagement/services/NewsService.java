package com.isicod.net.communitiesManagement.services;


import com.isicod.net.communitiesManagement.dto.NewsDto;
import com.isicod.net.communitiesManagement.models.News;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface NewsService {

    public void saveNews(NewsDto news);

    public List<News> getNewsValidation();

    public News changeValidationToTrue(Long idNews);

    public List<News> getNewsApprovation();

    public File downloadNewsFile(String photoName) throws IOException;

    public List<String> getPhotoNews(Long idNews) ;

}
