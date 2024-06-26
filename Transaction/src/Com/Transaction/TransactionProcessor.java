package Com.Transaction;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionProcessor {
    private final BlockingQueue<Transaction> queue;
    private final TransactionHistory history;
    private final Logger logger;

    public TransactionProcessor(int queueCapacity) {
        queue = new ArrayBlockingQueue<>(queueCapacity);
        history = new TransactionHistory();
        logger = Logger.getLogger(TransactionProcessor.class.getName()); 
    }

    public void startProcessing() {
        Thread producerThread = new Thread(new Producer());
        Thread consumerThread = new Thread(new Consumer());
        producerThread.start();
        consumerThread.start();
    }

    public BlockingQueue<Transaction> getQueue() {
        return queue;
    }

    public TransactionHistory getHistory() {
        return history;
    }

    private class Producer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Transaction transaction = generateRandomTransaction();
                    queue.put(transaction);
                    if (logger != null) {
                        logger.log(Level.INFO, "Produced: " + transaction);
                    } else {
                        System.out.println("Logger is null, cannot log.");
                    }
                    Thread.sleep(10000); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }

        private Transaction generateRandomTransaction() {
            String[] types = {"Deposit", "Withdrawal"};
            String type = types[(int) (Math.random() * types.length)];
            double amount = Math.random() * 1000;
            return new Transaction(type, amount);
        }
    }

    private class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Transaction transaction = queue.take();
                    processTransaction(transaction);
                    if (logger != null) {
                        logger.log(Level.INFO, "Consumed: " + transaction);
                    } else {
                        System.out.println("Logger is null, cannot log.");
                    }
                    Thread.sleep(70000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }

        private void processTransaction(Transaction transaction) {
            history.add(transaction);
        }
    }

   
}
