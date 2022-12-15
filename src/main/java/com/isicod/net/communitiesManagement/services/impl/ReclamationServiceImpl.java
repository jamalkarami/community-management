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
        if (!Files.exists(Paths.get(outPath))) {
            new File(outPath).mkdir();
        }

        String  outPath2=outPath+reclamationDto.getId();


        if (!Files.exists(Paths.get(outPath2))) {
            new File(outPath2).mkdir();
        }
        Status s= statusRepository.findByCode("EV");
        System.out.println("<<<<<<<<<"+s.getLibelle());

//        Reclamation reclamation= reclamationRepository.save(reclamationMapper.ReclamationDtoToReclamation(reclamationDto));
//
//        for(MultipartFile file:multipart){
//            int i=0;
//            if(reclamationDto.getChemainPremierPhoto()==null){
//                reclamationDto.setChemainPremierPhoto(outPath2+slash+file.getOriginalFilename()+"TEXTTEXT0"+reclamation.getId()+i++);
//            }
//            else {
//                if(reclamationDto.getChemainPremierPhoto()!=null && reclamationDto.getChemainDeuxsiemePhoto()==null){
//                    reclamationDto.setChemainPremierPhoto(outPath2+slash+file.getOriginalFilename()+"TEXTTEXT"+reclamation.getId()+i++);
//                }
//            }
//
//            byte[] data=file.getBytes();
//            Path path= Paths.get(outPath2+slash+file.getOriginalFilename()+"TEXTTEXT");
//
//            Files.write(path, data);
//
//        }
        Reclamation r=reclamationMapper.ReclamationDtoToReclamation(reclamationDto);
        r.setStatus(s);
        reclamationRepository.save(r);
    }

    @Override
    public List<Reclamation> getReclamationsOfGerant(Long idGerant,String status) {
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
    public List<Reclamation> nonSatisfait(Long idUser) {
        return reclamationRepository.getReclamationNonSatisfait(idUser);
    }

    @Override
    public Reclamation citoyenSatisfaitNonSatisfait(Long idReclamation, String satisfait) {
        Reclamation reclamation=  reclamationRepository.getOne(idReclamation);

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
