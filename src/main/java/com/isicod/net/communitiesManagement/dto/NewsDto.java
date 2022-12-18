package com.isicod.net.communitiesManagement.dto;

import com.isicod.net.communitiesManagement.models.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDto {
    private String title;
    private String description;
    private Users users;
    List<MultipartFile> files;
}
