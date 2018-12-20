package generics;

public class OrderedPair<K extends Comparable<? super K>, V>
        implements Comparable <OrderedPair<K, V>>, Pair<K, V> {

    private K key;
    private V value;

    public OrderedPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public int compareTo(OrderedPair<K, V> o) {
        return this.key.compareTo(o.getKey());
    }
}
