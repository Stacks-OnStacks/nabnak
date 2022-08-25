package com.revature.nabnak.card.dto.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewCardRequest {

    private String description;
    private int points;
    private String tech;
    private String status;

    public NewCardRequest(String description, int points, String tech, String status) {
        this.description = description;
        this.points = points;
        this.tech = tech;
        this.status = status;
    }

}
