package com.isicod.net.communitiesManagement.controllers;

import com.isicod.net.communitiesManagement.constants.ParametrageConstants;
import com.isicod.net.communitiesManagement.dto.SuggestionDto;
import com.isicod.net.communitiesManagement.models.Suggestion;
import com.isicod.net.communitiesManagement.services.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("apis/suggestion/")
@CrossOrigin("*")
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
}
