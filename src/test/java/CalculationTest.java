import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CalculationTest {
    private static List<Integer> million;
    private static ForkJoinPool forkJoinPool;

    @BeforeAll
    static void beforeAll() {
        forkJoinPool = ForkJoinPool.commonPool();
        million = Stream.iterate(1, n -> n).limit(1_000_000).collect(Collectors.toList());
    }

    @Test
    void getSumWithFork_Ok() {
        Assertions.assertEquals(1_000_000, forkJoinPool.invoke(new MyRecursive(million)));
    }

    @Test
    void getSumWithExecutor_Ok() {
        Assertions.assertEquals(1_000_000, MyCallable.getSum(million, 1));
        Assertions.assertEquals(1_000_000, MyCallable.getSum(million, 2));
        Assertions.assertEquals(1_000_000, MyCallable.getSum(million, 3));
    }

    @Test
    void getSumWithExecutor_NotOk() {
        Assertions.assertThrows(RuntimeException.class, ()
                        -> MyCallable.getSum(million, 0),
                "Was expected exception with incorrect value");
    }
}
