package com.seyun.CodeStudy.model.lecture;

import com.seyun.CodeStudy.model.member.Profile;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Lecture {

    private Long lectureId;
    private String writerId;
    private String writerName;
    private String title;
    private String intro;
    private Boolean isDeleted;
    private int viewCnt;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private LocalDateTime uploadDate;

    private Thumbnail thumbnail;
    private List<Tag> tagList;

    private Profile profile;
}
