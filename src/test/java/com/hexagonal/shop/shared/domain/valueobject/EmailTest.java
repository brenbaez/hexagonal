package com.hexagonal.shop.shared.domain.valueobject;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailTest {

    @Test
    void shouldCreateEmail() {
        List<String> emails = Arrays.asList("first.last@iana.org",
                "wo..oly@iana.org",
                "Tera@Code.com");
        List<Email> validEmails = emails.stream().map(Email::new).collect(Collectors.toList());

        assertEquals(emails.size(), validEmails.size());
    }

    @Test
    void shouldBeInvalidEmail() {
        String invalidEmail = "Tera@Code.1234";
        Exception exception = assertThrows(InvalidEmail.class, () -> new Email(invalidEmail));

        String expectedMessage = "<" + invalidEmail + "> is not a valid email";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}