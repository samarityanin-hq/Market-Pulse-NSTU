package academy.backend.market_pulse.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Registry <K, V>{
    private final Map<K, V> map;

    public Registry() {
        this.map = new HashMap<>();
    }

    // Лучше использовать Objects вместо Optional т.к.
    // при проверке с Optional мы постоянно будем создавать новый объект в хипе
    public void register(K key, V value){
        Objects.requireNonNull(key, "ключ не может быть null");
        Objects.requireNonNull(value, "значение не может быть null");

        //Можно выкинуть исключение на старе если допустим будут 2 одинаковых фабрики
        if (map.containsKey(key)){
            System.out.printf("Ключ %s, уже есть в хранилище%n", key);
            //throw new IllegalStateException("Дублирование фабрик");
            return;
        }

        map.put(key, value);
    }

    public Optional<V> get(K key){
        Objects.requireNonNull(key, "ключи не может быть null");
        return Optional.ofNullable(map.get(key));
    }

}
