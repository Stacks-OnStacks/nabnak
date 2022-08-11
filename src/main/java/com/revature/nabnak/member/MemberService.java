package com.revature.nabnak.member;

import com.revature.nabnak.util.exceptions.InvalidUserInputException;
import com.revature.nabnak.util.exceptions.ResourcePersistanceException;

import java.util.List;

public class MemberService {
    // Attributes

    private final MemberDao memberDao;
    private Member sessionMember = null;

    // CONSTRUCTOR
    public MemberService(MemberDao memberDao){
        this.memberDao = memberDao;
    }
    // Methods
    public Member registerMember(Member newMember) {
        try {

            if (!isMemberValid(newMember)) {
                throw new InvalidUserInputException("User input was invalid");
            }

            if(!isEmailAvailable(newMember.getEmail())){
                throw new ResourcePersistanceException("Email is already registered please try logging in.");
            }

            memberDao.create(newMember);

            return newMember;

        } catch (InvalidUserInputException | ResourcePersistanceException e) {
            // TODO: NEW READ ME (Lines 38-41)
            return null;
        } catch (RuntimeException e){
            return null;
        } catch (Exception e) {
            return null;
        }
    }
    // TODO: NEW READ ME (Lines 43-73)
    public Member login(String email, String password){
        Member member = memberDao.loginCredentialCheck(email, password);
        sessionMember = member;
        return member;
    }

    // TODO: NEW READ ME (Lines 76 - 105)
    public List<Member> readAll(){
        List<Member> members = memberDao.findAll();
        return members;
    }

    public Member findById(String email){
        return memberDao.findById(email);
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
    public boolean isEmailAvailable(String email){
        List<Member> members = readAll();
        for(int i = 0; i < members.size(); i++){
            if(members.get(i) == null) break;
            if(members.get(i).getEmail().equals(email)){
                return false;
            }
        }
        return true;
    }

    public Member getSessionMember(){
        return sessionMember;
    }

    public void logout(){
        sessionMember = null;
    }

    public boolean isSessionActive(){
        return sessionMember != null;
    }

}
