package com.yunqi.common.viewService;

import com.yunqi.common.view.DetailView;
import com.yunqi.common.viewMapper.DetailViewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailViewService {

    private DetailViewMapper DetailViewMapper;

    @Autowired
    public void setMapper(DetailViewMapper DetailViewMapper) {
        this.DetailViewMapper = DetailViewMapper;
    }

    public List<DetailView> select(DetailView DetailView) {
        return DetailViewMapper.select(DetailView);
    }
}
