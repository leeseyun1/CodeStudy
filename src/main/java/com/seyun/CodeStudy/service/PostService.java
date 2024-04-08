package com.seyun.CodeStudy.service;

import com.seyun.CodeStudy.common.Pagination;
import com.seyun.CodeStudy.common.Paging;
import com.seyun.CodeStudy.common.SearchDto;
import com.seyun.CodeStudy.mapper.PostMapper;
import com.seyun.CodeStudy.model.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostMapper postMapper;

    @Transactional
    public Long create(final Post params){
        postMapper.create(params);
        return params.getPostId();
    }

    public Post findByPostId(final Long postId){
        postMapper.viewCntUp(postId);
        return postMapper.findByPostId(postId);
    }

    @Transactional
    public Long update(final Post params){
        postMapper.update(params);
        return params.getPostId();
    }

    public Long delete(final Long postId){
        postMapper.deleteByPostId(postId);
        return postId;
    }

    public Paging<Post> findAllPost(final SearchDto params){

        int count = postMapper.count(params);
        if(count < 1){
            return new Paging<>(Collections.emptyList(), null);
        }

        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        List<Post> list = postMapper.findAllPost(params);
        return new Paging<>(list, pagination);
    }

    public Long[] findAllPostId(){
        return postMapper.findAllPostId();
    }
}
