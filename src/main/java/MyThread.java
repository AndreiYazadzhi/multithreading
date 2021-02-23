import org.apache.log4j.Logger;

public class MyThread extends Thread {
    private static final Logger logger = Logger.getLogger(MyThread.class);
    private static final int MAX_VALUE = 100;
    private final Counter index;

    public MyThread(Counter index) {
        this.index = index;
    }

    @Override
    public void run() {
        logger.info("Thread example " + Thread.currentThread().getName() + "was started..");
        while (index.getCount() < MAX_VALUE) {
            logger.info(Thread.currentThread().getName() + ": " + index.increment());
        }
        logger.info("Thread example " + Thread.currentThread().getName() + "was finished");
    }
}
