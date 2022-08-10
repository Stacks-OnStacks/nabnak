package com.revature.nabnak.card;

public class Card {
    // Attributes
    private int cardId;
    private String description;
    private int points;
    private String tech;
    private String status;
    private String memberEmail;

    public Card(int cardId, String description, int points, String tech, String status, String memberEmail) {
        this.cardId = cardId;
        this.description = description;
        this.points = points;
        this.tech = tech;
        this.status = status;
        this.memberEmail = memberEmail;
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
