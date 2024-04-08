package com.seyun.CodeStudy.model.member;

import com.seyun.CodeStudy.model.member.Auth;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Member {

    private String id;
    private String password;
    private String name;
    private Gender gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private Boolean isDeleted;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private List<Auth> authList;
    private Profile profile;

    public enum Gender {
        M, F
    }
}
