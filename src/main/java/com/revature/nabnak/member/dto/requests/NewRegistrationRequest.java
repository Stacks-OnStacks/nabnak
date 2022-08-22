package com.revature.nabnak.member.dto.requests;

import java.util.UUID;

public class NewRegistrationRequest {

    private String id;

    private String fullName;

    private String email;

    private String password;

    private int experienceMonths;

    public NewRegistrationRequest() {
    }

    public NewRegistrationRequest(String fullName, String email, String password, int experienceMonths) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.experienceMonths = experienceMonths;
        this.id = UUID.randomUUID().toString();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getExperienceMonths() {
        return experienceMonths;
    }

    public void setExperienceMonths(int experienceMonths) {
        this.experienceMonths = experienceMonths;
    }

    @Override
    public String toString() {
        return "NewRegistractionRequest{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", experienceMonths='" + experienceMonths + '\'' +
                '}';
    }
}
