
import java.util.Random;
import java.util.concurrent.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class FaultTolerance {

    public static Map<String, Integer> mapWithFaultTolerance(String chunk) {
        Random random = new Random();
        int failChance = random.nextInt(10); // Simulate failure with 10% probability
        if (failChance < 2) {
            throw new RuntimeException("Simulated node failure");
        }
        return MapReduceFramework.map(chunk);
    }

    public static List<Map<String, Integer>> distributeMapTasksWithRetries(List<String> dataChunks, int maxRetries)
            throws InterruptedException, ExecutionException {
        List<Map<String, Integer>> results = new ArrayList<>();
        for (String chunk : dataChunks) {
            boolean success = false;
            int retries = 0;
            while (!success && retries < maxRetries) {
                try {
                    results.add(TaskDistribution.executor.submit(() -> mapWithFaultTolerance(chunk)).get());
                    success = true;
                } catch (Exception e) {
                    retries++;
                    System.out.println("Retrying task due to failure... Attempt " + retries);
                }
            }
            if (!success) {
                System.out.println("Task failed after " + maxRetries + " retries.");
            }
        }
        return results;
    }

}
