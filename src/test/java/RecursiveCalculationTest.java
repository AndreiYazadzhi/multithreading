import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RecursiveCalculationTest {
    private static List<Integer> million;

    @BeforeAll
    static void beforeAll() {
        million = Stream.iterate(1, n -> n).limit(1_000_000).collect(Collectors.toList());
    }

    @Test
    public void getSumWithExecutor_Ok() {
        Assertions.assertEquals(1_000_000, CallableSumCalculator.getSum(million, 1));
        Assertions.assertEquals(1_000_000, CallableSumCalculator.getSum(million, 2));
        Assertions.assertEquals(1_000_000, CallableSumCalculator.getSum(million, 3));
    }

    @Test
    public void getSumWithExecutor_NotOk() {
        Assertions.assertThrows(RuntimeException.class, ()
                        -> CallableSumCalculator.getSum(million, 0),
                "Was expected exception with incorrect value");
    }
}
