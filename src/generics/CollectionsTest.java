package generics;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class CollectionsTest {

    private static final int numberOfNumbersToAdd = 1000;
    private static final int numberOfNumbersToCheck = 500;
    private static final int numberOfNumbersToRemove = 300;
    private static ArrayList<Collection<Integer>> collections = new ArrayList<>();
    private static HashMap<String, Duration> addTestTime = new HashMap<>();
    private static HashMap<String, Duration> containsTestTime = new HashMap<>();
    private static HashMap<String, Duration> removeTestTime = new HashMap<>();
    private static Random random = new Random();

    public static void main(String[] args) {

        addColectionToTest(new ArrayList<>());
        addColectionToTest(new Stack<>());
        addColectionToTest(new Vector<>());
        addColectionToTest(new PriorityQueue<>());
        addColectionToTest(new HashSet<>());
        addColectionToTest(new TreeSet<>());

        testCollections();

        System.out.println("Add time:");
        printResults(addTestTime);
        System.out.println("Contain time:");
        printResults(containsTestTime);
        System.out.println("Remove time:");
        printResults(removeTestTime);

    }

    private static void printResults(HashMap<String, Duration> results) {
        for (Map.Entry<String, Duration> wynik: addTestTime.entrySet()) {
            String key = wynik.getKey();
            Duration value = wynik.getValue();
            System.out.println(key + ": " + value);
        }
    }

    private static void addColectionToTest(Collection<Integer> collection) {
        collections.add(collection);
    }

    private static void testCollections() {
        for (Collection<Integer> collection : collections)
            testSingleCollection(collection);
    }

    private static void testSingleCollection(Collection<Integer> collection) {
        addTest(collection);
        containsTest(collection);
        removeTest(collection);
    }

    private static void addTest(Collection<Integer> collection) {

        Instant beforeTest = Instant.now();
        for (int i = 0; i < numberOfNumbersToAdd; i++)
            collection.add(random.nextInt(numberOfNumbersToAdd));
        Instant afterTest = Instant.now();

        Duration timeElapsed = Duration.between(beforeTest, afterTest);
        saveResult(collection, addTestTime, timeElapsed);
    }

    private static void containsTest(Collection<Integer> collection) {

        Instant beforeTest = Instant.now();
        for (int i = 0; i < numberOfNumbersToCheck; i++)
            collection.contains(random.nextInt(numberOfNumbersToAdd));
        Instant afterTest = Instant.now();

        Duration timeElapsed = Duration.between(beforeTest, afterTest);
        saveResult(collection, containsTestTime, timeElapsed);
    }

    private static void removeTest(Collection<Integer> collection) {

        Instant beforeTest = Instant.now();
        for (int i = 0; i < numberOfNumbersToRemove; i++)
            collection.remove(random.nextInt(numberOfNumbersToAdd));
        Instant afterTest = Instant.now();

        Duration timeElapsed = Duration.between(beforeTest, afterTest);
        saveResult(collection, removeTestTime, timeElapsed);
    }

    private static void saveResult(Collection<Integer> collection,
                                   HashMap<String, Duration> results,
                                   Duration result) {
        results.put(name(collection), result);
    }

    private static String name(Object object) {
        return object.getClass().getSimpleName();
    }
}
