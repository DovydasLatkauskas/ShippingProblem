package runner;

import models.*;
import org.junit.jupiter.api.Test;
import runner.DiscountRule;
import runner.MatchSmallPackagePrice;
import runner.ThirdLargeShipmentLP;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountRuleTest {
    @Test
    public void testMatchSmallPackagePrice() {
        // create a transaction with small package size with MR delivery method
        Transaction transaction = new Transaction(LocalDate.of(2022,10,15), PackageSize.S, DeliveryMethod.MR, 200, 0);
        MonthlyRestriction thisMonth = new MonthlyRestriction();

        // create an instance of the MatchSmallPackagePrice discount rule and apply it to the transaction
        DiscountRule discountRule = new MatchSmallPackagePrice();
        int discount = discountRule.applyRule(transaction, thisMonth);
        assertEquals(50, discount);
    }
    @Test
    public void testThirdLargeShipmentLP() { // could definitely be written nicer but this is only a test, so it's fine
        LocalDate date = LocalDate.of(2022,10,1);
        ArrayList<Transaction> transactionList = new ArrayList<>();
        Transaction transaction1 = new Transaction(date, PackageSize.L, DeliveryMethod.LP, 690, 0);
        Transaction transaction2 = new Transaction(date, PackageSize.L, DeliveryMethod.LP, 690, 0);
        Transaction transaction3 = new Transaction(date, PackageSize.L, DeliveryMethod.LP, 690, 0);
        transactionList.add(transaction1);
        transactionList.add(transaction2);
        transactionList.add(transaction3);

        TransactionFile transactionFile = new TransactionFile(transactionList);

        DiscountRule discountRule1 = new ThirdLargeShipmentLP();
        int discount1 = discountRule1.applyRule(transaction1, transactionFile.getMonthlyRestrictions().get(date));
        DiscountRule discountRule2 = new ThirdLargeShipmentLP();
        int discount2 = discountRule2.applyRule(transaction2, transactionFile.getMonthlyRestrictions().get(date));
        DiscountRule discountRule3 = new ThirdLargeShipmentLP();
        int discount3 = discountRule3.applyRule(transaction3, transactionFile.getMonthlyRestrictions().get(date));

        // the first two transactions should not have a discount, because this is not the third large LP shipment this month
        assertEquals(0, discount1);
        assertEquals(0, discount2);
        // the third transaction should have a discount equal to the shipment cost, because this is the third large LP shipment this month
        assertEquals(690, discount3);
    }
}
