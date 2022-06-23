package com.dayoung.ginseng.file.repository;

import com.dayoung.ginseng.file.domain.UploadFile;
import com.dayoung.ginseng.member.domain.MemberVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

public interface FileDBDao {
    public void insertFile(UploadFile uploadFile);
}
