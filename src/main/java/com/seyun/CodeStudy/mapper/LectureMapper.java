package com.seyun.CodeStudy.mapper;

import com.seyun.CodeStudy.common.SearchDto;
import com.seyun.CodeStudy.model.lecture.Lecture;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LectureMapper {

    void create(Lecture params);

    List<Lecture> findAllLecture(SearchDto params);

    int count(SearchDto params);

    Lecture findByLectureId(Long lectureId);

    void update(Lecture params);

    void viewCntUp(Long lectureId);

    void deleteByLectureId(Long lectureId);

    void updateUploadDate(Long lectureId);

}
