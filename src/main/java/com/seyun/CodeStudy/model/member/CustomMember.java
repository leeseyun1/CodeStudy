package com.seyun.CodeStudy.model.member;


import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.stream.Collectors;

@Getter
public class CustomMember extends User {

    private static final long serialVersionUID = 1L;
    private Member member;

    public CustomMember(Member member) {
        super(member.getId(), member.getPassword(), member.getAuthList().stream().map(auth ->
                new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
        this.member = member;
    }
}
