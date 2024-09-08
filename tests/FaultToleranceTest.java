
import java.util.concurrent.ExecutionException;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

public class FaultToleranceTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<String> dataChunks = Arrays.asList(
                "cat dog bird",
                "dog bird bird",
                "cat bird dog",
                "dog cat cat");

        // Simulate fault tolerance with retries (max 3 retries per task)
        List<Map<String, Integer>> intermediateResults = FaultTolerance.distributeMapTasksWithRetries(dataChunks, 3);

        // Run the reduce phase
        Map<String, Integer> finalResult = TaskDistribution.runReduce(intermediateResults);
        System.out.println("Final Result with Fault Tolerance: " + finalResult);

        // Shutdown the executor
        TaskDistribution.executor.shutdown();
    }
}
