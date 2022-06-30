package com.dayoung.ginseng.member.repository;

import com.dayoung.ginseng.member.domain.MemberVo;

public interface MemberDao {

    public void insertMember(MemberVo memberVo);

    public MemberVo selectMember(String id);

    public MemberVo selectMemberByIdAndPassword(MemberVo member);
}

