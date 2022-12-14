package com.isicod.net.communitiesManagement.services.impl;

import com.isicod.net.communitiesManagement.models.Gerant;
import com.isicod.net.communitiesManagement.models.Reclamation;
import com.isicod.net.communitiesManagement.models.Suggestion;
import com.isicod.net.communitiesManagement.models.Users;
import com.isicod.net.communitiesManagement.repositories.GerantRepository;
import com.isicod.net.communitiesManagement.repositories.SuggestionRepository;
import com.isicod.net.communitiesManagement.repositories.UsersRepository;
import com.isicod.net.communitiesManagement.services.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestionServiceImpl implements SuggestionService {
    @Autowired
    private SuggestionRepository suggestionRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private GerantRepository gerantRepository;

    @Override
    public Suggestion saveSuggestion(Suggestion suggestion) {
        return suggestionRepository.save(suggestion);
    }

    @Override
    public List<Suggestion> findSuggestionofCitoyen(Long id) {
        Users users=usersRepository.findById(id).get();
        return suggestionRepository.findByUsers(users);
    }

    @Override
    public List<Suggestion> getSuggestionOfGerant(Long idGerant) {
        Gerant gerant=gerantRepository.findById(idGerant).get();
        return suggestionRepository.getSuggestionOfGerant(gerant.getProfil().getId());
    }

    @Override
    public List<Suggestion> getSuggestionOfPresident(int secondes) {
        List<Suggestion> suggestionOfPresident=suggestionRepository.getReclamationsOfPresident(secondes);
        suggestionOfPresident.forEach(suggestion->{
            suggestion.setSeenByGerant(false);
            suggestionRepository.save(suggestion);
        });
        return suggestionOfPresident;
    }


}
