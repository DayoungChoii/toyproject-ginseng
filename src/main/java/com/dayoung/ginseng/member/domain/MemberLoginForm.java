package com.dayoung.ginseng.member.domain;

import com.dayoung.ginseng.file.domain.UploadFile;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
public class MemberLoginForm {
    @NotBlank
    private String id;

    @NotBlank
    private String password;
}
