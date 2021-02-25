import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableSumCalculator {
    private static final int LIMIT = 1000;

    public static int getSum(List<Integer> list, int threads) {
        if (threads <= 0 || threads > LIMIT) {
            throw new RuntimeException("Incorrect number of threads");
        }
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Callable<Integer>> tasks = new ArrayList<>();
        for (int i = 0; i < list.size(); i += list.size() / threads) {
            int length = i + list.size() / threads;
            tasks.add(new MyMultiAdder(list.subList(i, Math.min(length, list.size()))));
        }
        try {
            List<Future<Integer>> futures = executor.invokeAll(tasks);
            int result = 0;
            for (Future<Integer> future : futures) {
                result += future.get();
            }
            executor.shutdown();
            return result;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Can't complete adding", e);
        }
    }
}
