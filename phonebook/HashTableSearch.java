package phonebook;

import java.util.List;

public class HashTableSearch implements Algorithm {

    private final List<String> directory;
    private final List<String> namesToFind;
    int matchesFound = 0;
    long totalSearchTime;
    long totalCreateTime;

    HashTableSearch(List<String> directory, List<String> namesToFind) {
        this.directory = directory;
        this.namesToFind = namesToFind;
    }

    public void start() {
        System.out.println("Start searching (hash table)... ");
        PhoneBookHashTable<String> phoneBookHashTable = new PhoneBookHashTable<>(10000);
        long startTime = System.currentTimeMillis();

        for (String phoneBookEntry : directory) {
            phoneBookHashTable.put(phoneBookEntry, phoneBookEntry);
        }
        totalCreateTime = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();

        for (String name : namesToFind) {
            if (phoneBookHashTable.get(name) != null) {
                matchesFound++;
            }
        }
        totalSearchTime = System.currentTimeMillis() - startTime;
        Algorithm.printResults(matchesFound, totalCreateTime, totalCreateTime);
    }
}