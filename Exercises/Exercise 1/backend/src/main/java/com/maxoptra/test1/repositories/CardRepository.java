package com.maxoptra.test1.repositories;

import com.maxoptra.test1.models.Card;
import com.maxoptra.test1.utilities.CustomCollections;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

@Component
public class CardRepository {
    private ArrayList<Card> cards = new ArrayList<>();

    public Card findById(String id) {
        return cards.stream()
                .filter(card -> card.getCardNumber().equals(id))
                .collect(CustomCollections.toSingleton());
    }

    public ArrayList<Card> findAllSorted() {
        Collections.sort(cards, Collections.reverseOrder());
        return cards;
    }

    public void save(Card card) {
        if (cards.contains(card))
            throw new IllegalStateException("This card already exists");

        cards.add(card);
    }

    public void save(ArrayList<Card> cards) {
        cards.forEach(this::save);
    }

    public void deleteAll() {
        cards.clear();
    }
}
