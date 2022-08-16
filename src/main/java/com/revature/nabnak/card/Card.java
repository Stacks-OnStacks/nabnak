package com.revature.nabnak.card;

import com.revature.nabnak.card.dto.requests.NewCardRequest;
import com.revature.nabnak.member.Member;

import javax.persistence.*;

@Entity
@Table(name="card")
public class Card {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private int cardId;
    private String description;
    private int points;
    private String tech;
    private String status;

    @ManyToOne
    @JoinColumn(name="member_id", nullable = false)
    private Member memberId;

    public Card(String description, int points, String tech, String status, Member memberId) {
        this.description = description;
        this.points = points;
        this.tech = tech;
        this.status = status;
        this.memberId = memberId;
    }

    public Card(int cardId, String description, int points, String tech, String status, Member memberId) {
        this.cardId = cardId;
        this.description = description;
        this.points = points;
        this.tech = tech;
        this.status = status;
        this.memberId = memberId;
    }

    public Card(NewCardRequest cardRequest, Member member) {
        this.description = cardRequest.getDescription();
        this.points = cardRequest.getPoints();
        this.tech = cardRequest.getTech();
        this.status = cardRequest.getStatus();
        this.memberId = member;
    }

    public Card() {
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Member getMemberId() {
        return memberId;
    }

    public void setMemberId(Member memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", description='" + description + '\'' +
                ", points=" + points +
                ", tech='" + tech + '\'' +
                ", status='" + status + '\'' +
                ", memberEmail=" + memberId.getEmail() +
                '}';
    }
}
