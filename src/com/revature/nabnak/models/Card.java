package com.revature.nabnak.models;

public class Card {
    // Attributes
    private String cardId;
    private String description;
    private int points;
    private String tech;
    private int status;
    private String memberEmail;

    public Card(String cardId, String description, int points, String tech, int status, String memberEmail) {
        this.cardId = cardId;
        this.description = description;
        this.points = points;
        this.tech = tech;
        this.status = status;
        this.memberEmail = memberEmail;
    }

    public Card() {
    }


    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId='" + cardId + '\'' +
                ", description='" + description + '\'' +
                ", points=" + points +
                ", tech='" + tech + '\'' +
                ", status=" + status +
                ", memberEmail='" + memberEmail + '\'' +
                '}';
    }
}
