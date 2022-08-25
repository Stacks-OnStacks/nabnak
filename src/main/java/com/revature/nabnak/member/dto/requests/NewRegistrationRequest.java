package com.revature.nabnak.member.dto.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.UUID;

@Data
@NoArgsConstructor
public class NewRegistrationRequest {

    private String id;
    @NotBlank(message = "Hey you need to enter your full name please")
    @NotNull
    private String fullName;
    @Email(message = "Please provide valid email")
    private String email;

    @Pattern(message = "Minimum eight characters, at least one letter, one number and one special character",regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    private String password;
    @Min(value = 0)
    private int experienceMonths;

    public NewRegistrationRequest(String fullName, String email, String password, int experienceMonths) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.experienceMonths = experienceMonths;
        this.id = UUID.randomUUID().toString();
    }

}
