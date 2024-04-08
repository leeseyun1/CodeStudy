package com.seyun.CodeStudy.controller;

import com.seyun.CodeStudy.common.CommentSearchDto;
import com.seyun.CodeStudy.common.Paging;
import com.seyun.CodeStudy.model.post.Comment;
import com.seyun.CodeStudy.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //수정해야함 ** uri
    //create
    @PostMapping("/comment/save/{postId}")
    public Comment create(@PathVariable final Long postId, @RequestBody final Comment params){
        Long commentId = commentService.create(params);
        return commentService.findByCommentId(commentId);
    }

    //list read
    @GetMapping("/comment/list/{postId}")
    public Paging<Comment> findAllComment(@PathVariable final Long postId, final CommentSearchDto params){
        return commentService.findAllComment(params);
    }

    //one comment read
    @GetMapping("/comment/one/{commentId}")
    public Comment findByCommentId(@PathVariable final Long commentId){
        return commentService.findByCommentId(commentId);
    }

    //update
    @PatchMapping("/comment/update/{commentId}")
    public Comment update(@PathVariable final Long commentId, @RequestBody final Comment params){
        commentService.update(params);
        return commentService.findByCommentId(commentId);
    }

    //delete
    @DeleteMapping("/comment/delete/{commentId}")
    public Long deleteByCommentId(@PathVariable final Long commentId){
        return commentService.deleteByCommentId(commentId);
    }


}
