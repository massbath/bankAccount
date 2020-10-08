package com.vassant.kata.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AmountTest {

    @Test
    void cannot_be_initialize_with_negativ_number() {
        assertThatThrownBy(() -> new Amount(-15)).isInstanceOf(NegativeAmountNotAllowedException.class);
    }
}