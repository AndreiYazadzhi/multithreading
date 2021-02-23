import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class MyRecursive extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 100000;
    private List<Integer> list;

    public MyRecursive(List<Integer> list) {
        this.list = list;
    }

    @Override
    protected Integer compute() {
        return list.size() > THRESHOLD
                ? ForkJoinTask.invokeAll(createSubtasks())
                        .stream()
                        .mapToInt(ForkJoinTask::join)
                        .sum() :
                processing(list);
    }

    private Collection<MyRecursive> createSubtasks() {
        List<MyRecursive> dividedTasks = new ArrayList<>();
        dividedTasks.add(new MyRecursive(
                list.subList(0, list.size() / 2)));
        dividedTasks.add(new MyRecursive(
                list.subList(list.size() / 2, list.size())));
        return dividedTasks;
    }

    private Integer processing(List<Integer> list) {
        return list.stream().reduce(0, Integer::sum);
    }
}
