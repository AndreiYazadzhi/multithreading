import org.apache.log4j.Logger;

public class MyRunnable implements Runnable {
    private static final Logger logger = Logger.getLogger(MyThread.class);
    private final Counter index = new Counter();
    private static final int MAX_VALUE = 100;

    @Override
    public void run() {
        logger.info("Thread example " + Thread.currentThread().getName() + "was started..");
        while (index.getCount() < MAX_VALUE) {
            int count = index.getCount();
            System.out.println(Thread.currentThread().getName() + ": " + ++count);
            index.setCount(count);
        }
        logger.info("Thread example " + Thread.currentThread().getName() + "was finished");
    }
}
