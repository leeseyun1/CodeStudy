package com.seyun.CodeStudy.mapper;

import com.seyun.CodeStudy.model.lecture.Thumbnail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ThumbnailMapper {

    void create(Thumbnail thumbnail);

    void deleteByThumbnailId(Long thumbnailId);

    void deleteByLectureId(Long lectureId);
}
