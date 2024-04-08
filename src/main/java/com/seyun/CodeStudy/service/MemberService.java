package com.seyun.CodeStudy.service;

import com.seyun.CodeStudy.mapper.MemberMapper;
import com.seyun.CodeStudy.model.member.Auth;
import com.seyun.CodeStudy.model.member.CustomMember;
import com.seyun.CodeStudy.model.member.Member;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;
    private MemberMapper memberMapper;

    public int create(Member member){
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        int count = memberMapper.create(member);
        memberMapper.createAuth(new Auth(member.getId(), "ROLE_USER"));
        return count;
    }

    public Member findMemberById(String id){
        return memberMapper.findMemberById(id);
    }

    public void update(Member member){
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberMapper.update(member);
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = memberMapper.findMemberById(id);
        return member == null ? null : new CustomMember(member);
    }

}
