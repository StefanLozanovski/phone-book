package phonebook;

public class PhoneBookHashTable<T> {

    private final int size;
    private final PhoneBookEntry[] table;

    PhoneBookHashTable(int size) {
        this.size = size;
        table = new PhoneBookEntry[size];
    }

    public void put(String key, T value) {
        int index = findKey(key);

        if (index == -1) {
            return;
        }
        table[index] = new PhoneBookEntry<>(key, value);
    }

    public T get(String key) {
        int index = findKey(key);

        if (index == -1 || table[index] == null) {
            return null;
        }
        return (T) table[index].getValue();
    }

    private int findKey(String keyString) {
        int key = createKeyFromName(keyString);
        int hash = key % size;

        while (table[hash] != null && createKeyFromName(table[hash].getKey()) != key) {
            hash = (hash + 1) % size;

            if (hash == key % size) {
                return -1;
            }
        }
        return hash;
    }

    private int createKeyFromName(String keyString) {
        int sum = 0;
        char[] key = keyString.toCharArray();

        for (char c : key) {
            sum += c;
        }
        sum += key[0] * key[key.length - 1];
        return sum;
    }
}

class PhoneBookEntry<T> {

    private final String key;
    private final T value;

    PhoneBookEntry(String key, T value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }
}