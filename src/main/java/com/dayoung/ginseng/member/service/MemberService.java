package com.dayoung.ginseng.member.service;

import com.dayoung.ginseng.member.domain.MemberRegisterForm;
import com.dayoung.ginseng.member.domain.MemberVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface MemberService {

    void register(MemberRegisterForm form, MultipartFile profileFile) throws NoSuchAlgorithmException, IOException;

    boolean isExistedMember(String id);

    MemberVo login(String id, String password) throws NoSuchAlgorithmException;
}
