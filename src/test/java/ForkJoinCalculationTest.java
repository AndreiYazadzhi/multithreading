import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ForkJoinCalculationTest {
    private static List<Integer> million;
    private static ForkJoinPool forkJoinPool;

    @BeforeAll
    static void beforeAll() {
        forkJoinPool = ForkJoinPool.commonPool();
        million = Stream.iterate(1, n -> n).limit(1_000_000).collect(Collectors.toList());
    }

    @Test
    public void getSumWithFork_Ok() {
        Assertions.assertEquals(1_000_000, forkJoinPool.invoke(new RecursiveSumCalculator(million)));
    }

    @Test
    public void getSumNegativeElements_ok() {
        List<Integer> testList = List.of(-20, -30, -40, -50, -60, -70, -80, -90, -100);
        Assertions.assertEquals(-540, forkJoinPool.invoke(new RecursiveSumCalculator(testList)));
    }
}
