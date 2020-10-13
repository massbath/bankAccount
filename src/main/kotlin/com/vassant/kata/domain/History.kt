package com.vassant.kata.domain

class History(operations: Collection<Operation>) {
    var lines: Set<HistoryLine>

    init {
        lines = operations.sortedBy { it.date }
                .fold(mutableSetOf(), { historyLines, operation ->
                    val actualBalance = if (historyLines.isEmpty()) Balance.of(0) else historyLines.last().balance
                    historyLines += HistoryLine(operation, actualBalance apply operation)
                    historyLines
                })
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as History

        if (lines != other.lines) return false

        return true
    }

    override fun hashCode(): Int {
        return lines.hashCode()
    }

}

data class HistoryLine(val operation: Operation, val balance: Balance)
