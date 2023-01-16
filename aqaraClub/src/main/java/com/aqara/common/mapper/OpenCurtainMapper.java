package com.aqara.common.mapper;

import com.aqara.common.entity.OpenCurtain;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpenCurtainMapper {

    List<OpenCurtain> currentData();

    List<OpenCurtain> currentData1();

    void insert(OpenCurtain OpenCurtain);

    void upload(OpenCurtain openCurtain);

    void delete(Integer id);
}
