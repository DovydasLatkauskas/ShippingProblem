package models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static runner.ApplyDiscounts.applyDiscounts;

public class TransactionFile {
    private List<Transaction> transactionList;
    private Map<LocalDate, MonthlyRestriction> monthlyRestrictions;
    public TransactionFile(List<Transaction> transactionList) {
        this.transactionList = transactionList;
        this.monthlyRestrictions = createMonthlyRestrictionMap(transactionList);
    }
    public void calculateCosts() {
        setTransactionList(applyDiscounts(this));
    }

    public void createTextFile(String outputFolderPath, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFolderPath + "output_of_" + fileName))) {
            for (Transaction transaction : getTransactionList()) {
                writer.write(transaction.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred in TransactionFile.createTextFile.");
            e.printStackTrace();
        }
    }
    public void printToConsole() {
        for (Transaction transaction : getTransactionList()) {
            System.out.println(transaction.toString());
        }
    }

    // here we create a Map that maps each month to the discount available for that month
    private static Map<LocalDate, MonthlyRestriction> createMonthlyRestrictionMap(List<Transaction> input) {
        Map<LocalDate, MonthlyRestriction> output = new HashMap<>(); // we use LocalDate instead of month to differentiate different years

        for (Transaction transaction : input) {
            if(transaction.getBadInputText() != null){continue;} // we skip bad inputs
            output.put(transaction.getDate().withDayOfMonth(1), new MonthlyRestriction()); // we set day to 1 since we only care about the month and year
        }
        return output;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
    /**
     This method checks the availability of a discount for a given transaction and subtracts the used discount amount from the corresponding month's available discount amount.
     @param inputDiscount the amount of discount requested
     @param transaction the transaction for which the discount is requested
     @return the amount of discount applied to the transaction
     */
    public int checkDiscountMonthAndSubtract(int inputDiscount, Transaction transaction) {
        MonthlyRestriction thisMonth = this.getMonthlyRestrictions().get(transaction.getDate().withDayOfMonth(1));
        int discountLeftThisMonth = thisMonth.getAvailableDiscountCents();
        int appliedDiscount =  (inputDiscount > discountLeftThisMonth) ? discountLeftThisMonth : inputDiscount;
        thisMonth.setAvailableDiscountCents(discountLeftThisMonth - appliedDiscount);
        return appliedDiscount;
    }

    public Map<LocalDate, MonthlyRestriction> getMonthlyRestrictions() {
        return this.monthlyRestrictions;
    }
}
