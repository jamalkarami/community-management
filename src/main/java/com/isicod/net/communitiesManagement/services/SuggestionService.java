package com.isicod.net.communitiesManagement.services;


import com.isicod.net.communitiesManagement.dto.SuggestionDto;
import com.isicod.net.communitiesManagement.models.Reclamation;
import com.isicod.net.communitiesManagement.models.Suggestion;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface SuggestionService {
    public void saveSuggestion(SuggestionDto suggestionDto, List<MultipartFile> multipart) throws IOException;

    public List<Suggestion> findSuggestionofCitoyen(Long id);

    public List<Suggestion> getSuggestionOfGerant(Long idGerant);

    public List<Suggestion> getSuggestionOfPresident(int secondes);

    public List<Suggestion> getSuggestionByStatusAndUser(Long idUser, String status);

    public List<Suggestion> getAllSuggestion();

    public File downloadSuggestionFile(String photoName) throws IOException;

    public List<String> getPhotoSuggestion(Long idReclamation) ;
}
