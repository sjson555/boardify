package com.ssj.member.controller;

import com.ssj.member.service.MemberService;
import com.ssj.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@AllArgsConstructor
public class MemberController {
    private MemberService memberService;

    // 메인 페이지
    @GetMapping("/")
    public String index() {
        return "common/index";
    }

    // 회원가입 페이지
    @GetMapping("/user/signup")
    public String dispSignup() {
        return "member/signup";
    }

    // 회원가입 처리
    @PostMapping("/user/signup")
    public String execSignup(@Valid MemberDto memberDto, Errors errors, Model model) {
        if (errors.hasErrors()) {
            // 회원가입 실패시, 입력 데이터를 유지
            model.addAttribute("memberDto", memberDto);

            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = memberService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "member/signup";
        }
        memberService.joinUser(memberDto);
        return "redirect:/user/login";
    }

    // 로그인 페이지
    @GetMapping("/user/login")
    public String dispLogin() {
        return "member/login";
    }

    // 로그인 결과 페이지
    @GetMapping("/user/login/result")
    public String dispLoginResult() {
        return "member/loginSuccess";
    }

    // 로그아웃 결과 페이지
    @GetMapping("/user/logout/result")
    public String dispLogout() {
        return "member/logout";
    }

    // 접근 거부 페이지
    @GetMapping("/user/denied")
    public String dispDenied() {
        return "member/denied";
    }

    // 내 정보 페이지
    @GetMapping("/user/info")
    public String dispMyInfo() {
        return "member/myinfo";
    }

    // 어드민 페이지
    @GetMapping("/admin")
    public String dispAdmin() {
        return "member/admin";
    }
}
