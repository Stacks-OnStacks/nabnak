package com.revature.nabnak.util;

import com.revature.nabnak.member.Member;
import com.revature.nabnak.member.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
public class RepoTestSuite {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void testConnection() throws SQLException {
        Iterable<Member> members = memberRepository.findAll();
        Assertions.assertNotNull(members);
    }

}
