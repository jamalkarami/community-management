package com.isicod.net.communitiesManagement.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface PhotoService {
    public File downloadFile(String Url) throws IOException;

    public List<String> getPhotos(Long idReclamation,String path) ;
}
