package com.revature.nabnak.card;

import com.revature.nabnak.card.dto.requests.NewCardRequest;
import com.revature.nabnak.card.dto.responses.CardResponse;
import com.revature.nabnak.member.Member;
import com.revature.nabnak.member.MemberService;
import com.revature.nabnak.util.exceptions.InvalidUserInputException;
import com.revature.nabnak.util.exceptions.ResourceNotFoundException;
import com.revature.nabnak.util.exceptions.UnauthorizedException;
import com.revature.nabnak.util.web.auth.DTO.response.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {

    private final MemberService memberService; // this now has it's own unique session
    private final CardRepository cardRepository;

    @Autowired
    public CardService(MemberService memberService, CardRepository cardRepository){ // what's this methodology?
        this.memberService = memberService;
        this.cardRepository = cardRepository;

    }

    //TODO: Implement validation
     public boolean isCardValid(Card newCard){
         return false;
     }

     public CardResponse addCard(NewCardRequest cardRequest) throws InvalidUserInputException{

        areEnumsValid(cardRequest);

        Card newCard = new Card(cardRequest);

        return new CardResponse(cardRepository.save(newCard));
     }

    public List<CardResponse> addMultiCard(List<NewCardRequest> cardRequests) throws InvalidUserInputException{

        cardRequests.forEach(cardRequest -> areEnumsValid(cardRequest));

        List<Card> newCards = cardRequests.stream().map(Card::new).collect(Collectors.toList());

        return ((Collection<Card>) cardRepository.saveAll(newCards)).stream().map(CardResponse::new).collect(Collectors.toList());
    }


     public List<CardResponse> findAllCards(){
        return ((Collection<Card>) cardRepository.findAll()).stream().map(CardResponse::new).collect(Collectors.toList());
     }

     public List<CardResponse> findAllCardsByMember(String memberId){

        return ((Collection<Card>) cardRepository.findAllByUser(memberId)).stream().map(CardResponse::new).collect(Collectors.toList());
     }

     public CardResponse findCardById(String id){
        return new CardResponse(cardRepository.findById(Integer.parseInt(id)).orElseThrow(ResourceNotFoundException::new));
     }

     public Card update(Card updatedCard){

        cardRepository.save(updatedCard);
        return updatedCard;
     }

     public boolean delete(String id, Principal principal){
        if(findCardById(id).getMemberEmail().equals(principal.getEmail())) {
            cardRepository.deleteById(Integer.parseInt(id));
            return true;
        } else {
            throw new UnauthorizedException("Email & Card memberEmail do not match");
        }
     }

     public boolean areEnumsValid(NewCardRequest cardRequest) throws InvalidUserInputException{
        List<String> techEnums = Arrays.asList("JAVA", "JAVASCRIPT", "PYTHON", "RUBY", "GO", "REACT", "TYPESCRIPT", "LUA", "CARBON");
        List<String> statusEnums = Arrays.asList("OPEN", "INPROGRESS", "CLOSED");

        List<Boolean> checkTechEnums = techEnums.stream().map(str -> str.equals(cardRequest.getTech().toUpperCase())).collect(Collectors.toList());
        if(!checkTechEnums.contains(true)){
            throw new InvalidUserInputException(
                    "Tech was not a valid entry please try the follow : " +
                    techEnums.stream().map(Object::toString).collect(Collectors.joining(",")) // this will produce all available tech enums
                    );
        }

         List<Boolean> checkStatusEnums = statusEnums.stream().map(str -> str.equals(cardRequest.getStatus().toUpperCase())).collect(Collectors.toList());
         if(!checkStatusEnums.contains(true)){
             throw  new InvalidUserInputException(
                     "Status was not a valid entry please try the follow : " +
                             statusEnums.stream().map(Object::toString).collect(Collectors.joining(",")) // this will produce all available tech enums
             );
         }
        return true;

     }

}
