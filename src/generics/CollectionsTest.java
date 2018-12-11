package generics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class CollectionsTest {

    private static final int numberOfNumbersToAdd = 25000000;
    private static final int numberOfNumbersToCheck = 500;
    private static final int numberOfNumbersToRemove = 500;
    private static ArrayList<Collection<Integer>> collections = new ArrayList<>();
    private static HashMap<String, ArrayList<Duration>> addTestTimes = new HashMap<>();
    private static HashMap<String, ArrayList<Duration>> containsTestTimes = new HashMap<>();
    private static HashMap<String, ArrayList<Duration>> removeTestTimes = new HashMap<>();
    private static Random random = new Random();
    private static final int testRepeats = 20;

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
        for (Collection<Integer> collection: collections)
            try {
                writeTestResultsToFile(collection, "add");
                writeTestResultsToFile(collection, "contains");
                writeTestResultsToFile(collection, "remove");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalTestType e) {
                System.out.println("Nie istnieje typ testu: " + e.getTestType());
            }
    }

    private static void writeTestResultsToFile(Collection<Integer> collection, String testType)
            throws IllegalTestType, IOException {

        FileWriter fileWriter = new FileWriter(generateFilename(collection, testType));
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (Duration time: getTimes(collection, testType))
            printWriter.println(formatTime(time));

        printWriter.close();
    }

    private static String formatTime(Duration duration) {
        return duration.getSeconds() + "." + duration.getNano();
    }

    private static ArrayList<Duration> getTimes(Collection<Integer> collection, String testType)
            throws IllegalTestType {

        switch (testType.toLowerCase()) {
            case "add":
                return addTestTimes.get(name(collection));
            case "contains":
                return containsTestTimes.get(name(collection));
            case "remove":
                return removeTestTimes.get(name(collection));
            default:
                throw new IllegalTestType(testType);
        }
    }

    private static String generateFilename(Collection<Integer> collection, String testType) {
        return testType + name(collection) + "Times.txt";
    }

    private static void addCollectionToTest(Collection<Integer> collection) {
        collections.add(collection);
    }

    private static void testCollections() {
        for (int i = 0 ; i < testRepeats; i++)
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
        System.out.println(timeElapsed);
        saveResult(collection, removeTestTimes, timeElapsed);
    }

    private static void saveResult(Collection<Integer> collection,
                                   HashMap<String, ArrayList<Duration>> results,
                                   Duration result) {

        if (!results.containsKey(name(collection)))
            results.put(name(collection), new ArrayList<>());

        results.get(name(collection)).add(result);
    }

    private static String name(Object object) {
        return object.getClass().getSimpleName();
    }
}
