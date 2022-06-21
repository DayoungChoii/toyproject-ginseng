package com.dayoung.ginseng.member.service;

import com.dayoung.ginseng.file.domain.UploadFile;
import com.dayoung.ginseng.file.service.FileDBService;
import com.dayoung.ginseng.file.service.FileService;
import com.dayoung.ginseng.file.util.FileUtil;
import com.dayoung.ginseng.member.domain.MemberRegisterForm;
import com.dayoung.ginseng.member.domain.MemberVo;
import com.dayoung.ginseng.member.repository.MemberDao;
import com.dayoung.ginseng.member.util.SHA256;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao;
    private final FileService fileService;
    private final FileDBService fileDBService;

    @Override
    public MemberVo login(String id, String password) throws NoSuchAlgorithmException {
        MemberVo member = new MemberVo(id, SHA256.encrypt(password));
        MemberVo loginMember = memberDao.selectMemberByIdAndPassword(member);

        return loginMember == null ? null : loginMember;
    }

    @Override
    @Transactional
    public void register(MemberRegisterForm memberRegisterForm, MultipartFile profileFile) throws NoSuchAlgorithmException, IOException {
        MemberVo memberVo = new MemberVo();
        setVoFromForm(memberRegisterForm, memberVo);

        if(FileUtil.doesFileExist(profileFile)){
            UploadFile uploadFile = fileService.storeFile(profileFile);
            uploadFile.setTargetId(memberVo.getMemberId());
            fileDBService.saveFile(uploadFile);
        }

        memberDao.insertMember(memberVo);
    }

    @Override
    public boolean isExistedMember(String id) {
        MemberVo memberVo = memberDao.selectMember(id);
        return memberVo == null ? false : true;
    }

    private void setVoFromForm(MemberRegisterForm memberRegisterForm, MemberVo memberVo) throws NoSuchAlgorithmException {
        memberVo.setMemberId("M" + UUID.randomUUID().toString());
        memberVo.setId(memberRegisterForm.getId());
        memberVo.setNickname(memberRegisterForm.getNickname());
        memberVo.setPassword(SHA256.encrypt(memberRegisterForm.getPassword()));
    }
}
