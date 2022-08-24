package com.revature.nabnak.member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Stereotype Annotation - can handle dependency & identify it as a bean
public interface MemberRepository extends CrudRepository<Member, String> {
    
    @Query(value = "from Member where email= :email")
    Optional<Member> checkEmail(String email);

    @Query(value = "FROM Member where email = :email AND password = :password")
    Optional<Member> loginCredentialCheck(String email, String password);

}
