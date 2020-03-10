package com.vassant.kata.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class HistoryTest {

    @Test
    void should_sort_operations_by_date() {
        LocalDateTime TODAY = LocalDateTime.now();
        LocalDateTime TOMORROW = TODAY.plusDays(1);
        LocalDateTime NEXT_WEEK = TODAY.plusWeeks(1);
        List<Operation> operations = asList(aOperationAt(TOMORROW), aOperationAt(TODAY), aOperationAt(NEXT_WEEK));

        History history = History.from(operations);

        assertThat(history.getOperations()).extracting("date").containsExactly(TODAY, TOMORROW, NEXT_WEEK);
    }

    private Operation aOperationAt(LocalDateTime TOMORROW) {
        return Operation.builder().date(TOMORROW).build();
    }
}