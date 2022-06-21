package com.dayoung.ginseng.member.advice;

import com.dayoung.ginseng.member.exception.MemberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Slf4j
@ControllerAdvice
public class MemberAdvice {

    @ExceptionHandler
    public ModelAndView MemberExceptionHandle(MemberException e) {
        HashMap<String, String> modelMap = new HashMap<>();
        modelMap.put("errorMsg", e.getMessage());
        return new ModelAndView("member/memberTryAgain", modelMap);
    }
}
