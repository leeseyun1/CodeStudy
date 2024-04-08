package com.seyun.CodeStudy.service;

import com.seyun.CodeStudy.mapper.TagMapMapper;
import com.seyun.CodeStudy.mapper.TagMapper;
import com.seyun.CodeStudy.model.lecture.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagMapper tagMapper;
    private final TagMapMapper tagMapMapper;

    public void saveTag(String[] tagContents, Long lectureId){
        for(String tagContent : tagContents){
            Tag findTag = tagMapper.findTagByTagContent(tagContent);

            if(findTag == null){
                tagMapper.createTag(tagContent);
            }
            Tag findTag2 = tagMapper.findTagByTagContent(tagContent);
            tagMapper.addTagCount(findTag2.getTagId());
            tagMapMapper.createTagMap(findTag2.getTagId(), lectureId);
        }
    }

    public void deleteTagMap(Long lectureId){
        tagMapMapper.deleteTagMap(lectureId);
    }
}
