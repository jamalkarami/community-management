package com.isicod.net.communitiesManagement.controllers;

import com.isicod.net.communitiesManagement.constants.ParametrageConstants;
import com.isicod.net.communitiesManagement.models.Reclamation;
import com.isicod.net.communitiesManagement.models.Suggestion;
import com.isicod.net.communitiesManagement.services.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("apis/suggestion/")
@CrossOrigin("*")
public class SuggestionController {

    @Autowired
    private SuggestionService suggestionService;

    @PostMapping("saveSuggestion")
    public Suggestion saveSuggestion(@RequestBody Suggestion suggestion){
        return suggestionService.saveSuggestion(suggestion);
    }

    @GetMapping("suggestionOfCitoyen/{id}")
    public List<Suggestion> getSuggestionOfCitoyen(@PathVariable Long id){
        return suggestionService.findSuggestionofCitoyen(id);
    }

    @GetMapping("suggestionsOfGerant/{idGerant}")
    public List<Suggestion> getSuggestionOfGerant(@PathVariable Long idGerant){
        return suggestionService.getSuggestionOfGerant(idGerant);
    }

    @GetMapping("suggestionOfPresident")
    public List<Suggestion> getSuggestionOfPresident(){
        return suggestionService.getSuggestionOfPresident(ParametrageConstants.DUREE_ATTENTE_BY_SECONDES);
    }
}
