package com.isicod.net.communitiesManagement.services.impl;

import com.isicod.net.communitiesManagement.dto.SuggestionDto;
import com.isicod.net.communitiesManagement.mapper.ReclamationMapper;
import com.isicod.net.communitiesManagement.mapper.SuggestionMapper;
import com.isicod.net.communitiesManagement.models.*;
import com.isicod.net.communitiesManagement.repositories.GerantRepository;
import com.isicod.net.communitiesManagement.repositories.StatusRepository;
import com.isicod.net.communitiesManagement.repositories.SuggestionRepository;
import com.isicod.net.communitiesManagement.repositories.UsersRepository;
import com.isicod.net.communitiesManagement.services.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class SuggestionServiceImpl implements SuggestionService {
    @Autowired
    private SuggestionRepository suggestionRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private GerantRepository gerantRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    SuggestionMapper suggestionMapper;


    @Override
    public void saveSuggestion(SuggestionDto suggestionDto, List<MultipartFile> multipart) {
        Status s= statusRepository.findByCode("EV");
        System.out.println("<<<<<<<<<"+s.getLibelle());

        Suggestion r=suggestionMapper.SuggestionDtoToSuggestion(suggestionDto);
        r.setStatus(s);
        suggestionRepository.save(r);
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

    @Override
    public List<Suggestion> getSuggestionByStatusAndUser(Long idUser, String status) {
        return suggestionRepository.getSuggestionByStatusAndUser(idUser,status);
    }

    public List<Suggestion> getAllSuggestion(){
        return suggestionRepository.findAll();
    }

}
