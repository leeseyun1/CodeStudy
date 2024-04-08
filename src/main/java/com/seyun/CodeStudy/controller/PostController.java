package com.seyun.CodeStudy.controller;

import com.seyun.CodeStudy.common.Paging;
import com.seyun.CodeStudy.common.SearchDto;
import com.seyun.CodeStudy.model.member.Member;
import com.seyun.CodeStudy.model.post.Post;
import com.seyun.CodeStudy.service.PostService;
import com.seyun.CodeStudy.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/post/write")
    public String postWrite(@RequestParam(value="postId", required = false) final Long postId,
                            Model model, Principal principal){
        Member member = memberService.findMemberById(principal.getName());
        model.addAttribute("member", member);
        if(postId != null){
            Post post = postService.findByPostId(postId);
            model.addAttribute("post", post);
        }
        return "/post/write";
    }

    @GetMapping("/post/list")
    public String postList(@ModelAttribute("params") final SearchDto params, Model model){
        Paging<Post> posts = postService.findAllPost(params);
        model.addAttribute("posts", posts);
        return "/post/list";
    }

    @GetMapping("/post/view")
    public String postView(@RequestParam final Long postId, Model model,
                           Principal principal){
        if(principal != null){
            Member member = memberService.findMemberById(principal.getName());
            model.addAttribute("member", member);
        }
        Post post = postService.findByPostId(postId);
        model.addAttribute("post", post);
        return "/post/view";
    }

    @PostMapping("/post/save")
    public String postSave(final Post params){
        Long postId = postService.create(params);
        return "redirect:/post/list";
    }

    @PostMapping("/post/update")
    public String postUpdate(final Post params){
        postService.update(params);
        return "redirect:/post/list";
    }

    @PostMapping("/post/delete")
    public String postDelete(final Long postId){
        postService.delete(postId);
        return "redirect:/post/list";
    }
}
