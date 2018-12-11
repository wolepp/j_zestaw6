package generics;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class CollectionsTest {

    private static final int numberOfNumbersToAdd = 10000;
    private static final int numberOfNumbersToCheck = 500;
    private static final int numberOfNumbersToRemove = 300;
    private static ArrayList<Collection<Integer>> collections = new ArrayList<>();
    private static HashMap<String, Duration> addTestTime = new HashMap<>();
    private static HashMap<String, Duration> containsTestTime = new HashMap<>();
    private static HashMap<String, Duration> removeTestTime = new HashMap<>();
    private static Random random = new Random();

    public static void main(String[] args) {

        addCollectionToTest(new ArrayList<>());
        addCollectionToTest(new Stack<>());
        addCollectionToTest(new Vector<>());
        addCollectionToTest(new PriorityQueue<>());
        addCollectionToTest(new HashSet<>());
        addCollectionToTest(new TreeSet<>());

        testCollections();
        printResults();
    }

    private static void printResults() {
        System.out.println("Add time:");
        printSingleResult(addTestTime);
        System.out.println(System.lineSeparator()+"Contain time:");
        printSingleResult(containsTestTime);
        System.out.println(System.lineSeparator()+"Remove time:");
        printSingleResult(removeTestTime);
    }

    private static void printSingleResult(HashMap<String, Duration> results) {
        for (Map.Entry<String, Duration> result : results.entrySet()) {
            String key = result.getKey();
            Duration value = result.getValue();
            System.out.println(key + ": " + value);
        }
    }

    private static void addCollectionToTest(Collection<Integer> collection) {
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
