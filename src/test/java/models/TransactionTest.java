package models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {
    @Test
    void testToStringNoCosts(){
        Transaction transaction = new Transaction(LocalDate.of(2022, 11, 23),
                PackageSize.S, DeliveryMethod.MR);
        String expected = "2022-11-23 S MR";
        assertEquals(expected, transaction.toString());
    }

    @Test
    public void testToStringWithCosts() {
        Transaction transaction = new Transaction(LocalDate.of(2022, 11, 23),
                PackageSize.S, DeliveryMethod.MR, 3012, 50);
        String expected = "2022-11-23 S MR 30.12 0.50";
        assertEquals(expected, transaction.toString());
    }
    @Test
    public void testToStringBadInput() {
        Transaction transaction = new Transaction("2015-02-29 CUSPS");
        String expected = "2015-02-29 CUSPS Ignored";
        assertEquals(expected, transaction.toString());
    }
}
