package runner;

import models.MonthlyRestriction;
import models.Transaction;
import models.TransactionFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static runner.DiscountRule.getDiscountRuleList;

public class ApplyDiscounts {
    public static List<Transaction> applyDiscounts(TransactionFile transactionFile){
        List<Transaction> input = transactionFile.getTransactionList();
        Map<LocalDate, MonthlyRestriction> monthlyRestrictions = transactionFile.getMonthlyRestrictions();

        for(Transaction transaction : transactionFile.getTransactionList()){
            for(DiscountRule discountRule : getDiscountRuleList()){
                // here we apply this discount rule and subtract the used discount amount from the corresponding month's available discount amount
                int discountAmount = discountRule.applyRule(transaction, monthlyRestrictions.get(transaction.getDate().withDayOfMonth(1)));
                // in case the shipment cost is lesser than the discount
                discountAmount = (discountAmount > transaction.getShipmentCostCents()) ? transaction.getShipmentCostCents() : discountAmount;
                discountAmount = transactionFile.checkDiscountMonthAndSubtract(discountAmount, transaction);
                transaction.increaseDiscountCents(discountAmount); // we use increase instead of set to allow for multiple discount rules
            }
        }
        return new ArrayList<Transaction>(); // temporary code to compile
    }
}
