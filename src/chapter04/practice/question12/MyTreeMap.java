package chapter04.practice.question12;

import java.util.*;

/**
 * 4.12 使用存储类型TreeSet<Map.Entry<KeyType, ValueType>>的一个数据成员编写实现TreeMap类
 */
public class MyTreeMap<K, V> implements NavigableMap<K,V> {
    private TreeSet<Map.Entry<K, V>> data;

    @Override
    public Entry<K, V> lowerEntry(K key) {
        return null;
    }

    @Override
    public K lowerKey(K key) {
        return null;
    }

    @Override
    public Entry<K, V> floorEntry(K key) {
        return null;
    }

    @Override
    public K floorKey(K key) {
        return null;
    }

    @Override
    public Entry<K, V> ceilingEntry(K key) {
        return null;
    }

    @Override
    public K ceilingKey(K key) {
        return null;
    }

    @Override
    public Entry<K, V> higherEntry(K key) {
        return null;
    }

    @Override
    public K higherKey(K key) {
        return null;
    }

    @Override
    public Entry<K, V> firstEntry() {
        return null;
    }

    @Override
    public Entry<K, V> lastEntry() {
        return null;
    }

    @Override
    public Entry<K, V> pollFirstEntry() {
        return null;
    }

    @Override
    public Entry<K, V> pollLastEntry() {
        return null;
    }

    @Override
    public NavigableMap<K, V> descendingMap() {
        return null;
    }

    @Override
    public NavigableSet<K> navigableKeySet() {
        return null;
    }

    @Override
    public NavigableSet<K> descendingKeySet() {
        return null;
    }

    @Override
    public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
        return null;
    }

    @Override
    public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
        return null;
    }

    @Override
    public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
        return null;
    }

    @Override
    public Comparator<? super K> comparator() {
        return null;
    }

    @Override
    public SortedMap<K, V> subMap(K fromKey, K toKey) {
        return null;
    }

    @Override
    public SortedMap<K, V> headMap(K toKey) {
        return null;
    }

    @Override
    public SortedMap<K, V> tailMap(K fromKey) {
        return null;
    }

    @Override
    public K firstKey() {
        return this.data.first().getKey();
    }

    @Override
    public K lastKey() {
        return this.data.last().getKey();
    }

    @Override
    public int size() {
        return this.data.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        for (Map.Entry<K, V> entry : this.data) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (Map.Entry<K, V> entry : this.data) {
            if (entry.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        for (Map.Entry<K, V> entry : this.data) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        Map.Entry<K, V> entry = new AbstractMap.SimpleEntry<K, V>(key, value);
        V oldValue = get(key);
        this.data.add(entry);
        return oldValue;
    }

    @Override
    public V remove(Object _key) {
        K key = (K) _key;
        V value = get(key);
        Map.Entry<K, V> entry = new AbstractMap.SimpleEntry<K, V>(key, value);
        this.data.remove(entry);
        return value;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        Set<? extends Entry<? extends K, ? extends V>> entries = m.entrySet();
        this.data.addAll((Collection<? extends Entry<K, V>>) entries);
    }

    @Override
    public void clear() {
        this.data.clear();
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new TreeSet<>();
        for (Map.Entry<K, V> entry : data) {
            keySet.add(entry.getKey());
        }
        return keySet;
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        for (Map.Entry<K, V> entry : data) {
            values.add(entry.getValue());
        }
        return values;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return this.data;
    }
}
