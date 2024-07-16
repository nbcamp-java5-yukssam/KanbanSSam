package com.sparta.kanbanssam.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserSignUpRequestDto {

    @Email(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$",
            message = "이메일 형식에 맞지 않습니다.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "사용자 아이디를 입력해 주세요.")
    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "알파벳 소문자와 숫자로 구성된 4자 이상, 10자 이하여야 합니다.")
    private String accountId;

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$",
            message = "최소 8자 이상, 15자 이하의 알파벳 대소문자, 숫자, 특수문자로 구성되어야 합니다.")
    private String password;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;
}
