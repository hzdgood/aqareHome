package com.yunqi.common.mapper;

import com.yunqi.common.entity.Picture;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureMapper {
    List<Picture> select(Integer workId);
    void insert(Picture picture);
}
