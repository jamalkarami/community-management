package com.isicod.net.communitiesManagement.services.impl;

import com.isicod.net.communitiesManagement.dto.ReclamationDto;
import com.isicod.net.communitiesManagement.models.Citoyen;
import com.isicod.net.communitiesManagement.models.Gerant;
import com.isicod.net.communitiesManagement.models.Reclamation;
import com.isicod.net.communitiesManagement.models.Users;
import com.isicod.net.communitiesManagement.repositories.CitoyenRepository;
import com.isicod.net.communitiesManagement.repositories.GerantRepository;
import com.isicod.net.communitiesManagement.repositories.ReclamationRepository;
import com.isicod.net.communitiesManagement.repositories.UsersRepository;
import com.isicod.net.communitiesManagement.services.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ReclamationServiceImpl implements ReclamationService {

    @Autowired
    private CitoyenRepository citoyenRepository;
    @Autowired
    private ReclamationRepository reclamationRepository;
    @Autowired
    private GerantRepository gerantRepository;
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<Reclamation> findReclamationsofCitoyen(Long id) {
        Users users=usersRepository.findById(id).get();
        return reclamationRepository.findByUsers(users);
    }

    @Override
    public void saveReclamation(Reclamation reclamation) {
        reclamation.getStatus().setCode("EV");
        reclamationRepository.save(reclamation);
    }

    @Override
    public List<Reclamation> getReclamationsOfGerant(Long idGerant) {
        Gerant gerant=gerantRepository.findById(idGerant).get();
        return reclamationRepository.getReclamationsOfGerant(gerant.getProfil().getId());
    }

    @Override
    public void reclamationSeenByGerant(Long idReclamation) {
        Reclamation reclamation=reclamationRepository.findById(idReclamation).get();
        reclamation.setSeenByGerant(true);
        reclamationRepository.save(reclamation);
    }

    @Override
    public List<Reclamation> getReclamationsOfPresident(int secondes) {
        List<Reclamation> reclamationsOfPresident=reclamationRepository.getReclamationsOfPresident(secondes);
        reclamationsOfPresident.forEach(reclamation->{
            reclamation.setSeenByGerant(false);
            reclamationRepository.save(reclamation);
        });
        return reclamationsOfPresident;
    }

    @Override
    public void saveReclamationWithPhotos(ReclamationDto reclamationDto, List<MultipartFile> files) {


    }

    @Override
    public List<Reclamation> getReclamationByStatus(Long idUser, String status) {
       return reclamationRepository.getReclamationByStatus(idUser,status);
    }
}
