package com.seyun.CodeStudy.model.post;

import com.seyun.CodeStudy.model.member.Profile;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class Comment {

    private Long commentId;
    private Long postId;
    private String writerId;
    private String writerName;
    private String content;
    private Boolean isDeleted;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Profile profile;

}
