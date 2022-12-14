package com.isicod.net.communitiesManagement.controllers;

import com.isicod.net.communitiesManagement.constants.ParametrageConstants;
import com.isicod.net.communitiesManagement.dto.ReclamationDto;
import com.isicod.net.communitiesManagement.models.Reclamation;
import com.isicod.net.communitiesManagement.services.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("apis/reclamation/")
@CrossOrigin("*")
public class ReclamationController {

    @Autowired
    private ReclamationService reclamationService;

    @GetMapping("reclamationsOfCitoyen/{id}")
    public List<Reclamation> getReclamationsOfCitoyen(@PathVariable Long id){
      return reclamationService.findReclamationsofCitoyen(id);
    }

    @PostMapping("saveReclamation")
    public void saveReclamation(@RequestBody Reclamation reclamation){
        reclamationService.saveReclamation(reclamation);
    }

    @GetMapping("reclamationsOfGerant/{idGerant}")
    public List<Reclamation> getReclamationsOfGerant(@PathVariable Long idGerant){
        return reclamationService.getReclamationsOfGerant(idGerant);
    }

    @GetMapping("reclamationSeenByGerant/{idReclamation}")
    public void reclamationSeenByGerant(@PathVariable Long idReclamation){
        reclamationService.reclamationSeenByGerant(idReclamation);
    }

    @GetMapping("reclamationsOfPresident")
    public List<Reclamation> getReclamationsOfPresident(){
        return reclamationService.getReclamationsOfPresident(ParametrageConstants.DUREE_ATTENTE_BY_SECONDES);
    }

    @PostMapping(value = "saveReclamationWithPhotos", consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE })
    public void saveReclamationWithPhotos(@ModelAttribute ReclamationDto reclamationDto){
        reclamationService.saveReclamationWithPhotos(reclamationDto,reclamationDto.getFiles());
    }

}
