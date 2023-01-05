package com.isicod.net.communitiesManagement.mapper;

import com.isicod.net.communitiesManagement.dto.CommunityDto;
import com.isicod.net.communitiesManagement.dto.ReclamationDto;
import com.isicod.net.communitiesManagement.models.Community;
import com.isicod.net.communitiesManagement.models.Reclamation;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommunityMapper {
    private Mapper mapper = new DozerBeanMapper();

    public Community CommunityDtoToCommunity(CommunityDto communityDto) {
        Community community= mapper.map(communityDto, Community.class);

        return community;
    }

    public List<Community> CommunityDtoToCommunity(List<CommunityDto> communityDtos) {
        List<Community> dtos = new ArrayList<>();
        communityDtos.forEach(doc -> dtos.add(CommunityDtoToCommunity(doc)));
        return dtos;
    }
}
