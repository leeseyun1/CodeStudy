package com.seyun.CodeStudy.mapper;

import com.seyun.CodeStudy.common.SearchDto;
import com.seyun.CodeStudy.model.post.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    void create(Post params);

    Post findByPostId(Long postId);

    void update(Post params);

    void deleteByPostId(Long postId);

    List<Post> findAllPost(SearchDto params);

    int count(SearchDto params);

    void viewCntUp(Long postId);

    Long[] findAllPostId();
}
