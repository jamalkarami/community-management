package com.isicod.net.communitiesManagement.controllers;

import com.isicod.net.communitiesManagement.constants.ParametrageConstants;
import com.isicod.net.communitiesManagement.dto.SuggestionDto;
import com.isicod.net.communitiesManagement.models.Suggestion;
import com.isicod.net.communitiesManagement.services.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("apis/suggestion/")
public class SuggestionController {

    @Autowired
    private SuggestionService suggestionService;

    @PostMapping("saveSuggestion")
    public void saveSuggestion(@ModelAttribute SuggestionDto suggestionDto) throws IOException {
         suggestionService.saveSuggestion(suggestionDto,suggestionDto.getFiles());
    }

    @GetMapping("suggestions/{id}")
    public List<Suggestion> getSuggestionOfCitoyen(@PathVariable Long id){
        return suggestionService.findSuggestionofCitoyen(id);
    }


    @GetMapping("SuggestionsByStatusAndUser/{idUser}/{status}")
    public List<Suggestion> getSuggestionByStatus(@PathVariable Long idUser, @PathVariable String status){
        return suggestionService.getSuggestionByStatusAndUser(idUser,status);
    }

    @GetMapping("getAllSuggestion")
    public List<Suggestion> getAllSuggestion(){
        return suggestionService.getAllSuggestion();
    }


    @GetMapping(value = "downloadSuggestionFile/{photoName}")
    public ResponseEntity<InputStreamResource> downloadSuggestionFile(@PathVariable String photoName) throws IOException {
        if (photoName == null) {
            throw new IOException();
        }

        File file = suggestionService.downloadSuggestionFile(photoName);
        InputStreamResource resource = new InputStreamResource(new
                FileInputStream(file));
        return ResponseEntity.ok().body(resource);

    }

    @GetMapping(value = "getSuggestionPhotos/{idSuggestion}")
    public List<String> getSuggestionPhotos(@PathVariable Long idSuggestion){
        return suggestionService.getPhotoSuggestion(idSuggestion);
    }

}
