package models;

import java.util.List;

public class TransactionFile {
    private List<Transaction> transactionList;
    public TransactionFile(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
