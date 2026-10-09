package academy.backend.interactive.seminar_3;

import academy.backend.market_pulse.model.Instrument;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/// # Optional
/// ![Optional](./doc-files/s9_intro.png)
///
/// > Создание `null` - это ошибка на миллиард долларов.
///
/// -- Тони Хоар, создатель языка ALGOL
public class Section9_Optional {


    /// ## Как мы живём с `null`
    static class NullWorld {
        public static String getStringNullable(int x) {
            if (x == 0) {
                return null;
            } else {
                return "a number";
            }
        }

        public static void main(String... args) {
            final String s = getStringNullable(args.length);
            System.out.println("Длина строки: " + s.length()); // это придётся переписать в кучу кода
        }
    }

    /// ## Как мы живём с `Optional`
    static class OptionalWorld {
        public static Optional<String> getStringOptional(int x) {
            if (x == 0) {
                return Optional.empty();
            } else {
                return Optional.of("a number");
            }
        }

        public static void main(String... args) {
            final Optional<String> s = getStringOptional(args.length);

            // TODO-SHOW: как это будет выглядеть с Optional?
//            System.out.println("Длина строки: " + что?);
        }
    }

    /// ## Примеры использования `Optional`
    static void optionalExamples() {

        // Создание: .of / .ofNullable
        Optional<String> nonNullValue = Optional.of("a string");  // строгое не-null
        Optional<String> valueOrNull = Optional.ofNullable(null); // допустим null
        Optional<String> empty = Optional.empty();                      // пустой Optional, т.е. null

        // Проверка
        boolean b = valueOrNull.isPresent(); // проверка на наличие
        b = valueOrNull.isEmpty();           // проверка на отсутствие

        // Получение
        String s = nonNullValue.get();                   // выбросит NoSuchElementException, если null
        s = valueOrNull.orElse("plan B");          // отдаст значение или вернёт значение orElse
        s = valueOrNull.orElseGet(Section9_Optional::f); // отдаст значение или вызовет метод с выдачей значения

        // Впадание в панику
        s = valueOrNull.orElseThrow(); // выбросит NoSuchElementException, если null
        s = valueOrNull.orElseThrow(() -> new RuntimeException("АААА!!! ТУТ NULL!!!")); // выбросит созданное исключение

        // Условное выполнение
        valueOrNull.ifPresent(x -> System.out.println(x)); // выполнит над значением, если есть
        valueOrNull.ifPresentOrElse(x -> System.out.println(x), () -> System.out.println("Ничего не произошло")); // ... либо выполнит другую операцию

        // Преобразование
        Optional<Integer> l = valueOrNull.map(String::length); // зачем вытаскивать значение, если можно продолжить работу с Optional?
        Optional<String> filteredString = valueOrNull.filter(x -> x.equals("строка")); // даже можно фильтровать

        // TODO-SHOW: образование цепочки вызовов
    }

    static String f() {
        System.out.println("Зачем меня вызвали?");
        return "plan C";
    }



}
