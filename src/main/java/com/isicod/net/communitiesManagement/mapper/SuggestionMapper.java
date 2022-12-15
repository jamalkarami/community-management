package com.isicod.net.communitiesManagement.mapper;

import com.isicod.net.communitiesManagement.dto.ReclamationDto;
import com.isicod.net.communitiesManagement.dto.SuggestionDto;
import com.isicod.net.communitiesManagement.models.*;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SuggestionMapper {
    private Mapper mapper = new DozerBeanMapper();

    public Suggestion SuggestionDtoToSuggestion(SuggestionDto suggestionDto) {
        Suggestion suggestion= mapper.map(suggestionDto, Suggestion.class);
        if (suggestionDto.getTypeSuggestion() != null) {

            suggestion.setTypeSuggestion(mapper.map(suggestionDto.getTypeSuggestion(), TypeReclamation.class));
        }
        if(suggestionDto.getInnerTypeSuggestion()!=null){
            suggestion.setInnerTypeSuggestion(mapper.map(suggestionDto.getInnerTypeSuggestion(), SousTypeReclamation.class));
        }

        if(suggestionDto.getUsers()!=null){
            suggestion.setUsers(mapper.map(suggestionDto.getUsers(), Users.class));
        }

        if(suggestionDto.getStatus()!=null){
            suggestion.setStatus(mapper.map(suggestionDto.getStatus(), Status.class));
        }
        return suggestion;


    }


    public List<Suggestion> SuggestionDtoToSuggestion(List<SuggestionDto> suggestionDto) {
        List<Suggestion> dtos = new ArrayList<>();
        suggestionDto.forEach(doc -> dtos.add(SuggestionDtoToSuggestion(doc)));
        return dtos;
    }
}
