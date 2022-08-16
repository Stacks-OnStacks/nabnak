package com.revature.nabnak.card;

import com.revature.nabnak.card.dto.requests.NewCardRequest;
import com.revature.nabnak.card.dto.responses.CardResponse;
import com.revature.nabnak.member.MemberService;

import java.util.List;
import java.util.stream.Collectors;

public class CardService {

    private final MemberService memberService; // this now has it's own unique session
    private final CardDao cardDao;

    public CardService(MemberService memberService, CardDao cardDao){ // what's this methodology?
        this.memberService = memberService;
        this.cardDao = cardDao;

    }

    //TODO: Implement validation
     public boolean isCardValid(Card newCard){
         return false;
     }

     public CardResponse addCard(NewCardRequest cardRequest){

        Card newCard = new Card(cardRequest, memberService.getSessionMember());

        return new CardResponse(cardDao.create(newCard));
     }

     public List<CardResponse> findAllCards(){
        return cardDao.findAll().stream().map(CardResponse::new).collect(Collectors.toList());
     }

     public List<Card> findAllCardsByMember(String email){
        return cardDao.findAllByUser(email);
     }

     public Card findCardById(String id){
        return cardDao.findById(id);
     }

     public Card update(Card updatedCard){
        cardDao.update(updatedCard);
        return updatedCard;
     }

     public boolean delete(String id){
        return cardDao.delete(id);
     }

}
