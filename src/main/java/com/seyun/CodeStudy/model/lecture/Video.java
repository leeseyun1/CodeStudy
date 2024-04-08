package com.seyun.CodeStudy.model.lecture;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Video {
    private Long videoId;
    private Long lectureId;
    private String title;

    private String uploadPath;
    private String originalName;
    private String saveName;
    private long size;

    private Boolean isDeleted;
    private LocalDateTime createdDate;
    private LocalDateTime deletedDate;
}
