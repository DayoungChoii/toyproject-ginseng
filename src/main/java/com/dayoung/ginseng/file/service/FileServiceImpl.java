package com.dayoung.ginseng.file.service;

import com.dayoung.ginseng.file.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    @Value("${file.profile.dir}")
    private String profileFileDir;

    @Override
    public String getFullPath(String fileName) {
        return profileFileDir + fileName;
    }

    @Override
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        String realFileName = multipartFile.getOriginalFilename();
        String localFileName = createLocalFileName(realFileName);
        String fullPath = getFullPath(localFileName);
        multipartFile.transferTo(new File(fullPath));
        return new UploadFile(realFileName, localFileName, fullPath);
    }

    @Override
    public String createLocalFileName(String realFileName) {
        String ext = extractExt(realFileName);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String realFileName) {
        int pos = realFileName.lastIndexOf(".");
        return realFileName.substring(pos + 1);
    }
}
