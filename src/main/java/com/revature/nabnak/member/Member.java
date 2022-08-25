package com.revature.nabnak.member;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;


@Data // this handles toSTring, hashCode, equals() & getters & setters
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="members")
public class Member {
    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name="full_name")
    private String fullName;

    @Column(name="experience_months")
    private int experienceMonths;

    @Column(name = "registration_date")
    private Date registrationDate;

    @JsonAlias(value = {"Password", "pass", "theThing"})
    private String password;

    public Member(String email, String fullName, int experienceMonths, Date registrationDate, String password) {
        this.email = email;
        this.fullName = fullName;
        this.experienceMonths = experienceMonths;
        this.registrationDate = registrationDate;
        this.password = password;
    }
}
