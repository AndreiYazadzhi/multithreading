public class Main {
    public static void main(String[] args) {
        MyThread threadExample = new MyThread();
        threadExample.start();
        MyRunnable runnableExample = new MyRunnable();
        new Thread(runnableExample).start();
    }
}
