package models;

import java.util.List;

public class TransactionFile {
    private List<Transaction> transactionList;
    public TransactionFile(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
//    public void calculateCosts() {
//        setTransactionList(applyDiscount(getTransactionList()));
//    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
