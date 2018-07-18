package com.hex.base.converter;

import com.hex.base.domain.Product;
import com.hex.base.vo.ProductVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hexuan
 * Date: 2018/7/11
 * Time: 上午10:30
 */
public class Product2ProductVOConverter {
    public static ProductVO converter(Product product) {
        ProductVO productVO = new ProductVO();
        BeanUtils.copyProperties(product, productVO);
        productVO.setStateStr(product.stateStr().getMsg());
        return productVO;
    }

    public static List<ProductVO> converter(List<Product> productList) {
        List<ProductVO> productVOList = new ArrayList<>();
        for (Product product : productList) {
            productVOList.add(converter(product));
        }
        return productVOList;
    }
}
