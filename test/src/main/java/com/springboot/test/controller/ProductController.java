package com.springboot.test.controller;

import com.springboot.test.data.dto.ChangeProductNameDto;
import com.springboot.test.data.dto.ProductDto;
import com.springboot.test.data.dto.ProductResponseDto;
import com.springboot.test.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/product")
@Tag(name = "Product", description = "상품 관련 API")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(
            summary = "상품 조회",
            description = "상품 번호로 단일 상품을 조회합니다."
    )
    @GetMapping()
    public ResponseEntity<ProductResponseDto> getProduct(
            @Parameter(description = "조회할 상품 번호", required = true)
            @RequestParam Long number) {
        ProductResponseDto productResponseDto = productService.getProduct(number);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @Operation(
            summary = "상품 생성",
            description = "새로운 상품을 생성합니다."
    )
    @PostMapping()
    public ResponseEntity<ProductResponseDto> createProduct(
            @Parameter(description = "생성할 상품 정보", required = true)
            @RequestBody ProductDto productDto) {
        ProductResponseDto productResponseDto = productService.saveProduct(productDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDto);
    }

    @Operation(
            summary = "상품명 변경",
            description = "특정 상품의 이름을 수정합니다. 상품 번호와 새로운 이름이 필요합니다."
    )
    @PutMapping()
    public ResponseEntity<ProductResponseDto> changeProductName(
            @Parameter(description = "상품명 변경 정보", required = true)
            @RequestBody ChangeProductNameDto changeProductNameDto) throws Exception {
        ProductResponseDto productResponseDto = productService.changeProductName(
                changeProductNameDto.getNumber(),
                changeProductNameDto.getName());

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);

    }

    @Operation(
            summary = "상품 삭제",
            description = "특정 상품 번호를 기반으로 상품을 완전히 삭제합니다."
    )
    @DeleteMapping()
    public ResponseEntity<String> deleteProduct(
            @Parameter(description = "삭제할 상품 번호", required = true)
            @RequestParam Long number) throws Exception {
        productService.deleteProduct(number);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("정상적으로 삭제되었습니다.");
    }

}
