package com.revature.nabnak.card;

import com.revature.nabnak.card.dto.requests.NewCardRequest;
import com.revature.nabnak.card.dto.responses.CardResponse;
import com.revature.nabnak.util.exceptions.InvalidUserInputException;
import com.revature.nabnak.util.exceptions.ResourceNotFoundException;
import com.revature.nabnak.util.exceptions.ResourcePersistanceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;
    private final Logger logger = LogManager.getLogger();

    @Autowired
    public CardController(CardService cardService){
        this.cardService = cardService;
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
    public CardResponse create(@RequestBody NewCardRequest newCardRequest){
        return cardService.addCard(newCardRequest);
    }



}
