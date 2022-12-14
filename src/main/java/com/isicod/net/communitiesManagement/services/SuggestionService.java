package com.isicod.net.communitiesManagement.services;


import com.isicod.net.communitiesManagement.models.Reclamation;
import com.isicod.net.communitiesManagement.models.Suggestion;

import java.util.List;

public interface SuggestionService {
    public Suggestion saveSuggestion(Suggestion suggestion);

    public List<Suggestion> findSuggestionofCitoyen(Long id);

    public List<Suggestion> getSuggestionOfGerant(Long idGerant);

    public List<Suggestion> getSuggestionOfPresident(int secondes);
}
