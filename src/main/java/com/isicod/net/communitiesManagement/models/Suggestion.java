package com.isicod.net.communitiesManagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SUGGESTION")
public class Suggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean seenByGerant;
    private Boolean resolved;
    private String categorie;
    private String autreCategorie;
    private String commentaire;
    private String message;


    @CreationTimestamp
    private Date createdAt;

    @ManyToOne
    private TypeReclamation typeSuggestion;

    @ManyToOne
    private SousTypeReclamation innerTypeSuggestion;

    @ManyToOne
    private Users users;

    @ManyToOne
    private Status status;
}
