package com.aqara.common.service;

import com.aqara.common.entity.PageReq;
import com.aqara.common.entity.SpeedyContent;
import com.aqara.common.mapper.SpeedyContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeedyContentService {
    private SpeedyContentMapper SpeedyContentMapper;

    @Autowired
    public void setMapper(SpeedyContentMapper SpeedyContentMapper) {
        this.SpeedyContentMapper = SpeedyContentMapper;
    }

    public List<SpeedyContent> select(PageReq PageReq) {
        if (PageReq.getThemeId() == null || PageReq.getThemeId().equals("null")) {
            return SpeedyContentMapper.select(null, PageReq.getTeam(), PageReq.getTheme(), PageReq.getType(), PageReq.getPerson());
        } else {
            return SpeedyContentMapper.select(Integer.parseInt(PageReq.getThemeId()), PageReq.getTeam(), PageReq.getTheme(), PageReq.getType(), PageReq.getPerson());
        }
    }

    public void insert(SpeedyContent SpeedyContent) {
        SpeedyContentMapper.insert(SpeedyContent);
    }

    public void update(SpeedyContent SpeedyContent) {
        SpeedyContentMapper.update(SpeedyContent);
    }

    public void delete(List<Integer> ids) {
        SpeedyContentMapper.delete(ids);
    }
}
