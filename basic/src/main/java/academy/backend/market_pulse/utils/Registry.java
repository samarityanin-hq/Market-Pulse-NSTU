package academy.backend.market_pulse.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Registry<K, V> {

    private Map<K, V> storage = new HashMap<>();

    public void register(K key, V value) {
        if (storage.containsKey(key)) {
            System.out.println("Уже существует значение с ключом: " + key);
            return;
        }

        storage.put(key, value);
    }

    public Optional<V> get(K key) {
        return Optional.ofNullable(storage.get(key));
    }
}
