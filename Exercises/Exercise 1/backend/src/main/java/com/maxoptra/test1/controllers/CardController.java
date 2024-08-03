package com.maxoptra.test1.controllers;

import com.maxoptra.test1.models.Card;
import com.maxoptra.test1.services.CardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
@CrossOrigin(origins = { "http://localhost:4200" })
public class CardController {

    @Autowired
    private CardService cardService;

    @PutMapping("/create")
    public void createCard(@Valid @RequestBody Card card) {
        cardService.createCard(card);
    }

    @GetMapping
    public List<Card> getCards() {
        return cardService.getAllCards();
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        cardService.deleteAllCards();
    }
}
