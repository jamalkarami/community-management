package com.isicod.net.communitiesManagement.controllers;

import com.isicod.net.communitiesManagement.models.Pharmacie;
import com.isicod.net.communitiesManagement.services.PharmacieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("apis/pharmacie/")
@CrossOrigin("*")
public class PharmacieController {

    @Autowired
    private PharmacieService pharmacieService;

    @PostMapping(value = "savePharmacie")
    public void savePharmacie(@RequestBody Pharmacie pharmacie){
         pharmacieService.savePharmacie(pharmacie);
    }

    @GetMapping(value = "getListPharmacieSunday")
    public List<Pharmacie> getListPharmacieSunday(){
        return pharmacieService.getListPharmacie();
    }
}
