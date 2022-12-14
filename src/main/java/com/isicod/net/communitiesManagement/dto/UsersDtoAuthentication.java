package com.isicod.net.communitiesManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDtoAuthentication {

    private String telephone;
    private String password;
}
