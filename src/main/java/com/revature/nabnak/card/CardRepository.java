package com.revature.nabnak.card;

import com.revature.nabnak.card.dto.responses.CardResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {

    @Query(value = "FROM Card WHERE member_id = :memberId")
    Iterable<Card> findAllByUser(String memberId);
}
