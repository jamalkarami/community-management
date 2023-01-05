package com.isicod.net.communitiesManagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ELUS")
public class Elus extends Users{

    private String position;

    private String circonscrption;


}
