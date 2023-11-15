package com.yunqi.common.viewMapper;

import com.yunqi.common.view.RollerView;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RollerViewMapper {
    List<RollerView> select(RollerView RollerView);
}
