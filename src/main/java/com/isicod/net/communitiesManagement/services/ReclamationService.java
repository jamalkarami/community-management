package com.isicod.net.communitiesManagement.services;

import com.isicod.net.communitiesManagement.dto.ReclamationDto;
import com.isicod.net.communitiesManagement.models.Reclamation;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReclamationService {

    public List<Reclamation> findReclamationsofCitoyen(Long id);

    public void saveReclamation(Reclamation reclamation);

    public List<Reclamation> getReclamationsOfGerant(Long idGerant);

    public void reclamationSeenByGerant(Long idReclamation);

    public List<Reclamation> getReclamationsOfPresident(int secondes);

    public void saveReclamationWithPhotos(ReclamationDto reclamationDto, List<MultipartFile> files);
}
