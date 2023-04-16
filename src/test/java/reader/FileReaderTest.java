package reader;
import models.DeliveryMethod;
import models.PackageSize;
import models.Transaction;
import org.junit.jupiter.api.Test;
import reader.FileReader;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class FileReaderTest {
    @Test
    public void testValidInputTransaction() {
        String input = "2022-01-01 M MR";
        Transaction transaction = FileReader.stringToTransactionTest(input);
        assertNotNull(transaction);
        assertEquals(transaction.getDate(), LocalDate.parse("2022-01-01"));
        assertEquals(transaction.getPackageSize(), PackageSize.M);
        assertEquals(transaction.getDeliveryMethod(), DeliveryMethod.MR);
        assertEquals(transaction.getShipmentCostCents(), 300);
        assertEquals(transaction.getDiscountCents(), 0);
    }

    @Test
    public void testValidOutputTransaction() {
        String input = "2022-01-01 M MR 100.50 10.00";
        Transaction transaction = FileReader.stringToTransactionTest(input);
        assertNotNull(transaction);
        assertEquals(transaction.getDate(), LocalDate.parse("2022-01-01"));
        assertEquals(transaction.getPackageSize(), PackageSize.M);
        assertEquals(transaction.getDeliveryMethod(), DeliveryMethod.MR);
        assertEquals(transaction.getShipmentCostCents(), Integer.valueOf(10050));
        assertEquals(transaction.getDiscountCents(), Integer.valueOf(1000));
    }

    @Test
    public void testValidOutputTransactionNoDiscount() {
        String input = "2022-01-01 M MR 100.50 -";
        Transaction transaction = FileReader.stringToTransactionTest(input);
        assertNotNull(transaction);
        assertEquals(transaction.getDate(), LocalDate.parse("2022-01-01"));
        assertEquals(transaction.getPackageSize(), PackageSize.M);
        assertEquals(transaction.getDeliveryMethod(), DeliveryMethod.MR);
        assertEquals(transaction.getShipmentCostCents(), Integer.valueOf(10050));
        assertEquals(transaction.getDiscountCents(), 0);
    }

    @Test
    public void testInvalidTransaction() {
        String input = "2022-01-01 ERROR";
        Transaction transaction = FileReader.stringToTransactionTest(input);
        assertNotNull(transaction);
        assertEquals(transaction.getBadInputText(), input);
        assertNull(transaction.getDate());
        assertNull(transaction.getPackageSize());
        assertNull(transaction.getDeliveryMethod());
        assertNull(transaction.getShipmentCostCents());
        assertEquals(transaction.getDiscountCents(), 0);
    }
}