package com.seyun.CodeStudy.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagMapMapper {
    int createTagMap(Long tagId, Long lectureId);

    void deleteTagMap(Long lectureId);
}
