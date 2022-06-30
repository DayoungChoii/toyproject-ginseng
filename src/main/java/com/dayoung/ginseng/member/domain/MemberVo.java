package com.dayoung.ginseng.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberVo {
    private String memberId;
    private String id;
    private String password;
    private String nickname;

    public MemberVo(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
