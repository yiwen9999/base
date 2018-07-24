package com.hex.base.service;

import com.hex.base.domain.Product;
import com.hex.base.dto.ProductCondition;
import com.hex.base.enums.ObjectStateEnum;
import com.hex.base.repository.MySpec;
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

    @Override
    public Product updateProductStateById(Integer id) {
        Product product = productRepository.findOne(id);
        if (null != product) {
            if (product.getState() == ObjectStateEnum.BLOCK_UP.getCode()) {
                product.setState(ObjectStateEnum.START_USING.getCode());
            } else {
                product.setState(ObjectStateEnum.BLOCK_UP.getCode());
            }
            productRepository.save(product);
        }
        return product;
    }

    @Override
    public Page<Product> findProductListByCondition(ProductCondition productCondition, Pageable pageable) {
        return productRepository.findAll(MySpec.findProducts(productCondition), pageable);
    }
}
