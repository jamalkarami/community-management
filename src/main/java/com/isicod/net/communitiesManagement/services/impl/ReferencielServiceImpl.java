package com.isicod.net.communitiesManagement.services.impl;

import com.isicod.net.communitiesManagement.models.Community;
import com.isicod.net.communitiesManagement.models.Photos;
import com.isicod.net.communitiesManagement.models.SousTypeReclamation;
import com.isicod.net.communitiesManagement.models.TypeReclamation;
import com.isicod.net.communitiesManagement.repositories.CommunityRepository;
import com.isicod.net.communitiesManagement.repositories.SousTypeReclamationRepository;
import com.isicod.net.communitiesManagement.repositories.TypeReclamationRepository;
import com.isicod.net.communitiesManagement.services.ReferencielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ReferencielServiceImpl implements ReferencielService {

    @Value("${outPathPdf}")
    String outPathPdf;

    @Value("${slash}")
    String slash;

    @Autowired
    private TypeReclamationRepository typeReclamationRepository;

    @Autowired
    private SousTypeReclamationRepository sousTypeReclamationRepository;

    @Autowired
    private CommunityRepository communityRepository;

    @Override
    public List<TypeReclamation> getAllTypesReclamations() {
        return typeReclamationRepository.findAll();
    }

    @Override
    public List<SousTypeReclamation> findSousTypeReclamationsOfTypeReclamation(Long id) {

        TypeReclamation typeReclamation=typeReclamationRepository.findById(id).get();
        return sousTypeReclamationRepository.findByTypeReclamation(typeReclamation);
    }

    @Override
    public void saveCommunityPdf(MultipartFile multipart) throws IOException {
        if (multipart != null) {
            if (!Files.exists(Paths.get(outPathPdf))) {
                new File(outPathPdf).mkdir();
            }
            int i = 0;

            Community community = new Community();
            community.setChemainPdf("-" + i + "-" + multipart.getOriginalFilename());

            communityRepository.save(community);

            byte[] data = multipart.getBytes();
            Path path = Paths.get(outPathPdf + slash + "-" + i + "-" + multipart.getOriginalFilename());

            Files.write(path, data);
            i++;


        }
    }

    @Override
    public File downloadCommunityPdf() throws IOException{
        File file = new File(this.outPathPdf+"test.pdf");
        if (!file.exists()) {
            throw new IOException();
        }
        return file;
    }

    @Override
    public File downloadMotPresidentPdf() throws IOException {
        File file = new File(this.outPathPdf+"test.pdf");
        if (!file.exists()) {
            throw new IOException();
        }
        return file;
    }

    @Override
    public File downloadOrganigrametPdf() throws IOException {
        File file = new File(this.outPathPdf+"test.pdf");
        if (!file.exists()) {
            throw new IOException();
        }
        return file;
    }
}
