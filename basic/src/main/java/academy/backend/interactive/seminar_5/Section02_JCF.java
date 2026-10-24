package academy.backend.interactive.seminar_5;

import java.util.*;

/// # Java Collection Framework
///
/// ![](./doc-files/s02_intro_meme.png)
///
/// ## Основные интерфейсы
///
/// ![](./doc-files/s02_jcf_interfaces.png)
///
/// У каждого интерфейса разные реализации для разных ситуаций.
///
///
public class Section02_JCF {

    /// ## [java.util.Collection]
    ///
    /// Декларирует общие методы для перечислимых коллекций:
    /// - добавление/удаление элемента по значению;
    /// - размер;
    /// - наследуется от [Iterable];
    class CollectionSample {
        Collection<Object> collection;

        void sample() {
            Object a = new Object();

            // добавление
            boolean wasChanged = collection.add(a);

            // удаление
            boolean wasRemoved = collection.remove(a);

            // итерирование
            for (Object o : collection) {
                System.out.println(o);
            }
        }
    }

    /// ## [java.util.List]
    ///
    /// Упорядоченный по числовому индексу список элементов:
    /// - каждый элемент доступен по индексу;
    /// - может содержать дубликаты;
    class ListSample {
        List<Object> list;

        void sample() {
            // получение
            Object a = list.get(10);
            Object b = list.getFirst();
            Object c = list.getLast();

            // добавляем дубликаты
            list.add(a); // вернёт true
            list.add(a); // тоже вернёт true

            // часто используемые реализации
            list = new ArrayList<>(); // стандарт де-факто
            list = new LinkedList<>(); // связанный список, оптимален для частых удалений/вставок
        }
    }

    /// ## [java.util.Set]
    ///
    /// Множество уникальных элементов:
    /// - доступ по индексу невозможен;
    /// - не может содержать дубликаты;
    class SetSample {
        Set<Object> set;

        void sample() {
            // нет способа получить элемент напрямую
            // Object a = set.get() - такого не существует

            // дубликаты добавить не получится
            Object a = new Object();
            set.add(a); // вернёт true
            set.add(a); // вернёт false

            // часто используемые реализации
            set = new HashSet<>(); // стандарт де-факто
            set = new TreeSet<>(); // автоматическая сортировка
            set = new LinkedHashSet<>(); // сохранение порядка вставки
        }
    }

    /// ## [java.util.Queue]
    ///
    /// Очередь с реализацией принципа FIFO (first-in-first-out):
    /// - элементы упорядочены по порядку добавления;
    /// - могут быть дубликаты;
    class QueueSample {
        Queue<Object> queue;

        void sample() {
            Object a = new Object();
            Object b = new Object();
            Object c = new Object();

            // добавление в порядке очереди
            queue.add(a);
            queue.add(b);
            queue.add(c);

            Object polled = queue.poll(); // отдаст объект "a" и уберёт из очереди
            Object peeked = queue.peek(); // отдаст объект "b", но оставит в очереди

            // дубликаты разрешены
            queue.add(a); // вернёт true
            queue.add(a); // вернёт true

            // часто используемые реализации
            queue = new ArrayDeque<>(); // аналог ArrayList для Queue
            queue = new LinkedList<>(); // аналог LinkedList для Queue
        }
    }

    /// ## [java.util.Map]
    ///
    /// Таблица пар «ключ-значение»:
    /// - все ключи уникальны;
    /// - значения могут дублироваться;
    /// - можно получить коллекции отдельно ключей, значений или пар «ключ-значение»
    class MapSample {
        Map<String, Object> map;

        void sample() {
            String key = "капибара";
            Object a = new Object();
            Object b = new Object();

            // уникальность ключей
            Object previous = map.put(key, a); // "previous" получит null
            previous = map.put(key, b); // перетёрли значение для "key" с "a" на "b", "previous" содержит "a"

            // дублирование значений
            map.put("Ключ 1", a);
            map.put("Ключ 2", a);

            // отдельные коллекции ключей, значений и пар ключ-значение
            Set<String> keys = map.keySet();
            Collection<Object> values = map.values();
            Set<Map.Entry<String, Object>> entries = map.entrySet();

            // часто используемые реализации
            map = new HashMap<>(); // стандарт де-факто
            map = new TreeMap<>(); // автоматическая сортировка
            map = new LinkedHashMap<>(); // сохранение порядка вставки
        }
    }
}
