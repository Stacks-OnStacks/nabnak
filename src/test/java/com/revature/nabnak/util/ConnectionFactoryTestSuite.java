package com.revature.nabnak.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactoryTestSuite {

    @Test
    public void test_getConnection_returnValidConnection_givenProvidedCredentialsAreCorrect(){
        try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection();) {
            System.out.println(conn);
            Assertions.assertNotNull(conn);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
