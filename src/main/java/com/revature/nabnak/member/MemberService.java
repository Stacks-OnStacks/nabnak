package com.revature.nabnak.member;

import com.revature.nabnak.member.dto.requests.EditMemberRequest;
import com.revature.nabnak.member.dto.requests.NewRegistrationRequest;
import com.revature.nabnak.member.dto.response.MemberResponse;
import com.revature.nabnak.util.exceptions.InvalidUserInputException;
import com.revature.nabnak.util.exceptions.ResourcePersistanceException;
import com.revature.nabnak.util.exceptions.ResourceNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service // Any stereotypical annotation in spring (Component, Controller, Service, Repository) they all automatically add @Autowired to the Constructor (requires 1 constructor)
public class MemberService {
    // Attributes
    private final MemberRepository memberRepository;
    private Member sessionMember = null;
    private final Logger logger = LogManager.getLogger();

    // CONSTRUCTOR
    @Autowired // this is defaulty added due to the Service Above
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    // Methods

    @Transactional
    public MemberResponse registerMember(NewRegistrationRequest newRegistration) throws InvalidUserInputException, ResourcePersistanceException{

            Member newMember = new Member();

            newMember.setEmail(newRegistration.getEmail());
            newMember.setFullName(newRegistration.getFullName());
            newMember.setExperienceMonths(newRegistration.getExperienceMonths());
            newMember.setPassword(newRegistration.getPassword());
            newMember.setId(UUID.randomUUID().toString());
            // Java will set these for up
            newMember.setRegistrationDate(new Date(System.currentTimeMillis()));

            logger.info("Member registration service has begun with the provide: {}", newMember);
            if (!isMemberValid(newMember)) {
                throw new InvalidUserInputException("User input was invalid");
            }

            if(isEmailAvailable(newMember.getEmail())){
                throw new ResourcePersistanceException("Email is already registered please try logging in.");
            }

            newMember = memberRepository.save(newMember);

            return new MemberResponse(newMember);

    }
    // TODO: Comeback for custom Repo method
    @Transactional
    public Member login(String email, String password){
        Member member = memberRepository.loginCredentialCheck(email, password).orElseThrow(ResourceNotFoundException::new);
        sessionMember = member;
        return member;
    }

    // TODO: NEW READ ME (Lines 76 - 105)
    @Transactional(readOnly = true)
    public List<MemberResponse> readAll(){

        // Streams are a form of functional programming this is form a declarative programming
        List<MemberResponse> members = ((Collection<Member>) memberRepository.findAll()) // findAll now returns an Iterable we can cast an iterable to a Collection so we can stream it
                                                .stream()//this reads through each value inside of the collection (aka our List)
                                                //.map(member -> new MemberResponse(member))
                                                // this is leveraging (::) which is know as the method reference operator, it's taking the method from MemberReponse and applying to all objects in the stream
                                                .map(MemberResponse::new)
                                                .collect(Collectors.toList());

        return members;
    }

    @Transactional(readOnly = true)
    public MemberResponse findById(String email){

        // Optional
        Member member = memberRepository.findById(email).orElseThrow(ResourceNotFoundException::new);
        MemberResponse responseMember = new MemberResponse(member);
        return responseMember;
    }

    public boolean isMemberValid(Member newMember){
        if(newMember == null) return false;
        // this || is the expression to signify to the conditional that if either of these are true then perform the action
        if(newMember.getEmail() == null || newMember.getEmail().trim().equals("")) return false;
        if(newMember.getFullName() == null || newMember.getFullName().trim().equals("")) return false;
        if(newMember.getExperienceMonths() < 0 ) return false;
        if(newMember.getRegistrationDate() == null || newMember.getRegistrationDate().toString().trim().equals("")) return false;
        if(newMember.getPassword() == null || newMember.getPassword().trim().equals("")) return false;
        return true;
    }

    // TODO: IMPLEMENT MEEEEEEE!!!!!!!
    @Transactional(readOnly = true)
    public boolean isEmailAvailable(String email){
        boolean result = memberRepository.checkEmail(email).isPresent();
        logger.info("Value from is email Available returned: {}", result);
        return result;
    }

    @Transactional
    public boolean remove(String email){
        Member member = memberRepository.findById(email).orElseThrow(ResourceNotFoundException::new);
        memberRepository.delete(member);
        return true;
    }


    @Transactional
    public boolean update(EditMemberRequest editMember) throws InvalidUserInputException{

       Member foundMember = memberRepository.findById(editMember.getId()).orElseThrow(ResourceNotFoundException::new);

       // Predicate - to evaluate a true or false given a lambda expression
        // Lambda expression (arrow notation) - a syntax for a SINGULAR function
        Predicate<String> notNullOrEmpty = (str) -> str != null && !str.trim().equals("");

        // Example of Automatic Dirty Checking
       if(notNullOrEmpty.test(editMember.getFullName())){
           foundMember.setFullName(editMember.getFullName());
        }
       if(notNullOrEmpty.test(editMember.getPassword())){
               foundMember.setPassword(editMember.getPassword());
       }
       if(notNullOrEmpty.test(editMember.getEmail())){
           if(isEmailAvailable(editMember.getEmail())){
               throw new ResourcePersistanceException("The provided email is already registered");
           }
           foundMember.setEmail(editMember.getEmail());
       }

        return true;
    }

    public Member getSessionMember(){
        return sessionMember;
    }

}
