package com.isicod.net.communitiesManagement.controllers;

import com.isicod.net.communitiesManagement.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("apis/photo/")
@CrossOrigin("*")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @GetMapping(value = "downloadFile/{photoName}")
    public ResponseEntity<InputStreamResource> downloadFile( @PathVariable String photoName) throws IOException {
        if (photoName == null) {
            throw new IOException();
        }

        File file = photoService.downloadFile(photoName);
        InputStreamResource resource = new InputStreamResource(new
                FileInputStream(file));
        return ResponseEntity.ok().body(resource);

    }

    @GetMapping(value = "getPhotos/{idReclamation}/{path}")
    public List<String> getPhotos(@PathVariable Long idReclamation,@PathVariable String path){
        return photoService.getPhotos(idReclamation,path);
    }
}
