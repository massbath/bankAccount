package com.vassant.kata.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AmountTest {
    @Test
    void cannot_be_initialize_with_negativ_number() {
        assertThatThrownBy(()-> Amount.of(-15)).isInstanceOf(NegativeAmountNotAllowedException.class);
    }
}