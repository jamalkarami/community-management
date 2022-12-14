package com.isicod.net.communitiesManagement.controllers;

import com.isicod.net.communitiesManagement.models.SousTypeReclamation;
import com.isicod.net.communitiesManagement.models.TypeReclamation;
import com.isicod.net.communitiesManagement.services.ReferencielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apis/referenciel/")
@CrossOrigin("*")
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
}
