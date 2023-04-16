package runner;

import models.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static runner.Constants.TOTAL_DISCOUNT_MONTH_CENTS;
import static runner.DiscountRule.getDiscountRuleList;

public class ApplyDiscounts {
    public static List<Transaction> applyDiscounts(List<Transaction> input){
        Map<LocalDate, Integer> discountsLeftEachMonth = createDiscountsLeftEachMonth(input);

        for(Transaction transaction : input){
            for(DiscountRule discountRule : getDiscountRuleList()){
                int discountAmount = checkDiscountMonthAndSubtract(discountRule.applyRule(transaction), discountsLeftEachMonth, transaction);
                transaction.increaseDiscountCents(discountAmount);
                transaction.createShipmentCostCents(); // we define the shipment cost after the discount is applied

                // TODO create discount rules
            }
        }
        return new ArrayList<Transaction>(); // temporary code to compile
    }

    /**
     This method checks the availability of a discount for a given transaction and subtracts the used discount amount from the corresponding month's available discount amount.
     @param inputDiscount the amount of discount requested
     @param discountsLeftEachMonth a map containing the remaining discounts for each month
     @param transaction the transaction for which the discount is requested
     @return the amount of discount applied to the transaction
     */
    private static int checkDiscountMonthAndSubtract(int inputDiscount, Map<LocalDate, Integer> discountsLeftEachMonth, Transaction transaction) {
        int discountLeftThisMonth = discountsLeftEachMonth.get(transaction.getDate().withDayOfMonth(1));
        int appliedDiscount =  (inputDiscount > discountLeftThisMonth) ? discountLeftThisMonth : inputDiscount;
        discountsLeftEachMonth.put(transaction.getDate().withDayOfMonth(1), discountLeftThisMonth - appliedDiscount);
        return appliedDiscount;
    }

    // here we create a Map that maps each month to the amount of money we can give in discounts
    private static Map<LocalDate, Integer> createDiscountsLeftEachMonth(List<Transaction> input) {
        Map<LocalDate, Integer> output = new HashMap<>(); // we use LocalDate instead of month to differentiate different years

        for (Transaction transaction : input) {
            output.put(transaction.getDate().withDayOfMonth(1), TOTAL_DISCOUNT_MONTH_CENTS); // we set day to 1 since we only care about the month and year
        }
        return output;
    }
}
