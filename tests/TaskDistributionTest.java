
import java.util.concurrent.ExecutionException;
import java.util.List;
import java.util.Arrays;
import java.util.Map;

public class TaskDistributionTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Simulate data chunks
        List<String> dataChunks = Arrays.asList(
                "apple orange banana",
                "banana orange apple",
                "apple apple banana",
                "orange banana banana");

        // Distribute map tasks and collect intermediate results
        List<Map<String, Integer>> intermediateResults = TaskDistribution.distributeMapTasks(dataChunks);

        // Run the reduce phase
        Map<String, Integer> finalResult = TaskDistribution.runReduce(intermediateResults);
        System.out.println("Final Result: " + finalResult);

        // Shutdown the executor
        TaskDistribution.executor.shutdown();
    }
}
