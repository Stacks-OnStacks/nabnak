package com.revature.nabnak.card;

import com.revature.nabnak.util.interfaces.Crudable;
import com.revature.nabnak.util.exceptions.InvalidUserInputException;
import com.revature.nabnak.util.exceptions.ResourcePersistanceException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CardDao implements Crudable<Card> {

    @Override
    public Card create(Card newObject) {
        return null;
    }

    @Override
    public List<Card> findAll() {
        return null;
    }

    @Override
    public Card findById(String id) {
        return null;
    }

    @Override
    public boolean update(Card updatedObject) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    public List<Card> findAllByUser(String email) {
        return null;
    }
}
