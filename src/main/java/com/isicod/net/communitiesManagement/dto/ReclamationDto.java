package com.isicod.net.communitiesManagement.dto;

import com.isicod.net.communitiesManagement.models.SousTypeReclamation;
import com.isicod.net.communitiesManagement.models.Status;
import com.isicod.net.communitiesManagement.models.TypeReclamation;
import com.isicod.net.communitiesManagement.models.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReclamationDto {

    private Long id;
    private Boolean seenByGerant;
    private Boolean resolved;
    private String categorie;
    private String autreCategorie;
    private String commentaire;
    private String message;
    private Date createdAt;
    private String chemainPremierPhoto;
    private String chemainDeuxsiemePhoto;
//    private String adresse;
    private String cartier;
    private String rue;
    private Long numero;
    private Long numeroAppartement;
    private TypeReclamation typeReclamation;
    private SousTypeReclamation innerTypeReclamation;
    private Users users;
    private Status status;

    List<MultipartFile> multipartPhotos;

    MultipartFile multipartVideo;
}
