package academy.backend.interactive.seminar_3;

import java.util.ArrayList;
import java.util.List;

/// # Стирание типов - главное ограничение дженериков
/// ![Стирание](./doc-files/s7_type_erasure.png)
public class Section7_Generics_TypeErasure {

    /// # Как это выглядит
    public static void main(String... args) {
        // "Безопасный" список строк
        List<String> strList = new ArrayList<>();
        strList.add("a");
        strList.add("b");
        printStrs(strList);

        // Так НЕЛЬЗЯ - НЕ КОМПИЛИРУЕТСЯ
//        List<Integer> another = strList;

        // Но можно так...

        // Недженеричный список (привет из Java 1.2)
        List list = strList;              // законом не запрещено
        list.add(Double.valueOf(1.567));  // теперь в нашем "безопасном" строковом strList - Double
        printObjects(list);    // РАБОТАЕТ
        printObjects(strList); // РАБОТАЕТ
        printStrs(strList);  // КОМПИЛИРУЕТСЯ, НО НЕ РАБОТАЕТ

        // И теперь можно
        List<Integer> intList = list;
        intList.add(Integer.valueOf(45));
//        printTypes(intList); // хотя бы такое не компилируется
    }

    static void printStrs(List<String> strs) {
        for (String s : strs) {
            System.out.println(s);
        }
    }

    static void printObjects(List<String> strs) {
        for (Object o : strs) {
            System.out.println(o.getClass() + ": " + o);
        }
    }

//    /// ТАКОЕ НЕ КОМПИЛИРУЕТСЯ - перегрузка не работает, т.к. это просто List
//    static void printTypes(List<Integer> strs) {
//        for (Object o : strs) {
//            System.out.println(o.getClass() + ": " + o);
//        }
//    }
}
