package Com.Transaction;

import java.util.UUID;

public class Transaction {
    private String transactionID;
    private long timestamp;
    private String transactionType;
    private double amount;

    public Transaction(String transactionType, double amount) {
        this.transactionID = UUID.randomUUID().toString();
        this.timestamp = System.currentTimeMillis();
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID='" + transactionID + '\'' +
                ", timestamp=" + timestamp +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                '}';
    }
}
