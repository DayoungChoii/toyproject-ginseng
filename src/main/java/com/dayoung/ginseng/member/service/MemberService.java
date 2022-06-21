package com.dayoung.ginseng.member.service;

import com.dayoung.ginseng.member.domain.MemberForm;
import com.dayoung.ginseng.member.domain.MemberVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface MemberService {

    void register(MemberForm form, MultipartFile profileFile) throws NoSuchAlgorithmException, IOException;

    boolean isExistedMember(String id);
}
