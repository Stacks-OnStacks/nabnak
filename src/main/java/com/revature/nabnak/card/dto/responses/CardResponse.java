package com.revature.nabnak.card.dto.responses;

import com.revature.nabnak.card.Card;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardResponse {

    private int cardId;
    private String description;
    private int points;
    private String tech;
    private String status;
    private String memberEmail;

    public CardResponse(Card card){
        this.cardId = card.getCardId();
        this.description = card.getDescription();
        this.points = card.getPoints();
        this.status = card.getStatus().toString();
        this.tech = card.getTech().toString();
        this.memberEmail = card.getMemberId().getEmail();
    }

}
