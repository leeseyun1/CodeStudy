package com.seyun.CodeStudy.service;

import com.seyun.CodeStudy.mapper.ThumbnailMapper;
import com.seyun.CodeStudy.model.lecture.Thumbnail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ThumbnailService {

    private final ThumbnailMapper thumbnailMapper;

    @Transactional
    public void create(final Long lectureId, final Thumbnail thumbnail){
        thumbnail.setLectureId(lectureId);
        thumbnailMapper.create(thumbnail);
    }

    @Transactional
    public void deleteByThumbnailId(final Long thumbnailId){
        thumbnailMapper.deleteByThumbnailId(thumbnailId);
    }

    public void deleteByLectureId(final Long lectureId){
        thumbnailMapper.deleteByLectureId(lectureId);
    }
}
