package com.revature.nabnak.card;

import com.revature.nabnak.card.dto.requests.NewCardRequest;
import com.revature.nabnak.card.dto.responses.CardResponse;
import com.revature.nabnak.member.Member;
import com.revature.nabnak.util.web.Secured;
import com.revature.nabnak.util.web.auth.DTO.response.Principal;
import com.revature.nabnak.util.web.auth.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;
    private final TokenService tokenService;

    @Autowired
    public CardController(CardService cardService, TokenService tokenService){
        this.cardService = cardService;
        this.tokenService = tokenService;
    }

    @GetMapping
    public List<CardResponse> findAll(){
        return cardService.findAllCards();
    }

    @GetMapping("/{id}")
    public CardResponse findById(@PathVariable  String id){
        return cardService.findCardById(id);
    }

    @GetMapping("/member/{id}")
    public List<CardResponse> findByMemberId(@PathVariable  String id){
        return cardService.findAllCardsByMember(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Secured
    public CardResponse create(@RequestBody NewCardRequest newCardRequest, @RequestHeader(name="Authorization") String token){
        Principal requester = tokenService.extractTokenDetails(token);
        newCardRequest.setMember(new Member(requester.getId(), requester.getEmail(), requester.isAdmin()));
        return cardService.addCard(newCardRequest);
    }



}
