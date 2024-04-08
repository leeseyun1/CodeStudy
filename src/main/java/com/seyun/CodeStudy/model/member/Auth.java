package com.seyun.CodeStudy.model.member;

import lombok.Data;

@Data
public class Auth {
    private String id;
    private String auth;

    public Auth(String id, String auth){
        this.id = id;
        this.auth = auth;
    }
}
