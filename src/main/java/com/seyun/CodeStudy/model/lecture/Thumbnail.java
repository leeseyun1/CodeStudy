package com.seyun.CodeStudy.model.lecture;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Thumbnail {

    private Long thumbnailId;
    private Long lectureId;

    private String uploadPath;
    private String originalName;
    private String saveName;
    private long size;

    private Boolean isDeleted;
    private LocalDateTime createdDate;
    private LocalDateTime deletedDate;

}
