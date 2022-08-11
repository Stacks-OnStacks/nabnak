package com.revature.nabnak.card;

import com.revature.nabnak.member.MemberService;

import java.util.List;

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

     public Card addCard(Card newCard){
        return cardDao.create(newCard);
     }

     public List<Card> findAllCards(){
        return cardDao.findAll();
     }

     public List<Card> findAllCardsByMember(String email){
        return cardDao.findAllByUser(email);
     }

     public Card update(Card updatedCard){
        cardDao.update(updatedCard);
        return updatedCard;
     }

     public boolean delete(String id){
        return cardDao.delete(id);
     }

}