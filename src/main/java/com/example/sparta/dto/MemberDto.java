package com.example.sparta.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MemberDto {
    @NotBlank(message = "이름은 필수입니다.")
    @Size(min = 2, message = "이름은 2글자 이상이어야 합니다.")
    private String name;

    @Min(value = 18, message = "18세 미만은 가입할 수 없습니다.")
    private int age;
}
