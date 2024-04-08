package com.seyun.CodeStudy.model.member;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Profile {
    private Long profileId;
    private String id;

    private String uploadPath;
    private String originalName;
    private String saveName;
    private long size;

    private Boolean isDeleted;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
