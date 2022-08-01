package com.revature.nabnak.services;

import com.revature.nabnak.daos.CardDao;
import com.revature.nabnak.models.Card;

public class CardService {

    private final MemberService memberService; // this now has it's own unique session
    private final CardDao cardDao;

    public CardService(MemberService memberService, CardDao cardDao){ // what's this methodology?
        this.memberService = memberService;
        this.cardDao = cardDao;

    }

    //TODO: Implement
     public boolean isCardValid(Card newCard){
         return false;
     }


}
