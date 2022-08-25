package com.revature.nabnak.card;

import com.revature.nabnak.card.dto.requests.NewCardRequest;
import com.revature.nabnak.member.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @Enumerated(EnumType.STRING)
    private Tech tech; // Java, java, JaVA, JavA

    @Enumerated(EnumType.STRING)
    private Status status; // Open, OPEN, open

    @ManyToOne
    @JoinColumn(name="member_id", nullable = false)
    private Member memberId;

    public Card(NewCardRequest cardRequest, Member member) {
        this.description = cardRequest.getDescription();
        this.points = cardRequest.getPoints();
        this.tech = Tech.valueOf(cardRequest.getTech().toUpperCase());
        this.status = Status.valueOf(cardRequest.getStatus().toUpperCase());
        this.memberId = member;
    }

    public enum Tech{
        JAVA, JAVASCRIPT, PYTHON, RUBY, GO, REACT, TYPESCRIPT, LUA, CARBON
    }

    public enum Status{
        OPEN, INPROGRESS, CLOSED
    }

}
