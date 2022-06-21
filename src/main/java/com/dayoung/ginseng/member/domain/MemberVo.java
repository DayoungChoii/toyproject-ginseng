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
}
