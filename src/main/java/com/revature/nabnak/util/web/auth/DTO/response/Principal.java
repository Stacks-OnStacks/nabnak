package com.revature.nabnak.util.web.auth.DTO.response;

import com.revature.nabnak.member.Member;

import javax.validation.constraints.NotBlank;

public class Principal {

    @NotBlank
    private String id;

    @NotBlank
    private String email;

    private boolean isAdmin;

    public Principal() {
        super();
    }

    public Principal(Member authMember) {
        this.id = authMember.getId();
        this.email = authMember.getEmail();
        this.isAdmin = authMember.isAdmin();
    }

    public Principal(String id, String username, boolean isAdmin) {
        this.id = id;
        this.email = username;
        this.isAdmin = isAdmin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        this.isAdmin = admin;
    }

    public Member extractUser() {
        return new Member(id, email, isAdmin);
    }

    @Override
    public String toString() {
        return "PrincipalResponse{" +
                "id='" + id + '\'' +
                ", username='" + email + '\'' +
                ", role='" + isAdmin + '\'' +
                '}';
    }

}