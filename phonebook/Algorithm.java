package phonebook;

interface Algorithm {

    void start();

    static String convertTime(long totalMilliseconds) {
        long minutes = (totalMilliseconds / 1000) / 60;
        long seconds = (totalMilliseconds / 1000) % 60;
        long millis = totalMilliseconds - seconds * 1000;
        return String.format("%2d min. %2d sec. %2d ms.", minutes, seconds, millis);
    }

    static void printResults(int matchesFound, long totalSortTime, long totalSearchTime, boolean isNextSearch, boolean sortingTakesTooLong) {
        if (!isNextSearch) {
            System.out.printf("Found %d / %d entries. ", matchesFound, 500);
            System.out.println("Time taken: " + convertTime(totalSearchTime) + "\n");
        } else {
            System.out.printf("Found %d / %d entries. ", matchesFound, 500);
            System.out.println("Time taken: " + convertTime(totalSortTime + totalSearchTime));
            System.out.println("Sorting time: " + convertTime(totalSortTime) + (sortingTakesTooLong ? " - STOPPED, moved to linear search" : ""));
            System.out.println("Searching time: " + convertTime(totalSearchTime) + "\n");
        }
    }

    static void printResults(int matchesFound, long totalCreateTime, long totalSearchTime) {
        System.out.printf("Found %d / %d entries. ", matchesFound, 500);
        System.out.println("Time taken: " + convertTime(totalCreateTime + totalSearchTime));
        System.out.println("Creating time: " + convertTime(totalCreateTime));
        System.out.println("Searching time: " + convertTime(totalSearchTime));
    }
}