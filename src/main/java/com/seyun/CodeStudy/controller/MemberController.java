package com.seyun.CodeStudy.controller;

import com.seyun.CodeStudy.common.FileUtils;
import com.seyun.CodeStudy.model.lecture.Lecture;
import com.seyun.CodeStudy.model.lecture.Thumbnail;
import com.seyun.CodeStudy.model.member.Member;
import com.seyun.CodeStudy.model.member.Profile;
import com.seyun.CodeStudy.service.MemberService;
import com.seyun.CodeStudy.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ProfileService profileService;
    private final FileUtils fileUtils;

    //로그인 페이지
    @GetMapping("/auth/login")
    public String loginPage(){
        return "/auth/login";
    }

    //회원가입 페이지
    @GetMapping("/auth/signup")
    public String signupPage(){
        return "/auth/signup";
    }
    
    //회원정보 수정 페이지
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/auth/myPage")
    public String myPage(Model model, Principal principal){
        Member loginMember = memberService.findMemberById(principal.getName());
        model.addAttribute("loginMember", loginMember);
        return "/auth/myPage";
    }

    //회원가입
    @PostMapping("/member/create")
    public String createMember(@RequestParam(value = "multipartFile", required = false) final MultipartFile multipartFile,
                               final Member member){
        memberService.create(member);
        if(multipartFile.isEmpty() == false){
            Profile profile = fileUtils.uploadProfile(multipartFile);
            profileService.create(member.getId(), profile);
        }
        return "redirect:/auth/login";
    }


    @PostMapping("/member/update")
    public String updateMember(@RequestParam(value = "multipartFile", required = false) final MultipartFile multipartFile,
                               @RequestParam(value = "profileModified") final Boolean profileModified,
                               @RequestParam(value = "profileId", required = false) final Long profileId,
                               final Member params, Principal principal){
        params.setId(principal.getName());
        if(profileModified){
            profileService.deleteByProfileId(profileId);
            if(multipartFile.isEmpty() == false){
                Profile profile = fileUtils.uploadProfile(multipartFile);
                profileService.create(params.getId(), profile);
            }
        }
        memberService.update(params);
        return "redirect:/lecture/list";
    }

}
