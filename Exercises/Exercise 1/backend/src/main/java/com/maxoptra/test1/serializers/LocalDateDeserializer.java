package com.maxoptra.test1.serializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-yyyy");

    public static int monthToNumber(String monthName) {
        Optional<Month> monthOptional = Arrays.stream(Month.values())
                .filter(month -> month.name().substring(0, 3).equalsIgnoreCase(monthName))
                .findFirst();

        return monthOptional.orElseThrow(IllegalArgumentException::new).getValue();
    }

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String date = jsonParser.getText().toUpperCase();
        String[] monthYear = date.split("-");

        if (monthYear.length != 2)
            throw new IOException("Date format should be 'MON-YEAR' e.g. FEB-2024");

        if (!monthYear[1].chars().allMatch(Character::isDigit))
            throw new IOException("Date year must be a number");

        int month = monthToNumber(monthYear[0]);
        int year = Integer.parseInt(monthYear[1]);
        return LocalDate.of(year, month, 1);
    }
}
