package com.isicod.net.communitiesManagement.controllers;

import com.isicod.net.communitiesManagement.dto.ReclamationDto;
import com.isicod.net.communitiesManagement.models.SousTypeReclamation;
import com.isicod.net.communitiesManagement.models.TypeReclamation;
import com.isicod.net.communitiesManagement.services.ReferencielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/apis/referenciel/")
public class ReferencielController {

    @Autowired
    private ReferencielService service;

    @GetMapping("listTypeReclamation")
    public List<TypeReclamation> getAllTypeReclamation(){
        return service.getAllTypesReclamations();
    }

    @GetMapping("sousTypeReclamationsOfTypeReclamation/{id}")
    public List<SousTypeReclamation> getSousTypeReclamationsOfTypeReclamation(@PathVariable Long id){
        return service.findSousTypeReclamationsOfTypeReclamation(id);
    }

    @PostMapping("saveCommunityPdf")
    public void saveCommunityPdf(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        service.saveCommunityPdf(multipartFile);
    }

    @GetMapping(value = "community_presentation.pdf")
    public ResponseEntity<InputStreamResource> downloadCommunityPdf() throws IOException {

        File file = service.downloadCommunityPdf();
        InputStreamResource resource = new InputStreamResource(new
                FileInputStream(file));
        return ResponseEntity.ok().body(resource);

    }

    @GetMapping(value = "word_of_president.pdf")
    public ResponseEntity<InputStreamResource> downloadMotPresidentPdf() throws IOException {

        File file = service.downloadMotPresidentPdf();
        InputStreamResource resource = new InputStreamResource(new
                FileInputStream(file));
        return ResponseEntity.ok().body(resource);

    }

    @GetMapping(value = "organigrame.pdf")
    public ResponseEntity<InputStreamResource> downloadOrganigrametPdf() throws IOException {

        File file = service.downloadOrganigrametPdf();
        InputStreamResource resource = new InputStreamResource(new
                FileInputStream(file));
        return ResponseEntity.ok().body(resource);

    }


}
