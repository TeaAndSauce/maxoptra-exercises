package com.maxoptra.test1.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class CardTest {

    @Test
    void compareTo() {
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.now().minus(1, ChronoUnit.DAYS);
        LocalDate date3 = LocalDate.now().plus(1, ChronoUnit.DAYS);
        assert date1.compareTo(date2) > 0;
        assert date1.compareTo(date3) < 0;

        Card card = Card.builder()
                .bank("Test Bank 1")
                .cardNumber("5601-2345-3446-5678")
                .expiryDate(LocalDate.now().plus(5, ChronoUnit.YEARS))
                .build();

        Card card2 = Card.builder()
                .bank("Test Bank 2")
                .cardNumber("1234-5678-9876-4444")
                .expiryDate(LocalDate.now().plus(2, ChronoUnit.YEARS))
                .build();

        assert card2.compareTo(card) < 0;
        assert card.compareTo(card2) > 0;
    }

    @Test
    void testObfuscate() {
        Card card = Card.builder()
                .bank("Test Bank 1")
                .cardNumber("5601-2345-3446-5678")
                .expiryDate(LocalDate.now().plus(5, ChronoUnit.YEARS))
                .build();

        Card card2 = Card.builder()
                .bank("Test Bank 2")
                .cardNumber("1234-5678-9876-444")
                .expiryDate(LocalDate.now().plus(5, ChronoUnit.YEARS))
                .build();

        Card card3 = Card.builder()
                .bank("Test Bank 3")
                .cardNumber("5601234534465678")
                .expiryDate(LocalDate.now().plus(5, ChronoUnit.YEARS))
                .build();

        Card card4 = Card.builder()
                .bank("Test Bank 4")
                .cardNumber("1234-5678-9876")
                .expiryDate(LocalDate.now().plus(5, ChronoUnit.YEARS))
                .build();

        card.obfuscate();
        card2.obfuscate();
        card3.obfuscate();
        card4.obfuscate();

        System.out.println(card);
        System.out.println(card2);
        System.out.println(card3);
        System.out.println(card4);

        assert card.getCardNumber().equals("XXXX-XXXX-XXXX-5678");
        assert card2.getCardNumber().equals("XXXX-XXXX-XXXX-444");
        assert card3.getCardNumber().equals("XXXXXXXXXXXX5678");
        assert card4.getCardNumber().equals("XXXX-XXXX-9876");
    }
}