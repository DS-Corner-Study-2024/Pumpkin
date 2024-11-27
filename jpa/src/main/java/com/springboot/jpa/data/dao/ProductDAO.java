package com.springboot.jpa.data.dao;

import com.springboot.jpa.data.entity.Product;

// DAO(Data Access Object): 데이터베이스에 접근하기 위한 로직 관리.
public interface ProductDAO {

    Product insertProduct(Product product);

    Product selectProduct(Long number);

    Product updateProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;

}
