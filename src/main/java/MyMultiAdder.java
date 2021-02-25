import java.util.List;
import java.util.concurrent.Callable;

public class MyMultiAdder implements Callable<Integer> {
    private final List<Integer> numbers;

    public MyMultiAdder(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public Integer call() throws Exception {
        return numbers.stream().reduce(0, Integer::sum);
    }
}
