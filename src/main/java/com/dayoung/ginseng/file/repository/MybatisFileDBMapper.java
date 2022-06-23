package com.dayoung.ginseng.file.repository;

import com.dayoung.ginseng.file.domain.UploadFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MybatisFileDBMapper extends FileDBDao{
    @Override
    public void insertFile(UploadFile uploadFile);
}
