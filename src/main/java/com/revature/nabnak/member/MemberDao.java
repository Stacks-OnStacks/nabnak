package com.revature.nabnak.member;

import com.revature.nabnak.util.interfaces.Crudable;
import com.revature.nabnak.util.ConnectionFactory;
import com.revature.nabnak.util.exceptions.InvalidUserInputException;
import com.revature.nabnak.util.exceptions.ResourcePersistanceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MemberDao implements Crudable<Member> {

    private final Logger logger = LogManager.getLogger();

    @Override
    public Member create(Member newMember) {
        logger.info("User decided to create a new entry and register with {}", newMember);
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()){
            String sql = "insert into members (email, password, full_name, experience_months, registration_date) values (?,?,?,?,?)"; // we want to set up for a preparedStatement (this prevents SQL injection)

            // ; drop table members will be prevented
            PreparedStatement ps = conn.prepareStatement(sql);

            // our ps now needs to be adjusted with the appropriate values instead of the ?
            ps.setString(2, newMember.getPassword());
            ps.setString(1, newMember.getEmail());
            ps.setString(3, newMember.getFullName());
            ps.setInt(4, newMember.getExperienceMonths());
            ps.setDate(5, newMember.getRegistrationDate());

            // at this point it's a full sql statement with values where ? are

            int checkInsert = ps.executeUpdate(); // INSERT, UPDATE or DELETE

            if(checkInsert == 0){
                throw new ResourcePersistanceException("Member was not entered into the database.");
            }
            logger.info("User has successfully created a new entry with {}", newMember);

            return newMember;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Member> findAll() {

        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()){
            List<Member> members = new ArrayList<>();


            String sql = "select * from members"; // sql string should always be a sql statement


            Statement s = conn.createStatement(); // estbalish a statement is availble
            ResultSet rs = s.executeQuery(sql); // actually execute the query statement and take in a ResultSet (the data from our database)

            while(rs.next()){ // as long as there is a next value (another record) it will continue to add to the linkedList
                Member member = new Member();

                member.setEmail(rs.getString("email"));
                member.setFullName(rs.getString("full_name"));
                member.setExperienceMonths(rs.getInt("experience_months"));
                member.setRegistrationDate(rs.getDate("registration_date"));
                member.setPassword(rs.getString("password"));

                // we now have a completed member
                members.add(member);
            }

            return members;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Member findById(String id) {
        try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection()){
            String sql = "select * from members where email = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();

            if(!rs.next()){
                throw new InvalidUserInputException("Enter information is incorrect for login, please try agian");
            }

            Member member = new Member();

            member.setEmail(rs.getString("email"));
            member.setFullName(rs.getString("full_name"));
            member.setExperienceMonths(rs.getInt("experience_months"));
            member.setRegistrationDate(rs.getDate("registration_date"));
            member.setPassword(rs.getString("password"));

            return member;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Member updatedMember) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()){
            String sql = "update members set email = ?, password = ? , full_name = ? , experience_months = ? where email = ?"; // we want to set up for a preparedStatement (this prevents SQL injection)

            // ; drop table members will be prevented
            PreparedStatement ps = conn.prepareStatement(sql);

            // our ps now needs to be adjusted with the appropriate values instead of the ?
            ps.setString(1, updatedMember.getEmail());
            ps.setString(2, updatedMember.getPassword());
            ps.setString(3, updatedMember.getFullName());
            ps.setInt(4, updatedMember.getExperienceMonths());
            ps.setString(5, updatedMember.getEmail());

            // at this point it's a full sql statement with values where ? are

            int checkInsert = ps.executeUpdate(); // INSERT, UPDATE or DELETE

            if(checkInsert == 0){
                throw new ResourcePersistanceException("Member was not entered into the database.");
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String email) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()){
            String sql = "delete from members where email = ?"; // we want to set up for a preparedStatement (this prevents SQL injection)

            // ; drop table members will be prevented
            PreparedStatement ps = conn.prepareStatement(sql);

            // our ps now needs to be adjusted with the appropriate values instead of the ?
            ps.setString(1, email);


            // at this point it's a full sql statement with values where ? are

            int checkInsert = ps.executeUpdate(); // INSERT, UPDATE or DELETE

            if(checkInsert == 0){
                throw new ResourcePersistanceException("Member was not entered into the database.");
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Member loginCredentialCheck(String email, String password) {

        try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection()){
            String sql = "select * from members where email = ? and password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(!rs.next()){
             throw new InvalidUserInputException("Enter information is incorrect for login, please try agian");
            }

            Member member = new Member();

            member.setEmail(rs.getString("email"));
            member.setFullName(rs.getString("full_name"));
            member.setExperienceMonths(rs.getInt("experience_months"));
            member.setRegistrationDate(rs.getDate("registration_date"));
            member.setPassword(rs.getString("password"));

            return member;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
