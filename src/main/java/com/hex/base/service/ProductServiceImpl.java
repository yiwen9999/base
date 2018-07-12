package com.hex.base.service;

import com.hex.base.domain.Product;
import com.hex.base.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * User: hexuan
 * Date: 2018/7/10
 * Time: 下午2:34
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Integer id) {
        return productRepository.findOne(id);
    }

    @Override
    public Page<Product> findAllProductList(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public void deleteProductById(Integer id) {
        productRepository.delete(id);
    }
}
