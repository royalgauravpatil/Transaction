package Com.Transaction;

public class Main {

    public static void main(String[] args) {
        TransactionProcessor processor = new TransactionProcessor(10);
        processor.startProcessing();
  
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(30000);
                    System.out.println("Queue size: " + processor.getQueue().size());
                    System.out.println("Total transactions processed: " + processor.getHistory().size());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }).start();
        
        while (true) {
            try {
                Thread.sleep(5000); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
