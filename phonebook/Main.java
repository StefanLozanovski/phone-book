package phonebook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        final String directoryPath = "..\\directory.txt";
        final String namesToFindPath = "..\\find.txt";

        List<String> directory = removeNumbers(Files.readAllLines(Paths.get(directoryPath)));
        List<String> namesToFind = Files.readAllLines(Paths.get(namesToFindPath));

        AlgorithmFactory factory = new AlgorithmFactory(directory, namesToFind);
        Algorithm linearSearch = factory.getAlgorithm("LinearSearch");
        Algorithm bubbleSortJumpSearch = factory.getAlgorithm("BubbleSortJumpSearch");
        Algorithm quickSortBinarySearch = factory.getAlgorithm("QuickSortBinarySearch");
        Algorithm hashTableSearch = factory.getAlgorithm("HashTableSearch");

        linearSearch.start();
        bubbleSortJumpSearch.start();
        quickSortBinarySearch.start();
        hashTableSearch.start();
    }

    private static List<String> removeNumbers(List<String> directory) {
        for (int i = 0; i < directory.size(); i++) {
            directory.set(i, directory.get(i).replaceAll("\\d", "").trim());
        }
        return directory;
    }
}