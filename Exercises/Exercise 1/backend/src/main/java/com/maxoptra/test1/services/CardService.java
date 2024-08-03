package com.maxoptra.test1.services;

import com.maxoptra.test1.models.Card;
import com.maxoptra.test1.repositories.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public void deleteAllCards() {
        cardRepository.deleteAll();
    }

    public void createCard(Card card) {
        card.obfuscate();
        cardRepository.save(card);
    }

    public ArrayList<Card> getAllCards() {
        return cardRepository.findAllSorted();
    }
}
