package com.revature.nabnak.daos;

import com.revature.nabnak.models.Card;

public class CardDao implements Crudable<Card> {
    @Override
    public Card create(Card newObject) {
        return null;
    }

    @Override
    public Card[] findAll() {
        return new Card[0];
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
}
