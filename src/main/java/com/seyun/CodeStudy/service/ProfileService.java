package com.seyun.CodeStudy.service;

import com.seyun.CodeStudy.mapper.ProfileMapper;
import com.seyun.CodeStudy.model.lecture.Thumbnail;
import com.seyun.CodeStudy.model.member.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileMapper profileMapper;

    @Transactional
    public void create(final String id, final Profile profile){
        profile.setId(id);
        profileMapper.create(profile);
    }

    @Transactional
    public void deleteByProfileId(final Long profileId){
        profileMapper.deleteByProfileId(profileId);
    }
}
