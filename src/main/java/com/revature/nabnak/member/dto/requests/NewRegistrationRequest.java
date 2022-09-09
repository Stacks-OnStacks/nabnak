package com.revature.nabnak.member.dto.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.UUID;

@Data
@NoArgsConstructor
public class NewRegistrationRequest {

    private String id;

    @NotNull
    @NotBlank
    private String fullName;
    @Email(message = "Please provide valid email")

    @NotNull
    @NotBlank
    private String email;

    @Pattern(message = "Minimum eight characters, at least one letter, one number and one special character",regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    private String password;
    @Min(value = 0)
    private int experienceMonths;
    private String avatar;

    public NewRegistrationRequest(String fullName, String email, String password, int experienceMonths, String avatar) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.experienceMonths = experienceMonths;
        this.id = UUID.randomUUID().toString();
        this.avatar = avatar;
    }

}
