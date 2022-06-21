package com.dayoung.ginseng.member.controller;

import com.dayoung.ginseng.file.domain.UploadFile;
import com.dayoung.ginseng.file.service.FileDBService;
import com.dayoung.ginseng.file.service.FileService;
import com.dayoung.ginseng.file.util.FileUtil;
import com.dayoung.ginseng.member.domain.MemberVo;
import com.dayoung.ginseng.member.domain.MemberForm;
import com.dayoung.ginseng.member.exception.RegisterException;
import com.dayoung.ginseng.member.service.MemberService;
import com.dayoung.ginseng.member.util.SHA256;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/login")
    public String loginView() {
        return "member/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberForm memberForm) {
        MemberVo memberVo = new MemberVo();
        memberForm.setId(memberVo.getId());
        memberForm.setPassword(memberVo.getPassword());
        memberForm.setNickname(memberVo.getNickname());

        return "main/dashboard";
    }

    @GetMapping("/register")
    public String registerView(@ModelAttribute("memberForm") MemberForm memberForm) {
        return "member/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam MultipartFile profileFile,
                           @Validated @ModelAttribute MemberRegisterForm registerForm, BindingResult bindingResult) {
        if(memberService.isExistedMember(registerForm.getId())){
            bindingResult.reject("existedMember");
        } else if(!registerForm.getPassword().equals(registerForm.getPasswordCheck())){
            bindingResult.reject("passwordMismatch");
        }
 
        if (bindingResult.hasErrors()) {
            return "member/register";
        }

        try {
            memberService.register(registerForm, profileFile);
        } catch (NoSuchAlgorithmException e) {
            log.info("password encrypt fail, userId ={}", registerForm.getId());
            throw new MemberException(ms.getMessage("failToRegist", null, null));
        } catch (IOException e) {
            log.info("profile file upload fail, userId ={}", registerForm.getId());
            throw new MemberException(ms.getMessage("failToRegist", null, null));
        }

        return "redirect:/member/successedRegister";
    }

    @GetMapping("/successedRegister")
    public String successedRegisterView(){
        return "member/successedRegister";
    }

}
