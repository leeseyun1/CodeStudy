package com.seyun.CodeStudy.mapper;

import com.seyun.CodeStudy.model.member.Profile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProfileMapper {

    void create(Profile profile);

    void deleteByProfileId(Long profileId);
}
