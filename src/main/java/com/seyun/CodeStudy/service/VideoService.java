package com.seyun.CodeStudy.service;

import com.seyun.CodeStudy.common.VideoSearchDto;
import com.seyun.CodeStudy.common.Pagination;
import com.seyun.CodeStudy.common.Paging;
import com.seyun.CodeStudy.mapper.LectureMapper;
import com.seyun.CodeStudy.mapper.VideoMapper;
import com.seyun.CodeStudy.model.lecture.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoMapper videoMapper;
    private final LectureMapper lectureMapper;

    @Transactional
    public void create(final Video params){
        videoMapper.create(params);
    }

    @Transactional
    public Long deleteByVideoId(final Long videoId){
        videoMapper.deleteByVideoId(videoId);
        return videoId;
    }

    public void deleteByLectureId(final Long lectureId){
        videoMapper.deleteByLectureId(lectureId);
    }

    public Paging<Video> findAllVideo(final VideoSearchDto params){
        int count = videoMapper.count(params);
        if(count < 1){
            return new Paging<>(Collections.emptyList(), null);
        }
        Pagination pagination = new Pagination(count, params);
        List<Video> list = videoMapper.findAllVideo(params);
        return new Paging<>(list, pagination);
    }

    public Video findVideoByVideoId(final Long videoId){
        return videoMapper.findVideoByVideoId(videoId);
    }

    public Video findLatestVideo(final Long lectureId){
        return videoMapper.findLatestVideo(lectureId);
    }
}
