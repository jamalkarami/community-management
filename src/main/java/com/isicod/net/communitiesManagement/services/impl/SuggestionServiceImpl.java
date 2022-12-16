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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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


    @Override
    public void saveSuggestion(SuggestionDto suggestionDto, List<MultipartFile> multipart) throws IOException {
        Status s= statusRepository.findByCode("EV");
//        Suggestion r=suggestionMapper.SuggestionDtoToSuggestion(suggestionDto);
//        r.setStatus(s);
//        suggestionRepository.save(r);

        suggestionDto.setStatus(s);
        Suggestion suggestion= suggestionRepository.save(suggestionMapper.SuggestionDtoToSuggestion(suggestionDto));
        if(multipart!=null){
            if (!Files.exists(Paths.get(outPath))) {
                new File(outPath).mkdir();
            }

            String  outPath2=outPath+suggestionDto.getId();


            if (!Files.exists(Paths.get(outPath2))) {
                new File(outPath2).mkdir();
            }
            int i=0;
            for(MultipartFile file:multipart){

                if(suggestion.getChemainPremierPhoto()==null){
                    suggestion.setChemainPremierPhoto(outPath2+slash+"photo-"+ suggestion.getId()+ "-" + i+".jpg");
                }
                else {
                    if(suggestion.getChemainPremierPhoto()!=null && suggestion.getChemainDeuxsiemePhoto()==null){
                        suggestion.setChemainDeuxsiemePhoto(outPath2+slash+"photo-"+ suggestion.getId()+ "-" + i+".jpg");
                    }
                }

                byte[] data=file.getBytes();
                Path path= Paths.get(outPath2+slash+"photo-"+ suggestion.getId()+ "-" + i+".jpg");

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

}
