package com.seyun.CodeStudy.mapper;

import com.seyun.CodeStudy.common.VideoSearchDto;
import com.seyun.CodeStudy.model.lecture.Video;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VideoMapper {

    void create(Video params);

    void deleteByVideoId(Long videoId);

    void deleteByLectureId(Long lectureId);

    List<Video> findAllVideo(VideoSearchDto params);

    int count(VideoSearchDto params);

    Video findVideoByVideoId(Long videoId);

    Video findLatestVideo(Long lectureId);
}
