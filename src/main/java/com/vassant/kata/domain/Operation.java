package com.vassant.kata.domain;

import java.time.LocalDateTime;

public final class Operation {
    private final LocalDateTime date;
    private final OperationType operationType;
    private final Amount amount;
    private final Balance balance;

    Operation(LocalDateTime date, OperationType operationType, Amount amount, Balance balance) {
        this.date = date;
        this.operationType = operationType;
        this.amount = amount;
        this.balance = balance;
    }

    public static OperationBuilder builder() {
        return new OperationBuilder();
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public OperationType getOperationType() {
        return this.operationType;
    }

    public Amount getAmount() {
        return this.amount;
    }

    public Balance getBalance() {
        return this.balance;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Operation)) return false;
        final Operation other = (Operation) o;
        final Object this$date = this.getDate();
        final Object other$date = other.getDate();
        if (this$date == null ? other$date != null : !this$date.equals(other$date)) return false;
        final Object this$operationType = this.getOperationType();
        final Object other$operationType = other.getOperationType();
        if (this$operationType == null ? other$operationType != null : !this$operationType.equals(other$operationType))
            return false;
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        if (this$amount == null ? other$amount != null : !this$amount.equals(other$amount)) return false;
        final Object this$balance = this.getBalance();
        final Object other$balance = other.getBalance();
        if (this$balance == null ? other$balance != null : !this$balance.equals(other$balance)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $date = this.getDate();
        result = result * PRIME + ($date == null ? 43 : $date.hashCode());
        final Object $operationType = this.getOperationType();
        result = result * PRIME + ($operationType == null ? 43 : $operationType.hashCode());
        final Object $amount = this.getAmount();
        result = result * PRIME + ($amount == null ? 43 : $amount.hashCode());
        final Object $balance = this.getBalance();
        result = result * PRIME + ($balance == null ? 43 : $balance.hashCode());
        return result;
    }

    public String toString() {
        return "Operation(date=" + this.getDate() + ", operationType=" + this.getOperationType() + ", amount=" + this.getAmount() + ", balance=" + this.getBalance() + ")";
    }

    public static class OperationBuilder {
        private LocalDateTime date;
        private OperationType operationType;
        private Amount amount;
        private Balance balance;

        OperationBuilder() {
        }

        public Operation.OperationBuilder date(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public Operation.OperationBuilder operationType(OperationType operationType) {
            this.operationType = operationType;
            return this;
        }

        public Operation.OperationBuilder amount(Amount amount) {
            this.amount = amount;
            return this;
        }

        public Operation.OperationBuilder balance(Balance balance) {
            this.balance = balance;
            return this;
        }

        public Operation build() {
            return new Operation(date, operationType, amount, balance);
        }

        public String toString() {
            return "Operation.OperationBuilder(date=" + this.date + ", operationType=" + this.operationType + ", amount=" + this.amount + ", balance=" + this.balance + ")";
        }
    }
}
