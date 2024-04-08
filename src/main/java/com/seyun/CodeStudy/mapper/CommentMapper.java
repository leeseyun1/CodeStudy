package com.seyun.CodeStudy.mapper;

import com.seyun.CodeStudy.common.CommentSearchDto;
import com.seyun.CodeStudy.model.post.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CommentMapper {

    void create(Comment params);

    Comment findByCommentId(Long commentId);

    void update(Comment params);

    void deleteByCommentId(Long commentId);

    List<Comment> findAllComment(CommentSearchDto params);

    int count(CommentSearchDto params);


}
