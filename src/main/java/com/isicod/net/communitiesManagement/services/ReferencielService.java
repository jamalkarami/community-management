package com.isicod.net.communitiesManagement.services;

import com.isicod.net.communitiesManagement.models.SousTypeReclamation;
import com.isicod.net.communitiesManagement.models.TypeReclamation;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ReferencielService {

    public List<TypeReclamation> getAllTypesReclamations();

    public List<SousTypeReclamation> findSousTypeReclamationsOfTypeReclamation(Long id);

    public void saveCommunityPdf(MultipartFile file) throws IOException;

    public File downloadCommunityPdf() throws IOException;

    public File downloadMotPresidentPdf() throws IOException;

    public File downloadOrganigrametPdf() throws IOException;

}
