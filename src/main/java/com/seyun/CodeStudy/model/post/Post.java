package com.seyun.CodeStudy.model.post;

import com.seyun.CodeStudy.model.member.Profile;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Post {

    private Long postId;
    private String title;
    private String content;
    private String writerId;
    private String writerName;
    private int viewCnt;
    private Boolean isDeleted;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private Profile profile;

}
