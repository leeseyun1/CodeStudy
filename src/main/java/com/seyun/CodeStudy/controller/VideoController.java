package com.seyun.CodeStudy.controller;

import com.seyun.CodeStudy.common.Paging;
import com.seyun.CodeStudy.common.VideoSearchDto;
import com.seyun.CodeStudy.model.lecture.Video;
import com.seyun.CodeStudy.service.LectureService;
import com.seyun.CodeStudy.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class VideoController {
    private final VideoService videoService;
    private final LectureService lectureService;

    //리스트 조회
    @GetMapping("/video/list/{lectureId}")
    public Paging<Video> findAllVideo(@PathVariable final Long lectureId, final VideoSearchDto params){
        return videoService.findAllVideo(params);
    }

    //하나만 조회
    @GetMapping("/video/one/{videoId}")
    public Video findVideoByVideoId(@PathVariable final Long videoId){
        return videoService.findVideoByVideoId(videoId);
    }

    //최근 비디오 조회
    @GetMapping("/video/latest/{lectureId}")
    public Video findLatestVideo(@PathVariable final Long lectureId){
        return videoService.findLatestVideo(lectureId);
    }


    //비디오 재생
    @GetMapping(value = "/video/play/{videoId}")
    public ResponseEntity<ResourceRegion> getVideo(@RequestHeader HttpHeaders headers, @PathVariable final Long videoId) throws IOException {
        Video video = videoService.findVideoByVideoId(videoId);
        UrlResource resource = new UrlResource("file:///C:/CodeStudy/"+video.getUploadPath()+"/"+video.getSaveName());
        ResourceRegion resourceRegion;

        final long chunkSize = 1000000L;
        long contentLength = resource.contentLength();

        Optional<HttpRange> optional = headers.getRange().stream().findFirst();
        HttpRange httpRange;
        if (optional.isPresent()) {
            httpRange = optional.get();
            long start = httpRange.getRangeStart(contentLength);
            long end = httpRange.getRangeEnd(contentLength);
            long rangeLength = Long.min(chunkSize, end - start + 1);
            resourceRegion = new ResourceRegion(resource, start, rangeLength);
        } else {
            long rangeLength = Long.min(chunkSize, contentLength);
            resourceRegion = new ResourceRegion(resource, 0, rangeLength);
        }

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(resourceRegion);
    }

    //비디오 삭제
    @DeleteMapping("/video/delete/{videoId}")
    public void deleteByVideoId(@PathVariable final Long videoId){
        videoService.deleteByVideoId(videoId);
        //삭제 후에 해당 강의의 uploadDate수정
        lectureService.updateUploadDate(videoService.findVideoByVideoId(videoId).getLectureId());
    }


}
