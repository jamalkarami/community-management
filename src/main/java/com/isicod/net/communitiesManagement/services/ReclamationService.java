package com.isicod.net.communitiesManagement.services;

import com.isicod.net.communitiesManagement.dto.ReclamationDto;
import com.isicod.net.communitiesManagement.dto.SuggestionDto;
import com.isicod.net.communitiesManagement.models.Reclamation;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReclamationService {

    public List<Reclamation> findReclamationsofCitoyen(Long id);

    public void saveReclamation(ReclamationDto reclamation, List<MultipartFile> multipart) throws IOException;

    public List<Reclamation> getReclamationsOfGerant(Long idGerant,String status);

    public void reclamationSeenByGerant(Long idReclamation);

    public List<Reclamation> getReclamationsOfPresident(int secondes);

    public void saveReclamationWithPhotos(ReclamationDto reclamationDto, List<MultipartFile> files);

    public List<Reclamation> getReclamationByStatus(Long idUser, String status);

    public Reclamation changeStatusToTraitement(Long idReclamation);

    public Reclamation changeStatusToTraite(Long idReclamation);

    public List<Reclamation> nonSatisfait();

    public Reclamation citoyenSatisfaitNonSatisfait(Long idUser, String satisfait);

    public Reclamation cloturerReclamation(Long idReclamation);
}
