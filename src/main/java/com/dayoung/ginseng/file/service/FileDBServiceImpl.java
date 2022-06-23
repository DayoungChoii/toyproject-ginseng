package com.dayoung.ginseng.file.service;

import com.dayoung.ginseng.file.domain.UploadFile;
import com.dayoung.ginseng.file.repository.MybatisFileDBMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileDBServiceImpl implements FileDBService{

    private final MybatisFileDBMapper mybatisFileDBMapper;

    @Override
    public void saveFile(UploadFile uploadFile) {
        mybatisFileDBMapper.insertFile(uploadFile);
    }
}
