package com.isicod.net.communitiesManagement.mapper;

import com.isicod.net.communitiesManagement.dto.ReclamationDto;
import com.isicod.net.communitiesManagement.models.*;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReclamationMapper {

    private Mapper mapper = new DozerBeanMapper();



        public Reclamation ReclamationDtoToReclamation(ReclamationDto reclamationDto) {
            Reclamation reclamation= mapper.map(reclamationDto, Reclamation.class);
       if (reclamationDto.getTypeReclamation() != null) {

           reclamation.setTypeReclamation(mapper.map(reclamationDto.getTypeReclamation(), TypeReclamation.class));
        }
       if(reclamationDto.getInnerTypeReclamation()!=null){
           reclamation.setInnerTypeReclamation(mapper.map(reclamationDto.getInnerTypeReclamation(), SousTypeReclamation.class));
       }

       if(reclamationDto.getUsers()!=null){
                reclamation.setUsers(mapper.map(reclamationDto.getUsers(), Users.class));
       }

       if(reclamationDto.getStatus()!=null){
                reclamation.setStatus(mapper.map(reclamationDto.getStatus(), Status.class));
       }
       return reclamation;


    }

    public List<Reclamation> ReclamationDtoToReclamation(List<ReclamationDto> reclamationDto) {
        List<Reclamation> dtos = new ArrayList<>();
        reclamationDto.forEach(doc -> dtos.add(ReclamationDtoToReclamation(doc)));
        return dtos;
    }




}
