package com.revature.nabnak.util.web.auth.DTO.response;

import com.revature.nabnak.member.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class Principal {

    @NotBlank
    private String id;

    @NotBlank
    private String email;

    private boolean isAdmin;

    private String avatar;

    public Principal(Member authMember) {
        this.id = authMember.getId();
        this.email = authMember.getEmail();
        this.isAdmin = authMember.isAdmin();
        this.avatar = authMember.getAvatar();
    }

    public Principal(String id, String username, boolean isAdmin, String avatar) {
        this.id = id;
        this.email = username;
        this.isAdmin = isAdmin;
        this.avatar = avatar;
    }

}