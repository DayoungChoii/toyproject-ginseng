package com.dayoung.ginseng;

import com.dayoung.ginseng.member.domain.MemberLoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class PortalController {
    @GetMapping("/")
    public ModelAndView index(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        //세션이 없으면 로그인 페이지
        if(session == null){
            HashMap<String, MemberLoginForm> modelMap = new HashMap<>();
            modelMap.put("memberLoginForm", new MemberLoginForm());
            return new ModelAndView("member/login", modelMap);
        }

        //세션이 있으면 대쉬보드
        return new ModelAndView("dashboard/index");
    }
}
