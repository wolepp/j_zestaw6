package generics;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Test {

    final static private int Nadd     = 30000;
//    final static private int Nadd     = 3000000;
    final static private int Ncontain = 20000;
//    final static private int Nremove  = 7000;
    final static private int Nremove  = 70;

    private static ArrayList<Integer> addList = new ArrayList<>();
    private static ArrayList<Integer> containList = new ArrayList<>();
    private static ArrayList<Integer> removeList = new ArrayList<>();

    //TODO: no bo to nie może być tak, że contain sie robi Ncontain*Nadd xDDD
    //TODO: chyba osobne tablice bedo potrzebne
    //TODO: NO I CHYBA LEPIEJ W TYCH TESTACH OD RAZU WSTAWIAC RANDOM
    public static void main(String[] args) {

        Random random = new Random();

        // generowanie liczb do testowania
        for (int i = 0; i < Nadd; i++)
            addList.add(random.nextInt(Nadd*2));

        for (int i = 0; i < Ncontain; i++)
            containList.add(random.nextInt(Ncontain*2));

        for (int i = 0; i < Nremove; i++)
            removeList.add(random.nextInt(Nremove*2));

        // mapy na wyniki kolekcji
        HashMap<String, Duration> addTime = new HashMap<>();
        HashMap<String, Duration> containsTime = new HashMap<>();
        HashMap<String, Duration> removeTime = new HashMap<>();

        // kolekcja z testowanymi kolekcjami
        ArrayList<Collection<Integer>> kolekcje = new ArrayList<>();
        kolekcje.add(new ArrayList<>());
        kolekcje.add(new Stack<>());
        kolekcje.add(new Vector<>());
        kolekcje.add(new PriorityQueue<>());
        kolekcje.add(new HashSet<>());
        kolekcje.add(new TreeSet<>());

        // testowanie kolekcji
        for (Collection<Integer> kolekcja: kolekcje) {
            String nazwa = kolekcja.getClass().getSimpleName();
            addTime.put(nazwa, addTest(kolekcja));
            containsTime.put(nazwa, containsTest(kolekcja));
            removeTime.put(nazwa, removeTest(kolekcja));
        }

        // wypisanie wyników
        System.out.println("Add Time:");
        for(Map.Entry<String, Duration> wynik: addTime.entrySet()) {
            String key = wynik.getKey();
            Duration value = wynik.getValue();
            System.out.println(key+": "+value);
        }
        System.out.println();

        System.out.println("Contain Time:");
        for(Map.Entry<String, Duration> wynik: containsTime.entrySet()) {
            String key = wynik.getKey();
            Duration value = wynik.getValue();
            System.out.println(key+": "+value);
        }
        System.out.println();

        System.out.println("remove time:");
        for(Map.Entry<String, Duration> wynik: removeTime.entrySet()) {
            String key = wynik.getKey();
            Duration value = wynik.getValue();
            System.out.println(key+": "+value);
        }
    }

    private static Duration addTest(Collection<Integer> kontener) {
        Instant start = Instant.now();
        for (int i = 0; i < Nadd; i++)
            kontener.add(addList.get(i));
        Instant end = Instant.now();
        return Duration.between(start, end);
    }

    private static Duration containsTest(Collection<Integer> kontener) {
        Instant start = Instant.now();
        for (int i = 0; i < Ncontain; i++)
            kontener.contains(containList.get(i));
        Instant end = Instant.now();
        return Duration.between(start, end);
    }

    private static Duration removeTest(Collection<Integer> kontener) {
        Instant start = Instant.now();
        for (int i = 0; i < Nremove; i++)
            kontener.remove(removeList.get(i));
        Instant end = Instant.now();
        return Duration.between(start, end);
    }
}
