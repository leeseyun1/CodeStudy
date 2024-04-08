package com.seyun.CodeStudy.mapper;

import com.seyun.CodeStudy.model.member.Auth;
import com.seyun.CodeStudy.model.member.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    int create(Member member);

    int createAuth(Auth auth);

    Member findMemberById(String id);

    void update(Member member);
}
