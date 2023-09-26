package com.yunqi.common.viewMapper;

import com.yunqi.common.view.DetailView;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailViewMapper {

    List<DetailView> select(DetailView DetailView);
}
