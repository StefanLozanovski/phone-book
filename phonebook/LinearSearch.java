package phonebook;

import java.util.List;

class LinearSearch implements Algorithm {

    private final List<String> directory;
    private final List<String> namesToFind;
    static int matchesFound = 0;
    long totalSearchTime;

    LinearSearch(List<String> directory, List<String> namesToFind) {
        this.directory = directory;
        this.namesToFind = namesToFind;
    }

    @Override
    public void start() {
        System.out.println("Start searching (linear search)... ");
        linearSearch();
        Algorithm.printResults(matchesFound, 0, totalSearchTime, false, false);
    }

    protected void linearSearch() {
        matchesFound = 0;
        long startTime = System.currentTimeMillis();

        for (String name : namesToFind) {
            for (String directoryValue : directory) {
                if (directoryValue.contains(name)) {
                    matchesFound++;
                    break;
                }
            }
        }
        totalSearchTime = System.currentTimeMillis() - startTime;
    }
}