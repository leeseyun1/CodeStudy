package com.seyun.CodeStudy.controller;

import com.seyun.CodeStudy.common.FileUtils;
import com.seyun.CodeStudy.common.Paging;
import com.seyun.CodeStudy.common.SearchDto;
import com.seyun.CodeStudy.model.lecture.Lecture;
import com.seyun.CodeStudy.model.lecture.Video;
import com.seyun.CodeStudy.model.lecture.Thumbnail;
import com.seyun.CodeStudy.model.member.Member;
import com.seyun.CodeStudy.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;
    private final MemberService memberService;
    private final TagService tagService;
    private final ThumbnailService thumbnailService;
    private final VideoService videoService;
    private final FileUtils fileUtils;


    @GetMapping("/lecture/write")
    public String lectureWrite (@RequestParam(value = "lectureId", required = false) final Long lectureId,
                                Model model, Principal principal){
        Member member = memberService.findMemberById(principal.getName());
        model.addAttribute("member", member);
        if(lectureId != null){
            Lecture lecture = lectureService.findByLectureId(lectureId);
            model.addAttribute("lecture", lecture);
        }
        return "/lecture/write";
    }

    @GetMapping("/lecture/list")
    public String lectureList (@ModelAttribute("params") final SearchDto params, Model model){
        Paging<Lecture> lectures = lectureService.findAllLecture(params);
        model.addAttribute("lectures", lectures);
        return "/lecture/list";
    }

    @GetMapping("/lecture/view")
    public String lectureView (@RequestParam final Long lectureId, Model model,
                               Principal principal){
        if(principal != null){
            Member member = memberService.findMemberById(principal.getName());
            model.addAttribute("member", member);
        }

        Lecture lecture = lectureService.findByLectureId(lectureId);
        model.addAttribute("lecture", lecture);
        return "/lecture/view";
    }

    @PostMapping("/lecture/save")
    public String lectureSave(@RequestParam(value = "multipartFile", required = false) final MultipartFile multipartFile,
                              final Lecture params,
                              final String[] tagContents){
        Long lectureId = lectureService.create(params);
        if(multipartFile.isEmpty() == false){
            Thumbnail thumbnail = fileUtils.uploadThumbnail(multipartFile);
            thumbnailService.create(lectureId, thumbnail);
        }
        if(tagContents != null){
            tagService.saveTag(tagContents, lectureId);
        }
        return "redirect:/lecture/list";
    }

    @PostMapping("/lecture/update")
    public String lectureUpdate(@RequestParam(value = "multipartFile", required = false) final MultipartFile multipartFile,
                                @RequestParam(value = "thumbnailModified") final Boolean thumbnailModified,
                                @RequestParam(value = "thumbnailId", required = false) final Long thumbnailId,
                                final Lecture params, final String[] tagContents){
        if(thumbnailModified){
            thumbnailService.deleteByThumbnailId(thumbnailId);
            System.out.println(thumbnailId);
            if(multipartFile.isEmpty() == false){
                Thumbnail thumbnail = fileUtils.uploadThumbnail(multipartFile);
                thumbnailService.create(params.getLectureId(), thumbnail);
            }
        }

        tagService.deleteTagMap(params.getLectureId());
        if(tagContents != null){
            tagService.saveTag(tagContents, params.getLectureId());
        }
        lectureService.update(params);
        return "redirect:/lecture/list";
    }

    @PostMapping("/video/upload")
    public String videoUpload(@RequestParam(value = "multipartFile") final MultipartFile multipartFile,
                              final Video params){

        Video video = fileUtils.uploadVideo(multipartFile);
        video.setLectureId(params.getLectureId());
        video.setTitle(params.getTitle());
        videoService.create(video);
        //업로드 후에 해당 강의의 uploadDate수정
        lectureService.updateUploadDate(params.getLectureId());
        return "redirect:/lecture/view?lectureId="+params.getLectureId();
    }

    @PostMapping("/lecture/delete")
    public String lectureDelete(final Long lectureId){
        lectureService.deleteByLectureId(lectureId);
        videoService.deleteByLectureId(lectureId);
        thumbnailService.deleteByLectureId(lectureId);
        return "redirect:/lecture/list";
    }
}
