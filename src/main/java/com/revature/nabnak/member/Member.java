package com.revature.nabnak.member;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.revature.nabnak.card.Card;
import com.revature.nabnak.member.dto.requests.NewRegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Data // this handles toString, hashCode, equals() & getters & setters
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

    @Column(columnDefinition = "boolean default false")
    private boolean isAdmin = false;

    @OneToMany(mappedBy = "memberId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Transient
    private Set<Card> cards;

    private String avatar;

    public Member(NewRegistrationRequest newRegistration) {
        this.email = newRegistration.getEmail();
        this.fullName = newRegistration.getFullName();
        this.experienceMonths = newRegistration.getExperienceMonths();
        this.password = newRegistration.getPassword();
        this.id = UUID.randomUUID().toString();
        this.registrationDate = new Date(System.currentTimeMillis());
        this.avatar = newRegistration.getAvatar();
    }

    public Member(String id, String email, boolean isAdmin, String avatar) {
        this.id = id;
        this.email = email;
        this.isAdmin = isAdmin;
        this.avatar = avatar;
    }
}
