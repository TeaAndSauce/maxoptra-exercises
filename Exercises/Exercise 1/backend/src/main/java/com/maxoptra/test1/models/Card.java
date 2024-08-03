package com.maxoptra.test1.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card implements Comparable<Card> {

    @Size(max = 50, message = "Bank name must be 50 characters or less")
    private String bank;

    @Pattern(regexp = "([0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4})", message = "Card number must follow XXXX-XXXX-XXXX-XXXX format")
    private String cardNumber;

    // Uses custom serializer and deserializer
    @JsonFormat(pattern = "MMM-yyyy")
    @DateTimeFormat(pattern = "MMM-yyyy")
    private LocalDate expiryDate;

    /**
     * void obfuscate()
     *
     * Replaces all numbers with 'X' apart
     * from the last 4 digits.
     *
     * Problem:
     * If for some reason two cards under the same bank
     * shared the same last 4 digits as well as expiry month
     * then those cards would be treated as the same.
     *
     * Solution (not implemented):
     * Hash the details of each card before obfuscation, that
     * way we have a way to distinguish cards without exposing
     * the card number.
     */
    public void obfuscate() {
        StringBuilder builder = new StringBuilder();
        int lastDashPos = this.cardNumber.lastIndexOf('-');
        if (lastDashPos == -1) lastDashPos = this.cardNumber.length()-4;
        int index = 0;

        for (char ch : this.cardNumber.toCharArray()) {
            if (ch == '-') builder.append(ch);
            else if (index < lastDashPos) builder.append('X');
            else builder.append(ch);
            index++;
        }
        this.cardNumber = builder.toString();
    }

    @Override
    public int compareTo(Card o) {
        return this.getExpiryDate().compareTo(o.getExpiryDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return getBank().equals(card.getBank()) && getCardNumber().equals(card.getCardNumber()) && getExpiryDate().equals(card.getExpiryDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBank(), getCardNumber(), getExpiryDate());
    }

    @Override
    public String toString() {
        return bank + "\t" + cardNumber + " (expires " + expiryDate + ")";
    }
}
