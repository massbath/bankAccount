package com.vassant.kata.domain;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;


@Value
@Builder
final class History {
    @Singular
    List<Operation> operations;
}
