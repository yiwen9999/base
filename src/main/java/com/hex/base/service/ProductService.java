package com.hex.base.service;

import com.hex.base.domain.Product;
import com.hex.base.dto.ProductCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * User: hexuan
 * Date: 2018/7/10
 * Time: 下午2:32
 */
public interface ProductService {
    Product saveProduct(Product product);

    Product findProductById(Integer id);

    Page<Product> findAllProductList(Pageable pageable);

    void deleteProductById(Integer id);

    Product updateProductStateById(Integer id);

    Page<Product> findProductListByCondition(ProductCondition productCondition, Pageable pageable);
}
