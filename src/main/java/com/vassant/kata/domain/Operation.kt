package com.vassant.kata.domain

import java.time.LocalDateTime

data class Operation(var date: LocalDateTime,
                     val operationType: OperationType,
                     val amount: Amount,
                     val balance: Balance)