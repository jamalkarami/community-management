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
    private StatusRepository statusRepository;
    @Autowired
    private PhotosRepository photosRepository;
    @Autowired
    private VideosRepository videosRepository;

    @Override
    public List<Reclamation> findReclamationsofCitoyen(Long id) {
        Users users=usersRepository.findById(id).get();
        return reclamationRepository.findByUsers(users);
    }

    @Override
    public void saveReclamation(ReclamationDto reclamationDto,List<MultipartFile> multipartPhotos,MultipartFile multipartVideo) throws IOException {
        Status s= statusRepository.findByCode("EV");
        reclamationDto.setStatus(s);

        Reclamation reclamation= reclamationRepository.save(reclamationMapper.ReclamationDtoToReclamation(reclamationDto));
        if(multipartPhotos!=null){
            if (!Files.exists(Paths.get(outPath))) {
                new File(outPath).mkdir();
            }
            int i=0;
            for(MultipartFile file:multipartPhotos){
                Photos photosReclamation=new Photos();
                photosReclamation.setChemain("reclamation-"+ reclamation.getId()+ "-" + i+"-" + file.getOriginalFilename());
                photosReclamation.setReclamation(reclamation);
                photosRepository.save(photosReclamation);

                byte[] data=file.getBytes();
                Path path= Paths.get(outPath+slash+"reclamation-"+ reclamation.getId()+ "-" + i+"-" + file.getOriginalFilename());

                Files.write(path, data);
                i++;

            }

        }
        if(multipartVideo!=null) {
            if (!Files.exists(Paths.get(outPath))) {
                new File(outPath).mkdir();
            }
            Videos videos= new Videos();
            videos.setChemain("reclamation-"+ reclamation.getId()+ "-" + multipartVideo.getOriginalFilename());
            videos.setReclamation(reclamation);
            videosRepository.save(videos);

            byte[] data=multipartVideo.getBytes();
            Path path= Paths.get(outPath+slash+"reclamation-"+ reclamation.getId()+ "-"  + multipartVideo.getOriginalFilename());

            Files.write(path, data);

        }
        reclamationRepository.save(reclamation);


    }

    @Override
    public List<Reclamation> getReclamationsOfGerant(Long idGerant,String status) {
        Gerant gerant=gerantRepository.findById(idGerant).get();
            return reclamationRepository.getReclamationsOfGerant(gerant.getProfil().getId(),status);
    }

    @Override
    public List<Reclamation> getReclamationsOfGerantTRS(Long idGerant, String status) {
        Gerant gerant=gerantRepository.findById(idGerant).get();
        return reclamationRepository.getReclamationsOfGerantTRS(gerant.getProfil().getId(),status);
    }

    @Override
    public List<Reclamation> getReclamationsOfGerantTRNS(Long idGerant, String status) {
        Gerant gerant=gerantRepository.findById(idGerant).get();
        return reclamationRepository.getReclamationsOfGerantTRNS(gerant.getProfil().getId(),status);
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
        if(status.equals("TRN")){
          return  reclamationRepository.getReclamationByStatusAndNoSatisfait(idUser);
        }
        else{
            return    reclamationRepository.getReclamationByStatus(idUser,status);
        }
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
        Status st= new Status(3L,"TR","Trait??");
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
    public Reclamation cloturerReclamation(Long idReclamation, String message) {

        Reclamation reclamation= reclamationRepository.getOne(idReclamation);
        if(!message.isEmpty()){
            reclamation.setMessageCloture(message);
        }
        Status st= new Status(4L,"CL","Cl??tur??");
        reclamation.setStatus(st);
        return reclamationRepository.save(reclamation);
    }

    @Override
    public File downloadReclamationFile(String Url) throws IOException{
        File file = new File(this.outPath+Url);
        if (!file.exists() || !file.isFile()) {
            throw new IOException();
        }
        return file;
    }

    @Override
    public List<String> getPhotoReclamation(Long idReclamation) {
       List<Photos> photos=photosRepository.findByReclamation(idReclamation);
        List<String> photosUrl= new ArrayList<String>();

        for(Photos photo:photos){
            photosUrl.add("reclamation/downloadReclamationFile/"+photo.getChemain());
        }


       return photosUrl;


    }

    @Override
    public List<Reclamation> getGerantByStatutAndNonSatisfait(Long idUser) {
        Gerant gerant=gerantRepository.findById(idUser).get();
        return reclamationRepository.getReclamationByStatusAndNonSatifait(gerant.getProfil().getId());
    }

    @Override
    public List<Reclamation> getAllReclamationCloture() {
        return reclamationRepository.getReclamationCloture();
    }


}
