package com.dayoung.ginseng.member.controller;

import com.dayoung.ginseng.member.domain.MemberLoginForm;
import com.dayoung.ginseng.member.domain.MemberRegisterForm;
import com.dayoung.ginseng.member.domain.MemberVo;
import com.dayoung.ginseng.member.exception.MemberException;
import com.dayoung.ginseng.member.service.MemberService;
import com.dayoung.ginseng.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final MessageSource ms;


    @GetMapping("/login")
    public String loginView(@ModelAttribute(name="memberLoginForm")MemberLoginForm loginForm) {
        return "member/login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute MemberLoginForm memberLoginForm
            , BindingResult bindingResult, HttpServletRequest request) {

        if(bindingResult.hasErrors()){
            return "member/login";
        }

        MemberVo loginMember;
        try {
            loginMember = memberService.login(memberLoginForm.getId(), memberLoginForm.getPassword());
        } catch (NoSuchAlgorithmException e) {
            log.info("password encrypt fail, userId ={}", memberLoginForm.getId());
            throw new MemberException(ms.getMessage("failToLogin", null, null));
        }

        if (loginMember == null) {
            bindingResult.reject("idPasswordMismatch");
            return "member/login";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.ID, loginMember.getId());
        session.setAttribute(SessionConst.MEMBER_ID, loginMember.getMemberId());

        return "redirect:/";
    }

    @GetMapping("/register")
    public String registerView(@ModelAttribute("memberRegisterForm") MemberRegisterForm registerForm) {
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
