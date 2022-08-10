package com.revature.nabnak.daos;

import com.revature.nabnak.models.Card;
import com.revature.nabnak.models.Member;
import com.revature.nabnak.util.ConnectionFactory;
import com.revature.nabnak.util.exceptions.InvalidUserInputException;
import com.revature.nabnak.util.exceptions.ResourcePersistanceException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CardDao implements Crudable<Card> {
    @Override
    public Card create(Card newCard) {

        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "insert into card (description, points, tech, status, members_email) values (?,?,?,?,?)"; // we want to set up for a preparedStatement (this prevents SQL injection)

            // ; drop table members will be prevented
            PreparedStatement ps = conn.prepareStatement(sql);

            // our ps now needs to be adjusted with the appropriate values instead of the ?
            ps.setString(1, newCard.getDescription());
            ps.setInt(2, newCard.getPoints());
            ps.setString(3, newCard.getTech());
            ps.setString(4, newCard.getStatus());
            ps.setString(5, newCard.getMemberEmail());

            // at this point it's a full sql statement with values where ? are

            int checkInsert = ps.executeUpdate(); // INSERT, UPDATE or DELETE

            if (checkInsert == 0) {
                throw new ResourcePersistanceException("Member was not entered into the database.");
            }

            return newCard;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Card> findAll() {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            List<Card> cards = new LinkedList<>();


            String sql = "select * from card"; // sql string should always be a sql statement


            Statement s = conn.createStatement(); // estbalish a statement is availble
            ResultSet rs = s.executeQuery(sql); // actually execute the query statement and take in a ResultSet (the data from our database)

            while (rs.next()) { // as long as there is a next value (another record) it will continue to add to the linkedList
                Card card = new Card();

                card.setCardId(rs.getInt("card_id"));
                card.setDescription(rs.getString("description"));
                card.setPoints(rs.getInt("points"));
                card.setTech(rs.getString("tech"));
                card.setStatus(rs.getString("status"));
                card.setMemberEmail(rs.getString("members_email"));

                // we now have a completed member
                cards.add(card);
            }

            return cards;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Card findById(String id) {

        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {

            String sql = "select * from card where card_id = ?"; // sql string should always be a sql statement
            PreparedStatement ps = conn.prepareStatement(sql); // estbalish a statement is availble

            ps.setInt(1, Integer.parseInt(id));

            ResultSet rs = ps.executeQuery(); // actually execute the query statement and take in a ResultSet (the data from our database)

            if (!rs.next()) {
                throw new InvalidUserInputException("No card with the id: " + id + " exists in the database");
            }
            Card card = new Card();

            card.setCardId(rs.getInt("card_id"));
            card.setDescription(rs.getString("description"));
            card.setPoints(rs.getInt("points"));
            card.setTech(rs.getString("tech"));
            card.setStatus(rs.getString("status"));
            card.setMemberEmail(rs.getString("members_email"));


            return card;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public boolean update(Card updatedCard) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "update card set description = ?, points = ?, tech = ?, status = ?, members_email = ? where card_id = ?"; // we want to set up for a preparedStatement (this prevents SQL injection)

            // ; drop table members will be prevented
            PreparedStatement ps = conn.prepareStatement(sql);

            // our ps now needs to be adjusted with the appropriate values instead of the ?
            ps.setString(1, updatedCard.getDescription());
            ps.setInt(2, updatedCard.getPoints());
            ps.setString(3, updatedCard.getTech());
            ps.setString(4, updatedCard.getStatus());
            ps.setString(5, updatedCard.getMemberEmail());
            ps.setInt(6, updatedCard.getCardId());

            // at this point it's a full sql statement with values where ? are

            int checkInsert = ps.executeUpdate(); // INSERT, UPDATE or DELETE

            if (checkInsert == 0) {
                throw new ResourcePersistanceException("Member was not entered into the database.");
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "delete from card where card_id = ?"; // we want to set up for a preparedStatement (this prevents SQL injection)

            // ; drop table members will be prevented
            PreparedStatement ps = conn.prepareStatement(sql);

            // our ps now needs to be adjusted with the appropriate values instead of the ?

            ps.setInt(1, Integer.parseInt(id));

            // at this point it's a full sql statement with values where ? are

            int checkInsert = ps.executeUpdate(); // INSERT, UPDATE or DELETE

            if (checkInsert == 0) {
                throw new ResourcePersistanceException("Member was not entered into the database.");
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Card> findAllByUser(String email) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            List<Card> cards = new LinkedList<>();


            String sql = "select * from card where members_email = ?"; // sql string should always be a sql statement
            PreparedStatement ps = conn.prepareStatement(sql); // estbalish a statement is availble

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery(); // actually execute the query statement and take in a ResultSet (the data from our database)

            while (rs.next()) { // as long as there is a next value (another record) it will continue to add to the linkedList
                Card card = new Card();

                card.setCardId(rs.getInt("card_id"));
                card.setDescription(rs.getString("description"));
                card.setPoints(rs.getInt("points"));
                card.setTech(rs.getString("tech"));
                card.setStatus(rs.getString("status"));
                card.setMemberEmail(rs.getString("members_email"));

                // we now have a completed member
                cards.add(card);
            }

            return cards;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
