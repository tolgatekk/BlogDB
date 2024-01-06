package com.blog.dto.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDto {
    @Email(message = "Email adresini kontrol edin")
    private String email;
    private String name;
    private String surname;
    private String password;
    private String rePassword;

}
