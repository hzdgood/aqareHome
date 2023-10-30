package com.yunqi.common.mapper;

import com.yunqi.common.entity.Picture;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureMapper {
    List<Picture> select();
    void insert(Picture picture);
}
