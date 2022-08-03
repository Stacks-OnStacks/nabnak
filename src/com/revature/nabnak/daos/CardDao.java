package com.revature.nabnak.daos;

import com.revature.nabnak.models.Card;
import com.revature.nabnak.util.CustomCollections.LinkedList;
import com.revature.nabnak.util.CustomCollections.List;

public class CardDao implements Crudable<Card> {
    @Override
    public Card create(Card newObject) {
        return null;
    }

    @Override
    public List<Card> findAll() {
        return new LinkedList<>();
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
