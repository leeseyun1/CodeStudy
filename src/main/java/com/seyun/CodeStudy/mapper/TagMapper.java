package com.seyun.CodeStudy.mapper;

import com.seyun.CodeStudy.model.lecture.Tag;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagMapper {

    Tag findTagByTagContent(String tagContent);

    int createTag(String tagContent);

    int addTagCount(Long tagId);
}
