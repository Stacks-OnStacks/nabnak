package com.revature.nabnak.card;

import com.revature.nabnak.util.interfaces.Crudable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
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
