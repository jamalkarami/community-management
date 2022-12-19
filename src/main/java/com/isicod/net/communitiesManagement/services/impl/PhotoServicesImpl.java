package com.isicod.net.communitiesManagement.services.impl;

import com.isicod.net.communitiesManagement.models.Photos;
import com.isicod.net.communitiesManagement.repositories.PhotosRepository;
import com.isicod.net.communitiesManagement.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoServicesImpl implements PhotoService {
    @Value("${outPath}")
    String outPath;

    @Autowired
    private PhotosRepository photosRepository;

    @Override
    public File downloadFile(String Url) throws IOException {
        File file = new File(this.outPath+Url);
        if (!file.exists() || !file.isFile()) {
            throw new IOException();
        }
        return file;
    }

    @Override
    public List<String> getPhotos(Long idReclamation,String path) {
        List<Photos> photos=photosRepository.findByReclamation(idReclamation);
        List<String> photosUrl= new ArrayList<String>();

        for(Photos photo:photos){
            photosUrl.add(path+"/downloadFile/"+photo.getChemain());
        }
        return photosUrl;
    }
}
