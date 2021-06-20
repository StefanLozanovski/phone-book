package phonebook;

import java.util.Collections;
import java.util.List;

public class BubbleSortJumpSearch implements Algorithm {

    private final List<String> directory;
    private final List<String> namesToFind;
    static int matchesFound = 0;
    long totalSearchTime;
    long totalSortTime;
    static boolean sortingTakesTooLong = false;

    BubbleSortJumpSearch(List<String> directory, List<String> namesToFind) {
        this.directory = directory;
        this.namesToFind = namesToFind;
    }

    @Override
    public void start() {
        System.out.println("Start searching (bubble sort + jump search)... ");
        bubbleSort();
        if (sortingTakesTooLong) {
            long startTime = System.currentTimeMillis();
            new LinearSearch(directory, namesToFind).linearSearch();
            matchesFound = LinearSearch.matchesFound;
            totalSearchTime = System.currentTimeMillis() - startTime;
        } else {
            jumpSearch();
        }
        Algorithm.printResults(matchesFound, totalSortTime, totalSearchTime, true, sortingTakesTooLong);
    }

    protected void bubbleSort() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < directory.size() - 1; i++) {
            for (int j = 0; j < directory.size() - i - 1; j++) {
                if (directory.get(j).compareTo(directory.get(j + 1)) > 0) {
                    Collections.swap(directory, j, j + 1);
                    totalSortTime = System.currentTimeMillis() - startTime;

                    /* Stops if sorting time takes too long */
                    if (totalSortTime > totalSearchTime * 10) {
                        sortingTakesTooLong = true; // 10 times longer than all 500 iterations of the linear search
                        return;
                    }
                }
            }
        }
    }

    protected void jumpSearch() {
        int jumpLength = (int) Math.sqrt(directory.size());
        int currentRight = 0;
        int prevRight = 0;
        boolean isFound = false;

        long startTime = System.currentTimeMillis();

        if (directory.size() == 0 || namesToFind.size() == 0) {
            totalSearchTime = 0;
            return;
        }

        for (String name : namesToFind) {
            if (directory.get(0).contains(name)) {
                matchesFound++;
            }
            while (currentRight < directory.size() - 1) {
                currentRight = Math.min(directory.size() - 1, currentRight + jumpLength);
                if (directory.get(currentRight).compareTo(name) > 0) {
                    isFound = true;
                    break;
                }
                prevRight = currentRight;
            }

            /* Doing backward linear search in the found block */
            if (isFound) {
                for (int j = currentRight; j > prevRight; j--) {
                    if (directory.get(j).contains(name)) {
                        matchesFound++;
                    }
                }
            }
        }
        totalSearchTime = System.currentTimeMillis() - startTime;
    }
}