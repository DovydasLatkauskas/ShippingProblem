package runner;

import models.Transaction;

import java.util.ArrayList;
import java.util.List;

import static runner.Constants.TOTAL_DISCOUNT_MONTH_CENTS;

public class DiscountRules {
    public static List<Transaction> applyDiscounts(List<Transaction> input){
        int discountLeftCents = TOTAL_DISCOUNT_MONTH_CENTS;
        for(Transaction transaction : input){
            for(DiscountRule discountRule : discountList){
                int discountAmount = discountRule.apply(transaction);
            }
        }
        return new ArrayList<Transaction>();
    }
    private static Transaction matchSmallShipmentPrice(){

    }
}
