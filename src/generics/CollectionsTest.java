package generics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class CollectionsTest {

//    private static final int numberOfNumbersToAdd = 25000000;
    private static final int numberOfNumbersToAdd = 25000;
    private static final int numberOfNumbersToCheck = 500;
    private static final int numberOfNumbersToRemove = 500;
    private static ArrayList<Collection<Integer>> collections = new ArrayList<>();
    private static HashMap<String, ArrayList<Duration>> addTestTimes = new HashMap<>();
    private static HashMap<String, ArrayList<Duration>> containsTestTimes = new HashMap<>();
    private static HashMap<String, ArrayList<Duration>> removeTestTimes = new HashMap<>();
    private static Random random = new Random();
    private static final String fileName = "data.txt";

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
        try {
            writeToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile() throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("Some String");
        printWriter.printf("Product name is %s and its price is %d $", "iPhone", 1000);
        printWriter.close();
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
        collection.clear();
    }

    private static void addTest(Collection<Integer> collection) {
        Instant beforeTest = Instant.now();
        for (int i = 0; i < numberOfNumbersToAdd; i++) {
            collection.add(random.nextInt(numberOfNumbersToAdd));
        }
        Instant afterTest = Instant.now();

        Duration timeElapsed = Duration.between(beforeTest, afterTest);
        saveResult(collection, addTestTimes, timeElapsed);
    }

    private static void containsTest(Collection<Integer> collection) {
        Instant beforeTest = Instant.now();
        for (int i = 0; i < numberOfNumbersToCheck; i++)
            collection.contains(random.nextInt(numberOfNumbersToAdd));
        Instant afterTest = Instant.now();

        Duration timeElapsed = Duration.between(beforeTest, afterTest);
        saveResult(collection, containsTestTimes, timeElapsed);
    }

    private static void removeTest(Collection<Integer> collection) {
        Instant beforeTest = Instant.now();
        for (int i = 0; i < numberOfNumbersToRemove; i++)
            collection.remove(random.nextInt(numberOfNumbersToAdd));
        Instant afterTest = Instant.now();

        Duration timeElapsed = Duration.between(beforeTest, afterTest);
        saveResult(collection, removeTestTimes, timeElapsed);
    }

    private static void saveResult(Collection<Integer> collection,
                                   HashMap<String, ArrayList<Duration>> results,
                                   Duration result) {
        if (!results.containsKey(name(collection))) {
            ArrayList<Duration> times = new ArrayList<>();
            results.put(name(collection), times);
        } else {
            results.get(name(collection)).add(result);
        }
    }

    private static String name(Object object) {
        return object.getClass().getSimpleName();
    }
}
