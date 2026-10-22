package academy.backend.interactive.seminar_5;

import java.util.HashSet;

/// # [Object#equals(Object)], [Object#hashCode()] и как это связано с ключами в [java.util.Map]
public class Section03_MapEqualsHashCode {

    /// # Что такое `equals()`
    /// ![](./doc-files/s03_spirderman.png)
    static class EqualsSample {

        /// ## Что выведет `main()`?
        static void main() {
            String a = new String("abc");
            String b = new String("abc");

            System.out.println(a == b);
            System.out.println(a.equals(b));
            // TODO: почему?
        }

        /// ## Является ли это корректной реализацией метода `equals`? Почему?
        @Override
        public boolean equals(Object o) {
            return false;
        }

        /// ## Требования к `equals`
        static void equalsMustBe() {
            Object a = new Object();
            Object b = new Object();
            Object c = new Object();

            // Рефлексивность
            boolean r = a.equals(a); // --> true

            // Симметричность
            r = a.equals(b) == b.equals(a); // --> true

            // Транзитивность
            if (a.equals(b) && b.equals(c)) {
                r = a.equals(c); // --> true
            }

            // Непротиворечивость
            if (a.equals(b)) {
                r = a.equals(c) == b.equals(c); // --> true
            }

            // Идемпотентность
            // если объект не меняется, то и результат equals не меняется
        }
    }

    /// # Что такое `hashCode()`
    static class HashCodeSample {
        /// ## Что выведет `main()`?
        static void main() {
            String a = new String("abc");
            String b = new String("abc");

            System.out.println(a.hashCode());
            System.out.println(b.hashCode());

            Object c = new Object();
            System.out.println(c.hashCode());
            // TODO: почему?
        }

        /// ## Является ли это корректной реализацией метода `hashCode`? Почему?
        /// ![](./doc-files/s03_random.png)
        @Override
        public int hashCode() {
            return 4;
        }

        /// ## Требования к `hashCode`
        static void equalsMustBe() {
            Object a = new Object();
            Object b = new Object();

            // главное свойство
            if (a.equals(b)) {
                boolean r = a.hashCode() == b.hashCode(); // --> true
            }
        }
    }

    /// # И зачем это нужно?
    /// ![](./doc-files/s03_hashmap.png)
    static class IZachemEtoNuzhno {

        static void main() {
            HashSet<Integer> set = new HashSet<>();

            set.add(100);
            set.add(116);
            set.add(132);
            set.add(148);
            set.add(101);
            set.add(10000001);
            set.add(10000017);
            System.out.println(set);
        }

        /// ![](./doc-files/s03_question.png)
        int question;
    }



}
