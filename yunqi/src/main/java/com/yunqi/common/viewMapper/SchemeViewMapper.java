package com.yunqi.common.viewMapper;

import com.yunqi.common.view.SchemeView;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SchemeViewMapper {
    List<SchemeView> select(SchemeView SchemeView);

    List<SchemeView> selectSum(SchemeView schemeView);
}
