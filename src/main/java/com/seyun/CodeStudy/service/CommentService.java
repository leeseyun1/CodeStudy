package com.seyun.CodeStudy.service;

import com.seyun.CodeStudy.common.CommentSearchDto;
import com.seyun.CodeStudy.common.Pagination;
import com.seyun.CodeStudy.common.Paging;
import com.seyun.CodeStudy.mapper.CommentMapper;
import com.seyun.CodeStudy.model.post.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;

    @Transactional
    public Long create(final Comment params){
        commentMapper.create(params);
        return params.getCommentId();
    }

    public Comment findByCommentId(final Long commentId){
        return commentMapper.findByCommentId(commentId);
    }

    @Transactional
    public Long update(final Comment params){
        commentMapper.update(params);
        return params.getCommentId();
    }

    @Transactional
    public Long deleteByCommentId(final Long commentId){
        commentMapper.deleteByCommentId(commentId);
        return commentId;
    }

    public Paging<Comment> findAllComment(CommentSearchDto params){
        int count = commentMapper.count(params);
        if(count < 1){
            return new Paging<>(Collections.emptyList(), null);
        }

        Pagination pagination = new Pagination(count, params);
        List<Comment> list = commentMapper.findAllComment(params);
        return new Paging<>(list, pagination);
    }

}
