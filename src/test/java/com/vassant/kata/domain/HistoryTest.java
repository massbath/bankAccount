package com.vassant.kata.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class HistoryTest {

    @Test
    void should_sort_operations_by_date() {
        LocalDateTime TODAY = LocalDateTime.now();
        LocalDateTime TOMORROW = TODAY.plusDays(1);
        LocalDateTime NEXT_WEEK = TODAY.plusWeeks(1);
        List<Operation> operation = asList(aOperationAt(TOMORROW), aOperationAt(TODAY), aOperationAt(NEXT_WEEK));

        History history = new History(operation);

        assertThat(history.getOperation()).extracting("date").containsExactly(TODAY, TOMORROW, NEXT_WEEK);
    }

    private Operation aOperationAt(LocalDateTime TOMORROW) {
        return new Operation(TOMORROW, OperationType.DEPOSIT, Amount.of(100), Balance.of(0));
    }
}