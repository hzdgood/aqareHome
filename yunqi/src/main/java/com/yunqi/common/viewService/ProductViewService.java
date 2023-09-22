package com.yunqi.common.viewService;

import com.yunqi.common.view.ProductView;
import com.yunqi.common.viewMapper.ProductViewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductViewService {
    private ProductViewMapper ProductViewMapper;

    @Autowired
    public void setMapper(ProductViewMapper ProductViewMapper) {
        this.ProductViewMapper = ProductViewMapper;
    }

    public List<ProductView> select(ProductView ProductView) {
        return ProductViewMapper.select(ProductView);
    }

    public List<ProductView> selectByWork(ProductView productView) {
        return ProductViewMapper.selectByWork(productView);
    }
}
