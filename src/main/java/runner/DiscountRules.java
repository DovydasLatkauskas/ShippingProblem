package runner;

import models.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static runner.Constants.TOTAL_DISCOUNT_MONTH_CENTS;

public class DiscountRules {
    public static List<Transaction> applyDiscounts(List<Transaction> input){
        Map<LocalDate, Integer> discountsLeftEachMonth = createDiscountsLeftEachMonth(input);

        for(Transaction transaction : input){
            for(DiscountRule discountRule : discountList){
                int discountAmount = checkDiscountMonth(discountRule.apply(transaction), discountsLeftEachMonth, transaction);
                transaction.setDiscountCents(discountAmount);
                //transaction.setShipmentCostCents(); //TODO complete

                // TODO create discountList
                // TODO change setDiscountCents
                // TODO create discount rules
            }
        }
        return new ArrayList<Transaction>(); // temporary code to compile
    }

    private static int checkDiscountMonth(int inputDiscount, Map<LocalDate, Integer> discountsLeftEachMonth, Transaction transaction) {
        int discountLeftThisMonth = discountsLeftEachMonth.get(transaction.getDate().withDayOfMonth(1));
        return (inputDiscount > discountLeftThisMonth) ? discountLeftThisMonth : inputDiscount;
    }

    // here we create a Map that maps each month to the amount of money we can give in discounts
    private static Map<LocalDate, Integer> createDiscountsLeftEachMonth(List<Transaction> input) {
        Map<LocalDate, Integer> output = new HashMap<>(); // we use LocalDate instead of month to differentiate different years

        for (Transaction transaction : input) {
            output.put(transaction.getDate().withDayOfMonth(1), TOTAL_DISCOUNT_MONTH_CENTS); // we set day to 1 since we only care about the month and year
        }
        return output;
    }

    private static Transaction matchSmallShipmentPrice(){
        //TODO complete
        return new Transaction("temp");
    }
}
