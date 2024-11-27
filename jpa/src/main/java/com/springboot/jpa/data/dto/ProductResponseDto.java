package com.springboot.jpa.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductResponseDto {
    @Schema(
            description = "상품 고유 번호",
            example = "1"
    )
    @NotNull
    private Long number;

    @Schema(
            description = "상품 이름",
            example = "갤럭시 노트북"
    )
    @NotBlank
    private String name;

    @Schema(
            description = "상품 가격 (원)",
            example = "1200000"
    )
    @NotNull
    private int price;

    @Schema(
            description = "재고 수량",
            example = "50"
    )
    @NotNull
    private int stock;

}
