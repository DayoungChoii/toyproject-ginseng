package com.dayoung.ginseng.file.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadFile {

    private String realFileName;
    private String localFileName;
    private String filePath;
    private String targetId;

    public UploadFile(String realFileName, String localFileName, String filePath) {
        this.realFileName = realFileName;
        this.localFileName = localFileName;
        this.filePath = filePath;
    }
}
