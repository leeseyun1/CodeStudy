package com.seyun.CodeStudy.service;

import com.seyun.CodeStudy.common.Pagination;
import com.seyun.CodeStudy.common.Paging;
import com.seyun.CodeStudy.common.SearchDto;
import com.seyun.CodeStudy.mapper.LectureMapper;
import com.seyun.CodeStudy.model.lecture.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureMapper lectureMapper;

    @Transactional
    public Long create(final Lecture params){
        lectureMapper.create(params);
        return params.getLectureId();
    }

    public Paging<Lecture> findAllLecture(final SearchDto params){
        int count = lectureMapper.count(params);
        if(count < 1){
            return new Paging<>(Collections.emptyList(), null);
        }
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        List<Lecture> list = lectureMapper.findAllLecture(params);
        return new Paging<>(list, pagination);
    }

    public Lecture findByLectureId(Long lectureId){
        lectureMapper.viewCntUp(lectureId);
        return lectureMapper.findByLectureId(lectureId);
    }

    @Transactional
    public Long update(final Lecture params){
        lectureMapper.update(params);
        return params.getLectureId();
    }

    public void deleteByLectureId(final Long lectureId){
        lectureMapper.deleteByLectureId(lectureId);
    }


    public void updateUploadDate(final Long lectureId){
        lectureMapper.updateUploadDate(lectureId);
    }
}
