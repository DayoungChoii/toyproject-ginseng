package com.dayoung.ginseng.member.domain;

import com.dayoung.ginseng.file.domain.UploadFile;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
public class MemberRegisterForm {
    @NotBlank
    @Email
    @Size(min=1 ,max=40)
    private String id;

    @NotBlank
    @Size(min=1 ,max=40)
    private String password;

    @NotBlank
    @Size(min=1 ,max=40)
    private String passwordCheck;

    @NotBlank
    @Size(min= 1,max=20)
    private String nickname;

    private UploadFile uploadFile;
}
