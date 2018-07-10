package com.hex.base.repository;

import com.hex.base.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: hexuan
 * Date: 2018/7/10
 * Time: 下午2:30
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
