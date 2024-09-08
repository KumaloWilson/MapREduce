
import java.util.Random;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class LargeDatasetTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<String> dataChunks = new ArrayList<>();

        // Simulate a large dataset
        for (int i = 0; i < 1000; i++) {
            dataChunks.add(generateRandomText(500));
        }

        // Test with the large dataset
        List<Map<String, Integer>> intermediateResults = TaskDistribution.distributeMapTasks(dataChunks);
        Map<String, Integer> finalResult = TaskDistribution.runReduce(intermediateResults);

        System.out.println("Final Result for Large Dataset: " + finalResult);

        // Shutdown the executor
        TaskDistribution.executor.shutdown();
    }

    private static String generateRandomText(int wordCount) {
        String[] words = { "apple", "banana", "orange", "grape", "pear", "cherry", "melon" };
        Random rand = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < wordCount; i++) {
            text.append(words[rand.nextInt(words.length)]).append(" ");
        }
        return text.toString();
    }
}
