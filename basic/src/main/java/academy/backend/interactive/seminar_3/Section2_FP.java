package academy.backend.interactive.seminar_3;

import java.util.ArrayList;
import java.util.List;

/// # ФП - Функциональная парадигма
/// ![Титул](./doc-files/s2_entry.png)
///
/// # Примеры языков с ФП
///
/// ![Примеры языков](./doc-files/s2_fp_langs.png)
///
/// # Принципы ФП
///
/// ## Pure functions
///
/// ![Чистые функции](./doc-files/s2_fp_principle_1.png)
///
/// Пример на Haskell:
/// ```haskell
/// -- Объявление типа функции
/// rectangleArea :: Double -> Double -> Double
///
/// -- Сама реализация функции
/// rectangleArea width height = width * height
/// ```
///
/// ## Immutability
/// ![Иммутабельность](./doc-files/s2_fp_principle_2.png)
///
/// Пример на Scala:
/// ```scala
/// // Описываем неизменяемую структуру данных
/// case class User(name: String, email: String, age: Int)
///
/// // 1. Создаем исходного пользователя
/// val originalUser = User("Алексей", "alex@example.com", 25)
///
/// // 2. Нам нужно обновить email.
/// // Вместо изменения originalUser.email = "new@ex.com" (что вызовет ошибку),
/// // мы копируем объект с измененным полем:
/// val updatedUser = originalUser.copy(email = "alex_new@example.com")
/// ```
/// ## First-class citizen
/// ![First-class](./doc-files/s2_fp_principle_3.png)
///
/// Пример на Lisp:
/// ```lisp
/// ;; Обычная функция, которая удваивает число
/// (defun double-it (x)
///   (* x 2))
///
/// ;; Функция высшего порядка. Она принимает другую функцию (fn) и значение (val)
/// (defun apply-twice (fn val)
///   (funcall fn (funcall fn val))) ; применяет fn к результату fn
///
/// ;; Вызов: передаем функцию double-it (используя синтаксис #' для ссылки на функцию)
/// (print (apply-twice #'double-it 3))
/// ;; Результат: 12 (сначала 3 * 2 = 6, затем 6 * 2 = 12)
/// ```
///
/// ## Декларативный подход
/// ![Declarative](./doc-files/s2_fp_principle_4.png)
///
/// Пример на Scala:
/// ```scala
/// val numbers = List(1, 2, 3, 4, 5, 6)
///
/// // Императивный стиль
/// var sum = 0                 // Изменяемое состояние (плохо для ФП)
/// for (n <- numbers) {        // Явный цикл
///   if (n % 2 == 0) {         // Условие фильтрации
///     sum += n * n            // Мутация данных
///   }
/// }
///
/// // Декларативный стиль 1
/// val result = numbers
///   .filter(n => n % 2 == 0)  // Оставляем только четные
///   .map(n => n * n)          // Возводим каждое в квадрат
///   .sum                      // Считаем сумму всего, что осталось
///
/// // Деларативный стиль 2 (scala-special)
/// val resultWithFor = for {
///   n <- numbers if n % 2 == 0 // Выбираем n из списка, если оно четное
/// } yield n * n                // Генерируем новое значение (квадрат n)
///
/// val result = resultWithFor.sum
/// ```
///
/// # А как же Java?
public class Section2_FP {

    /******************************/

    /// # Чистые функции
    /// Помним, что в Java методы, а не функции. Функции, как в ФП языках, будут дальше.
    /// Но методы тоже могут быть чистыми или грязными.
    private static int myValue;

    public static void dirtyIncrement() {
        myValue++;
    }

    /// # Функции (методы) настолько чистые, насколько сами напишите
    ///
    /// - проще проверить;
    /// - гарантирует, что вызов не изменит состояние системы;
    public static int pureIncrement(final int value) {
        return value + 1;
    }

    /******************************/

    /// # Неизменяемость
    /// Мутабельный класс. Его состояние меняет любой.
    static class MutablePoint {
        public int x;
        private int y;

        /// "Грязные" методы
        public void moveRight(int distance) {
            x += distance;
        }
    }

    ///  Иммутабельный класс. Язык гарантирует, что состояние никто не поменяет.
    static class ImmutablePoint {
        public final int x;
        private final int y;

        public ImmutablePoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        ///  "Чистые" методы
        public ImmutablePoint moveRight(int distance) {
            return new ImmutablePoint(x + distance, y);
        }
    }

    /// Мутабельная коллекция
    List<String> mutableWords = new ArrayList<>();

    /// Немутабельная коллекция
    /// Вызов `add` выкинет ошибку.
    List<String> immutableWords = List.of("1", "2", "3");


}
