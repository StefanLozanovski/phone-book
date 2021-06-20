package phonebook;

import java.util.Collections;
import java.util.List;

public class QuickSortBinarySearch implements Algorithm {

    private final List<String> directory;
    private final List<String> namesToFind;
    static int matchesFound = 0;
    long totalSearchTime;
    long totalSortTime;

    QuickSortBinarySearch(List<String> directory, List<String> namesToFind) {
        this.directory = directory;
        this.namesToFind = namesToFind;
    }

    @Override
    public void start() {
        System.out.println("Start searching (quick sort + binary search)...");
        long startTime = System.currentTimeMillis();

        quickSort(0, directory.size() - 1);
        totalSortTime = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();

        for (String name : namesToFind) {
            if (binarySearch(name)) {
                matchesFound++;
            }
        }
        totalSearchTime = System.currentTimeMillis() - startTime;
        Algorithm.printResults(matchesFound, totalSortTime, totalSearchTime, true, false);
    }

    private void quickSort(int left, int right) {
        if (left < right) {
            int pivotIndex = partition(left, right);
            quickSort(left, pivotIndex - 1);
            quickSort(pivotIndex + 1, right);
        }
    }

    private int partition(int left, int right) {
        String pivot = directory.get(right);
        int partitionIndex = left;

        for (int i = left; i < right; i++) {
            if (directory.get(i).compareTo(pivot) < 0) {
                Collections.swap(directory, i, partitionIndex);
                partitionIndex++;
            }
        }
        Collections.swap(directory, partitionIndex, right);
        return partitionIndex;
    }

    private boolean binarySearch(String target) {
        int left = 0;
        int right = directory.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            String directoryValue = directory.get(mid);

            if (directoryValue.contains(target)) {
                return true;
            } else if (directory.get(mid).compareTo(target) > 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}