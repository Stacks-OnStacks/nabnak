package com.revature.nabnak.card.dto.requests;

import com.revature.nabnak.member.Member;

public class EditCardRequest {

    private int cardId;
    private String description;
    private int points;
    private String tech;
    private String status;
    private Member member;

    public EditCardRequest(String description, int points, String tech, String status) {
        this.description = description;
        this.points = points;
        this.tech = tech;
        this.status = status;
    }
}
