public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        MyThread threadExample = new MyThread(counter);
        threadExample.start();
        MyRunnable runnableExample = new MyRunnable(counter);
        new Thread(runnableExample).start();
    }
}
