package com.isicod.net.communitiesManagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "NEWS")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private Boolean validation;

    private LocalDateTime createdAt;

    private String pathFirstPhoto;
    private String pathSecondPhoto;

    @ManyToOne
    private Users users;

    @PrePersist
    public void onCreate(){
        setCreatedAt(LocalDateTime.now());
    }

}
