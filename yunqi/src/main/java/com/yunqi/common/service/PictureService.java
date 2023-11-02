package com.yunqi.common.service;

import com.yunqi.common.entity.Picture;
import com.yunqi.common.mapper.PictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureService {
    private PictureMapper PictureMapper;

    @Autowired
    public void setMapper(PictureMapper PictureMapper) {
        this.PictureMapper = PictureMapper;
    }
    public List<Picture> select(Integer workId) {
        return PictureMapper.select(workId);
    }
    public void insert(Picture picture) {
        PictureMapper.insert(picture);
    }
}
