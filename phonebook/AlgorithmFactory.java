package phonebook;

import java.util.List;

class AlgorithmFactory {

    private final List<String> directory;
    private final List<String> namesToFind;

    AlgorithmFactory(List<String> directory, List<String> namesToFind) {
        this.directory = directory;
        this.namesToFind = namesToFind;
    }

    public Algorithm getAlgorithm(String algorithmType) {
        switch (algorithmType) {
            case "LinearSearch":
                return new LinearSearch(directory, namesToFind);
            case "BubbleSortJumpSearch":
                return new BubbleSortJumpSearch(directory, namesToFind);
            case "QuickSortBinarySearch":
                return new QuickSortBinarySearch(directory, namesToFind);
            case "HashTableSearch":
                return new HashTableSearch(directory, namesToFind);
            default:
                throw new IllegalArgumentException("Invalid algorithm type!");
        }
    }
}