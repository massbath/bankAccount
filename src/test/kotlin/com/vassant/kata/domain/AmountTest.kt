package com.vassant.kata.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class AmountTest {
    @Test
    fun `cannot be initialize with negativ number`() {
        Assertions.assertThatThrownBy { Amount(-15) }.isInstanceOf(NegativeAmountNotAllowedException::class.java)
    }
}