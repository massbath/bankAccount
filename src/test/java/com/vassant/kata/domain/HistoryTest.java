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
        LocalDateTime NEXTWEEK = TODAY.plusWeeks(1);
        List<Operation> operations = asList(aOperationAt(TOMORROW), aOperationAt(TODAY), aOperationAt(NEXTWEEK));

        History history = History.from(operations);

        assertThat(history.getOperations()).extracting("date").containsExactly(TODAY,TOMORROW,NEXTWEEK);
    }

    private Operation aOperationAt(LocalDateTime TOMORROW) {
        return Operation.builder().date(TOMORROW).build();
    }
}