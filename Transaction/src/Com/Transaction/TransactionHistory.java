package Com.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class TransactionHistory {
    private final List<Transaction> transactions;
    private final ReentrantLock lock;

    public TransactionHistory() {
        transactions = new ArrayList<>();
        lock = new ReentrantLock();
    }

    public int size() {
        lock.lock();
        try {
            return transactions.size();
        } finally {
            lock.unlock();
        }
    }

    public boolean add(Transaction transaction) {
        lock.lock();
        try {
            return transactions.add(transaction);
        } finally {
            lock.unlock();
        }
    }

}
