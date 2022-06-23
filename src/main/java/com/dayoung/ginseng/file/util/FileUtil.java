package com.dayoung.ginseng.file.util;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
    public static boolean doesFileExist(MultipartFile file){
        return !file.isEmpty();
    }
}
