package academy.backend.interactive.seminar_3;

import java.util.ArrayList;
import java.util.List;

/// # Итак, Дженерики
/// ![Интро](./doc-files/s6_intro.png)
///
/// ## 3-й вид полиморфизма
///
/// Универсальная реализация, чтобы не дублировать код под разные типы.
public class Section6_Generics_Intro {

    /// ## Можно создавать дженеричные классы
    static class GenericClass<T, T2> {
        T f1;
        T2[] f2;

        public GenericClass(T f1) {
            this.f1 = f1;
        }

        public T2 getField() {
            return f2[0];
        }
    }

    /// ## Можно создавать дженеричные методы - статические и экземплярные
    static <RET_TYPE> RET_TYPE genericStaticMethod(int a, RET_TYPE b) {
        return b;
    }

    <U> int genericMethod(U... args) {
        return args.length;
    }

    static void useGenericMethod() {
        // Обычно, указание типа для дженеричного метода избыточно
        String a = Section6_Generics_Intro.<String>genericStaticMethod(30, "x");
        String b = genericStaticMethod(10, "f");
    }

    /// ## Плюсы от дженериков
    static void pluses() {
        // Пишешь один раз!

        List<String> strList = List.of("a", "b");
        List<Integer> intList = List.of(1, 2, 3);

        // Типовая безопасность!

        List<String> str = new ArrayList<>();
        str.add("a");              // МОЖНО
        String s = str.getFirst(); // МОЖНО

//        str.add(new Integer(1));    // ОШИБКА!
//        Integer i = str.getFirst(); // ОШИБКА!
    }

    /// ## Но не всё идеально
    /// - не работает с массивами - нельзя `T[]`;
    /// - не работает с примитивными типами - нельзя `List<int>`
    /// - не работает со static - нельзя `public static T t`;
    /// - И главное: **стирание типов** (type erasure)
    public static void main() {

    }

}
