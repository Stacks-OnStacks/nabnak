package com.revature.nabnak.services;

import com.revature.nabnak.member.MemberRepository;
import com.revature.nabnak.member.Member;
import com.revature.nabnak.member.MemberService;
import com.revature.nabnak.member.dto.requests.NewRegistrationRequest;
import com.revature.nabnak.member.dto.response.MemberResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.mockito.Mockito.*;

public class MemberServiceTestSuite {

    MemberService sut ;
    //MemberDao memberDao;
    MemberRepository mockMemberRepository;

    @BeforeEach // this goes ahead and re-creates our sut every single test, so we are working with a fresh instance
    public void testPrep(){
       // memberDao = new MemberDao(); // for now this is "okay", but badd in long run, cannot repeatedly test for creation
        mockMemberRepository = mock(MemberRepository.class); // mocktail of the MemberDao Class not an actual instance
        sut = new MemberService(mockMemberRepository);
    }

    @Test
    public void test_isMemberValid_returnTrue_givenValidMember(){
        // 3 A's

        // Arrange - setting up the information and resources you need for the test
        Member validMember = new Member("valid", "valid", 12, new Date(2022,8,05), "valid");

        // Act - performing the actual action on the unit your testing (invoking a method)
        boolean actualResult = sut.isMemberValid(validMember);

        // Assert - evaluated if the actual result is as expected
        Assertions.assertTrue(actualResult);
    }

    @Test
    public void test_isMemberValid_returnFalse_givenInvalidInput(){
        // Arrange some invalid input
        Member invalidMember1 = null;
        Member invalidMember2 = new Member("", "valid", 12, new Date(2022,8,05), "valid");
        Member invalidMember3 = new Member("  ", "valid", 12, new Date(2022,8,05), "valid");
        Member invalidMember4 = new Member(null, "valid", 12, new Date(2022,8,05), "valid");
        Member invalidMember5 = new Member("valid", " ", 12, new Date(2022,8,05), "valid");
        Member invalidMember6 = new Member("valid", null, 12, new Date(2022,8,05), "valid");
        // etc

        // Act
        boolean actualResult1 = sut.isMemberValid(invalidMember1);
        boolean actualResult2 = sut.isMemberValid(invalidMember2);
        boolean actualResult3 = sut.isMemberValid(invalidMember3);
        boolean actualResult4 = sut.isMemberValid(invalidMember4);
        boolean actualResult5 = sut.isMemberValid(invalidMember5);
        boolean actualResult6 = sut.isMemberValid(invalidMember6);

        // Assert
        Assertions.assertFalse(actualResult1);
        Assertions.assertFalse(actualResult2);
        Assertions.assertFalse(actualResult3);
        Assertions.assertFalse(actualResult4);
        Assertions.assertFalse(actualResult5);
        Assertions.assertFalse(actualResult6);
    }

    @Test
    public void test_login_returnsMember_givenValidLoginCredentials(){
        // Arrange
        String email = "ar@mail.com";
        String password = "gatorFan";

        when(mockMemberRepository.loginCredentialCheck(email,password)).thenReturn(new Member());

        //Act
        Member actualMember = sut.login(email, password);

        // Assert
        Assertions.assertInstanceOf(Member.class, actualMember);
    }

    @Test
    public void test_registerMember_returnsNewMember_givenValidMember(){
        Member validMember = spy(new Member("valid", "valid", 12, new Date(System.currentTimeMillis()), "valid"));
        NewRegistrationRequest registrationRequest = spy(new NewRegistrationRequest("valid", "valid", "pass", 12));
        validMember.setId(registrationRequest.getId());

        // when mocking we need to do a when/then for any DAO call
        when(mockMemberRepository.checkEmail(registrationRequest.getEmail())).thenReturn(true);
        doReturn(validMember).when(mockMemberRepository).create(validMember);

        MemberResponse actualNewMember = sut.registerMember(registrationRequest);

       Assertions.assertInstanceOf(MemberResponse.class, actualNewMember);
        // at the end we need to verify the number of dao calls from this method
        verify(mockMemberRepository, times(2));
    }



}
