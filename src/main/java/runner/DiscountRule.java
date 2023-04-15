package runner;

import models.Transaction;

public interface DiscountRule {
    int apply(Transaction transaction);
}
