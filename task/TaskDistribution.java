
import java.util.concurrent.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;

public class TaskDistribution {

    public static ExecutorService executor = Executors.newFixedThreadPool(4);

    public static List<Map<String, Integer>> distributeMapTasks(List<String> dataChunks)
            throws InterruptedException, ExecutionException {
        List<Future<Map<String, Integer>>> futures = new ArrayList<>();
        for (String chunk : dataChunks) {
            futures.add(executor.submit(() -> MapReduceFramework.map(chunk)));
        }

        // Collect intermediate results
        List<Map<String, Integer>> results = new ArrayList<>();
        for (Future<Map<String, Integer>> future : futures) {
            results.add(future.get());
        }
        return results;
    }

    public static Map<String, Integer> runReduce(List<Map<String, Integer>> intermediateResults) {
        return MapReduceFramework.reduce(intermediateResults);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<String> data = Arrays.asList("data chunk 1", "data chunk 2", "data chunk 3", "data chunk 4");
        List<Map<String, Integer>> intermediateResults = distributeMapTasks(data);
        Map<String, Integer> finalResult = runReduce(intermediateResults);
        System.out.println(finalResult);
        executor.shutdown();
    }
}
