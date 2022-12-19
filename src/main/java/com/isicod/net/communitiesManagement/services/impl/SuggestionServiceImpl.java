package com.isicod.net.communitiesManagement.services.impl;

import com.isicod.net.communitiesManagement.dto.SuggestionDto;
import com.isicod.net.communitiesManagement.mapper.ReclamationMapper;
import com.isicod.net.communitiesManagement.mapper.SuggestionMapper;
import com.isicod.net.communitiesManagement.models.*;
import com.isicod.net.communitiesManagement.repositories.*;
import com.isicod.net.communitiesManagement.services.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class SuggestionServiceImpl implements SuggestionService {

    @Value("${outPath}")
    String outPath;

    @Value("${slash}")
    String slash;


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
    
    @Autowired
    private PhotosRepository photosRepository;

    @Override
    public void saveSuggestion(SuggestionDto suggestionDto, List<MultipartFile> multipart) throws IOException {
        Status s= statusRepository.findByCode("EV");
        suggestionDto.setStatus(s);
        Suggestion suggestion= suggestionRepository.save(suggestionMapper.SuggestionDtoToSuggestion(suggestionDto));
        if(multipart!=null){
            if (!Files.exists(Paths.get(outPath))) {
                new File(outPath).mkdir();
            }
            int i=0;
            for(MultipartFile file:multipart){

                Photos photosReclamation=new Photos();
                photosReclamation.setChemain("suggestion-"+ suggestion.getId()+ "-" + i+"-" + file.getOriginalFilename());
                photosReclamation.setSuggestion(suggestion);
                photosRepository.save(photosReclamation);

                byte[] data=file.getBytes();
                Path path= Paths.get(outPath+slash+"suggestion-"+ suggestion.getId()+ "-" + i+"-" + file.getOriginalFilename());

                Files.write(path, data);
                i++;
            }
            suggestionRepository.save(suggestion);
        }
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

    @GetMapping(value = "downloadSuggestionFile")
    public ResponseEntity<InputStreamResource> downloadReclamationFile(@RequestParam String url) throws IOException {
        if (url == null) {
            throw new IOException();
        }
        File file = new File(this.outPath+url);
        if (!file.exists() || !file.isFile()) {
            throw new IOException();
        }
        InputStreamResource resource = new InputStreamResource(new
                FileInputStream(file));
        return ResponseEntity.ok().body(resource);
    }

    @GetMapping(value = "downloadSuggestionDeuxiemeFile")
    public ResponseEntity<InputStreamResource> downloadSuggestionDeuxiemeFile(@RequestParam String url) throws IOException {
        if (url == null) {
            throw new IOException();
        }
        File file = new File(this.outPath+url);
        if (!file.exists() || !file.isFile()) {
            throw new IOException();
        }
        InputStreamResource resource = new InputStreamResource(new
                FileInputStream(file));
        return ResponseEntity.ok().body(resource);
    }
}
