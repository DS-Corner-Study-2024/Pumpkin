package com.springboot.advanced_jpa.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChangeProductNameDto {

    @Schema(
            description = "상품 번호",
            example = "1"
    )
    @NotNull(message = "상품 번호는 필수 입력 값입니다.")
    private Long number;

    @Schema(
            description = "새로운 상품 이름",
            example = "갤럭시 노트북 프로"
    )
    @NotBlank(message = "새로운 상품 이름은 필수 입력 값입니다.")
    private String name;

    public ChangeProductNameDto(Long number, String name) {
        this.number = number;
        this.name = name;
    }

    public ChangeProductNameDto() {
    }

    public Long getNumber() {
        return this.number;
    }

    public String getName() {
        return this.name;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

}
