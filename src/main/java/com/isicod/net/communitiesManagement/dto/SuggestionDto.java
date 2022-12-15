package com.isicod.net.communitiesManagement.dto;

import com.isicod.net.communitiesManagement.models.SousTypeReclamation;
import com.isicod.net.communitiesManagement.models.Status;
import com.isicod.net.communitiesManagement.models.TypeReclamation;
import com.isicod.net.communitiesManagement.models.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionDto {
    private Long id;
    private Boolean seenByGerant;
    private Boolean resolved;
    private String categorie;
    private String autreCategorie;
    private String commentaire;
    private String message;
    private Date createdAt;
    private TypeReclamation typeSuggestion;
    private SousTypeReclamation innerTypeSuggestion;
    private Users users;
    private Status status;

    List<MultipartFile> files;
}
