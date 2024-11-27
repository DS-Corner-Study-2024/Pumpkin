package com.springboot.jpa.data.dao.impl;

import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.data.repository.ProductRepository;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component  // 스프링 빈 등록
public class ProductDAOImpl implements ProductDAO {

    private ProductRepository productRepository;

    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product insertProduct(Product product) {
        Product savedProduct = productRepository.save(product); // JPA에서 save() 등 메서드 기본 제공

        return savedProduct;
    }

    @Override
    public Product selectProduct(Long number) {
        Product selectedProduct = productRepository.getById(number);
        // getById(): 프락시 객체 반환. 프락시 객체를 사용할 때 실제 쿼리 실행됨. 엔티티가 존재하지 않으면 EntityNotFoundException 발생.
        // findById(): Optional 타입 반환. 영속성 컨텍스트 캐시에 데이터가 없으면 실제 데이터베이스에서 데이터 조회. 엔티티가 존재하지 않으면 Optional.empty() 반환.

        return selectedProduct;
    }

    @Override
    public Product updateProductName(Long number, String name) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number); // 영속성 컨텍스트에 추가됨.

        Product updatedProduct;
        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();

            product.setName(name);
            product.setUpdatedAt(LocalDateTime.now());

            updatedProduct = productRepository.save(product);   // Dirty Check(변경 감지) 수행
        } else {
            throw new Exception();
        }

        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();

            productRepository.delete(product);
        } else {
            throw new Exception();
        }
    }
}