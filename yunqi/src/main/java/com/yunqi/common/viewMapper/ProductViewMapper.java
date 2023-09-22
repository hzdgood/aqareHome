package com.yunqi.common.viewMapper;

import com.yunqi.common.view.ProductView;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductViewMapper {
    List<ProductView> select(ProductView ProductView);

    List<ProductView> selectByWork(ProductView productView);
}
