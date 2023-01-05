package com.isicod.net.communitiesManagement.controllers;

import com.isicod.net.communitiesManagement.models.Elus;
import com.isicod.net.communitiesManagement.services.ElusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("apis/elus/")
public class ElusController {

    @Autowired
    private ElusService elusService;

    @GetMapping(value = "getAllElus")
    public List<Elus> getAllElus(){
        return elusService.getAllElus();
    }
}
