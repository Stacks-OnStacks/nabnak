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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Transactional
    public MemberResponse registerMember(NewRegistrationRequest newRegistration) throws InvalidUserInputException, ResourcePersistanceException{
            Member newMember = new Member(newRegistration);
            isEmailAvailable(newMember.getEmail());
            return new MemberResponse(memberRepository.save(newMember));
    }

    @Transactional
    public Member login(String email, String password){
        Member member = memberRepository.loginCredentialCheck(email, password).orElseThrow(ResourceNotFoundException::new);
        return member;
    }

    @Transactional(readOnly = true)
    public List<MemberResponse> readAll(){
        return  ((Collection<Member>) memberRepository  .findAll())
                                                        .stream()
                                                        .map(MemberResponse::new)
                                                        .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MemberResponse findById(String email){
        Member member = memberRepository.findById(email).orElseThrow(ResourceNotFoundException::new);
        MemberResponse responseMember = new MemberResponse(member);
        return responseMember;
    }

    @Transactional(readOnly = true)
    public void isEmailAvailable(String email){
        if(memberRepository.checkEmail(email).isPresent()) throw new InvalidUserInputException("Email: " + email + " is already registered please try again. Or Log in with email & password.");
    }

    @Transactional
    public void remove(String email){
        memberRepository.deleteById(email);
    }

    @Transactional
    public void update(EditMemberRequest editMember) throws InvalidUserInputException{
       Member foundMember = memberRepository.findById(editMember.getId()).orElseThrow(ResourceNotFoundException::new);
       Predicate<String> notNullOrEmpty = (str) -> str != null && !str.trim().equals("");

       if(notNullOrEmpty.test(editMember.getFullName())) foundMember.setFullName(editMember.getFullName());

       if(notNullOrEmpty.test(editMember.getPassword())) foundMember.setPassword(editMember.getPassword());

       if(notNullOrEmpty.test(editMember.getEmail())){
           isEmailAvailable(editMember.getEmail());
           foundMember.setEmail(editMember.getEmail());
       }
    }

}
