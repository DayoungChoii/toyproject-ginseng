package com.dayoung.ginseng.member.repository;

import com.dayoung.ginseng.member.domain.MemberVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MybatisMemberMapper extends MemberDao {
    @Override
    void insertMember(MemberVo memberVo);

    @Override
    MemberVo selectMember(String id);

    @Override
    MemberVo selectMemberByIdAndPassword(MemberVo member);
}
