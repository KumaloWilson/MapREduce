
import java.util.*;

public class MapReduceFramework {

    // Map function: processes a chunk of text, emitting word counts
    public static Map<String, Integer> map(String input) {
        Map<String, Integer> wordCounts = new HashMap<>();
        String[] words = input.split("\s+");
        for (String word : words) {
            word = word.toLowerCase();
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }
        return wordCounts;
    }

    // Reduce function: aggregates word counts
    public static Map<String, Integer> reduce(List<Map<String, Integer>> intermediateResults) {
        Map<String, Integer> finalCounts = new HashMap<>();
        for (Map<String, Integer> result : intermediateResults) {
            for (Map.Entry<String, Integer> entry : result.entrySet()) {
                finalCounts.put(entry.getKey(), finalCounts.getOrDefault(entry.getKey(), 0) + entry.getValue());
            }
        }
        return finalCounts;
    }
}
        