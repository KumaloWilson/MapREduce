
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapReduceTest {

    public static void main(String[] args) {
        // Test the map function
        String input = "hello world hello";
        Map<String, Integer> mapResult = MapReduceFramework.map(input);
        System.out.println("Map Result: " + mapResult);

        // Test the reduce function
        List<Map<String, Integer>> intermediateResults = new ArrayList<>();
        intermediateResults.add(mapResult);
        intermediateResults.add(MapReduceFramework.map("world hello"));

        Map<String, Integer> reduceResult = MapReduceFramework.reduce(intermediateResults);
        System.out.println("Reduce Result: " + reduceResult);
    }
}
