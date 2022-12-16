package com.isicod.net.communitiesManagement.controllers;

import com.isicod.net.communitiesManagement.constants.ParametrageConstants;
import com.isicod.net.communitiesManagement.dto.ReclamationDto;
import com.isicod.net.communitiesManagement.models.Reclamation;
import com.isicod.net.communitiesManagement.services.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public void saveReclamation(@ModelAttribute ReclamationDto reclamationDto) throws IOException {
        reclamationService.saveReclamation(reclamationDto,reclamationDto.getFiles());
    }

    @GetMapping("reclamationsOfGerant/{idGerant}/{status}")
    public List<Reclamation> getReclamationsOfGerant(@PathVariable Long idGerant,@PathVariable String status){
        return reclamationService.getReclamationsOfGerant(idGerant,status);
    }

    @GetMapping("reclamationSeenByGerant/{idReclamation}")
    public void reclamationSeenByGerant(@PathVariable Long idReclamation){
        reclamationService.reclamationSeenByGerant(idReclamation);
    }

    @GetMapping("reclamationsOfPresident/{status}")
    public List<Reclamation> getReclamationsOfPresident(@PathVariable String status){
        if(status != null && status.equals("NR")){
            return reclamationService.getReclamationsOfPresident(ParametrageConstants.DUREE_ATTENTE_BY_SECONDES);
        }else{
            return reclamationService.nonSatisfait();
        }
    }

    @PostMapping(value = "saveReclamationWithPhotos", consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE })
    public void saveReclamationWithPhotos(@ModelAttribute ReclamationDto reclamationDto){
        reclamationService.saveReclamationWithPhotos(reclamationDto,reclamationDto.getFiles());
    }

    @GetMapping("reclamations/{idUser}/{status}")
    public List<Reclamation> getReclamationsByStatus(@PathVariable Long idUser, @PathVariable String status){
        return reclamationService.getReclamationByStatus(idUser,status);
    }

    @PutMapping("changeReclamationStatusToTraitement/{idReclamation}")
    public Reclamation changeReclamationStatusToTraitement(@PathVariable Long idReclamation){
        return reclamationService.changeStatusToTraitement(idReclamation);
    }

    @PutMapping("changeReclamationStatusTpTraite/{idReclamation}")
    public Reclamation changeReclamationStatusToTraite(@PathVariable Long idReclamation){
        return reclamationService.changeStatusToTraite(idReclamation);
    }

    @GetMapping("reclamationNonSatisfait/{idUser}")
    public List<Reclamation> getReclamationNonSatisfait(){
        return reclamationService.nonSatisfait();
    }

    @PutMapping("citoyenSatisfaitNonSatisfait/{idReclamation}/{satisfait}")
    public void citoyenSatisfaitNonSatisfait(@PathVariable Long idReclamation,@PathVariable String satisfait) {
        reclamationService.citoyenSatisfaitNonSatisfait(idReclamation,satisfait);
    }

    @GetMapping("cloturerReclamation/{idReclamation}")
    public Reclamation cloturerReclamation(@PathVariable Long idReclamation){
        return reclamationService.cloturerReclamation(idReclamation);
    }
}
