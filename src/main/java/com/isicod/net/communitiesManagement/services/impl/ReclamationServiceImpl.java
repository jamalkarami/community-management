package com.isicod.net.communitiesManagement.services.impl;

import com.isicod.net.communitiesManagement.dto.ReclamationDto;
import com.isicod.net.communitiesManagement.mapper.ReclamationMapper;
import com.isicod.net.communitiesManagement.models.*;
import com.isicod.net.communitiesManagement.repositories.*;
import com.isicod.net.communitiesManagement.services.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReclamationServiceImpl implements ReclamationService {

    @Value("${outPath}")
    String outPath;

    @Value("${slash}")
    String slash;

    @Autowired
    private CitoyenRepository citoyenRepository;
    @Autowired
    private ReclamationRepository reclamationRepository;
    @Autowired
    private GerantRepository gerantRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ReclamationMapper reclamationMapper;
    @Autowired
    StatusRepository statusRepository;

    @Override
    public List<Reclamation> findReclamationsofCitoyen(Long id) {
        Users users=usersRepository.findById(id).get();
        return reclamationRepository.findByUsers(users);
    }

    @Override
    public void saveReclamation(ReclamationDto reclamationDto,List<MultipartFile> multipart) throws IOException {
        List<ReclamationDto> reclamationDtoList= new ArrayList<ReclamationDto>();

        Status s= statusRepository.findByCode("EV");
        reclamationDto.setStatus(s);
        Reclamation reclamation= reclamationRepository.save(reclamationMapper.ReclamationDtoToReclamation(reclamationDto));
        if(multipart!=null){
            if (!Files.exists(Paths.get(outPath))) {
                new File(outPath).mkdir();
            }

            String  outPath2=outPath+reclamationDto.getId();


            if (!Files.exists(Paths.get(outPath2))) {
                new File(outPath2).mkdir();
            }

            for(MultipartFile file:multipart){
                int i=0;
                if(reclamation.getChemainPremierPhoto()==null){
                    reclamation.setChemainPremierPhoto(outPath2+slash+file.getOriginalFilename()+"1"+reclamation.getId()+i++);
                }
                else {
                    if(reclamation.getChemainPremierPhoto()!=null && reclamation.getChemainDeuxsiemePhoto()==null){
                        reclamation.setChemainDeuxsiemePhoto(outPath2+slash+file.getOriginalFilename()+"2"+reclamation.getId()+i++);
                    }
                }

                byte[] data=file.getBytes();
                Path path= Paths.get(outPath2+slash+file.getOriginalFilename()+"TEXTTEXT"+reclamation.getId()+i++);

                Files.write(path, data);

            }

            reclamationRepository.save(reclamation);
        }


    }

    @Override
    public List<Reclamation> getReclamationsOfGerant(Long idGerant,String status) {
        System.out.println(idGerant);
        Gerant gerant=gerantRepository.findById(idGerant).get();
        return reclamationRepository.getReclamationsOfGerant(gerant.getProfil().getId(),status);
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

    @Override
    public Reclamation changeStatusToTraitement(Long idReclamation) {
        Reclamation reclamation= reclamationRepository.getOne(idReclamation);
        Status st= new Status(2L,"ET","Traitement");
        reclamation.setStatus(st);
       return reclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation changeStatusToTraite(Long idReclamation) {
        Reclamation reclamation= reclamationRepository.getOne(idReclamation);
        Status st= new Status(3L,"TR","Traité");
        reclamation.setStatus(st);
        return reclamationRepository.save(reclamation);
    }

    @Override
    public List<Reclamation> nonSatisfait() {
        return reclamationRepository.getReclamationNonSatisfait();
    }

    @Override
    public Reclamation citoyenSatisfaitNonSatisfait(Long idReclamation, String satisfait) {
        Reclamation reclamation=  reclamationRepository.getOne(idReclamation);
        if(satisfait.equals("Y")){
            Status s = statusRepository.findByCode("CL");
            reclamation.setStatus(s);
        }
        reclamation.setSatisfait(satisfait);

       return reclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation cloturerReclamation(Long idReclamation) {
        Reclamation reclamation= reclamationRepository.getOne(idReclamation);
        Status st= new Status(4L,"CL","Clôturé");
        reclamation.setStatus(st);
        return reclamationRepository.save(reclamation);
    }
}
