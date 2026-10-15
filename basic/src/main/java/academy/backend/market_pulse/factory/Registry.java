package academy.backend.market_pulse.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class Registry <K, V>{
    // Можно пока взять обычную хэш мапу, но если целиться на реальную программу обработки рынка
    // то скорее всего придеться использовать многопоточную мапу
    // private final Map<K, V> map = new ConcurrentHashMap<>();
    private final Map<K, V> map = new HashMap<>();


    public Registry(){}

    public void register(K key, V value){
        Objects.requireNonNull(key, "ключ не может быть null");
        Objects.requireNonNull(value, "значение не может быть null");

        //Можно выкинуть исключение на старе если допустим будут 2 одинаковых фабрики
        if (map.containsKey(key)){
            System.out.printf("Ключ %s, уже есть в хранилище%n", key);
            //throw new IllegalStateException("Дублирование фабрик запрещено");
            return;
        }

        map.put(key, value);
    }

    public Optional<V> get(K key){
        Objects.requireNonNull(key, "ключи не может быть null");
        return Optional.ofNullable(map.get(key));
    }

}
