package generics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.*;

public class CollectionsTest {

    private static ArrayList<Collection<Integer>> collections = new ArrayList<>();
    private static HashMap<String, ArrayList<Long>> addTestTimes = new HashMap<>();
    private static HashMap<String, ArrayList<Long>> containsTestTimes = new HashMap<>();
    private static HashMap<String, ArrayList<Long>> removeTestTimes = new HashMap<>();
    private static Random random = new Random();
    private static final int testRepeats = 20;
    private static final int numberOfNumbersToAdd = 10_000_000;
    private static final int numberOfNumbersToCheck = 700;
    private static final int numberOfNumbersToRemove = 700;

    public static void main(String[] args) {

        addCollectionToTest(new ArrayList<>());
        addCollectionToTest(new Stack<>());
        addCollectionToTest(new Vector<>());
        addCollectionToTest(new PriorityQueue<>());
        addCollectionToTest(new HashSet<>());
        addCollectionToTest(new TreeSet<>());
        addCollectionToTest(new LinkedList<>());

        testCollections();
        printResults();
        displayResults();
    }

    private static void displayResults() {
        for (Collection<Integer> collection: collections) {
            System.out.println("Kolekcja: " + name(collection));
            displayTimes("Add", addTestTimes.get(name(collection)));
            displayTimes("Contain", containsTestTimes.get(name(collection)));
            displayTimes("Remove", removeTestTimes.get(name(collection)));
        }
    }

    private static void displayTimes(String testType, ArrayList<Long> times) {
        for (Long time : times)
            System.out.println(testType + ": " + ((double)time)/1000000000 + "s");
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
        for (long time: getTimes(collection, testType))
            printWriter.println(time);

        printWriter.close();
    }

    private static String formatTime(Duration duration) {
        return duration.getSeconds() + "." + duration.getNano();
    }

    private static ArrayList<Long> getTimes(Collection<Integer> collection, String testType)
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
                testSingleCollection(collection, "numer "+String.valueOf(i+1));
    }

    private static void testSingleCollection(Collection<Integer> collection, String info) {
        displayTestInfo(name(collection), info);
        addTest(collection);
        containsTest(collection);
        removeTest(collection);
        collection.clear();
    }

    private static void displayTestInfo(String testName, String info) {
        if (info.isEmpty())
            System.out.println("Running " + testName + " test");
        else
            System.out.println("Running " + testName + " test; Info: " + info);
    }

    private static void addTest(Collection<Integer> collection) {
        System.out.println("Add test");
        long beforeTest = System.nanoTime();
        for (int i = 0; i < numberOfNumbersToAdd; i++) {
            collection.add(random.nextInt(numberOfNumbersToAdd));
        }
        long afterTest = System.nanoTime();

        long timeElapsed = afterTest - beforeTest;
        saveResult(collection, addTestTimes, timeElapsed);
    }

    private static void containsTest(Collection<Integer> collection) {
        System.out.println("Contain test");
        long beforeTest = System.nanoTime();
        for (int i = 0; i < numberOfNumbersToCheck; i++)
            collection.contains(random.nextInt(numberOfNumbersToAdd));
        long afterTest = System.nanoTime();

        long timeElapsed = afterTest - beforeTest;
        saveResult(collection, containsTestTimes, timeElapsed);
    }

    private static void removeTest(Collection<Integer> collection) {
        System.out.println("Remove test");
        long beforeTest = System.nanoTime();
        for (int i = 0; i < numberOfNumbersToRemove; i++)
            collection.remove(random.nextInt(numberOfNumbersToAdd));
        long afterTest = System.nanoTime();

        long timeElapsed = afterTest - beforeTest;
        saveResult(collection, removeTestTimes, timeElapsed);
    }

    private static void saveResult(Collection<Integer> collection,
                                   HashMap<String, ArrayList<Long>> results,
                                   long result) {

        System.out.println("Saving result");
        if (!results.containsKey(name(collection)))
            results.put(name(collection), new ArrayList<>());

        results.get(name(collection)).add(result);
    }

    private static String name(Object object) {
        return object.getClass().getSimpleName();
    }
}
