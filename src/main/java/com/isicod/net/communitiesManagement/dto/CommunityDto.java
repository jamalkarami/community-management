package com.isicod.net.communitiesManagement.dto;

import org.springframework.web.multipart.MultipartFile;

public class CommunityDto {
    private Long id;
    private String libelle;
    private String code;
    private String chemainPdf;

    MultipartFile filePdf;
}
