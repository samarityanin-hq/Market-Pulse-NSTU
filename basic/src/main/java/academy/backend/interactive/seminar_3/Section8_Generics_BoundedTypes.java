package academy.backend.interactive.seminar_3;

import academy.backend.interactive.seminar_3.animals.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/// # Bounded Types
/// ![Bounded](./doc-files/s8_bounded.png)
public class Section8_Generics_BoundedTypes {

    /// ## При объявлении дженеричных классов/методов доступен только `extends`
    static class OnlyDogs<T extends Dog> {
        private final List<T> dogs = new ArrayList<>();

        public OnlyDogs(T dog) {
            this.dogs.add(dog);
        }

        /// ## `extends` позволяет "уточнить" тип, давая доступ к членам указанного класса
        <P extends Parrot> String whatSize(P p) {
            return p.size();
        }

        public static void main() {
            OnlyDogs<Labrador> onlyLabradors = new OnlyDogs<>(new Labrador());  // БЕЗОПАСНО
//            OnlyDogs<Labrador> onlyChihuahua = new OnlyDogs<>(new Chihuahua()); // НЕ КОМПИЛИРУЕТСЯ
//
            System.out.println(onlyLabradors.whatSize(new Macaw()));
            System.out.println(onlyLabradors.whatSize(new Senegal()));
        }
    }

    /// # Wildcards
    /// ![Wildcards](./doc-files/s8_wildcards.png)
    static class Wildcards {

        /// Совместим с `List<Dog>` и `List<Labrador>`
        static List<? extends Dog> dogs = List.of(new Labrador(), new Chihuahua() /*, new Macaw() */);

        /// Совместим с `List<Dog>` и `List<Object>`
        static List<? super Dog> dogsAndAncestors = List.of(new Labrador(), new Macaw());

        public static void main(String... args) {
            dogs = List.<Labrador>of(new Labrador() /*, new Chihuahua() */);

            // НЕ КОМПИЛИРУЕТСЯ, т.к. не совместим
//            dogsAndAncestors = List.<Parrot>of(new Macaw());

            // КОМПИЛИРУЕТСЯ, т.к. Dog наследуется от Object
            dogsAndAncestors = List.<Object>of(new Macaw());
        }

        /// ## Зачем нужен `? super ...`?
        static int compareParrots(Comparator<? super Parrot> comparator, Parrot a, Parrot b) {
            return comparator.compare(a, b);
        }

        static class AnimalComparator implements Comparator<Animal> {
            @Override
            public int compare(Animal o1, Animal o2) {
                return o1.getLegCount() - o2.getLegCount();
            }
        }

        /// Использование `Comparator<? super Parrot>` позволяет использовать любой
        /// `Comparator`, который совместим с `Parrot`, включая `AnimalComparator`,
        /// который пригоден для всех видов животных.
        static int example() {
            return compareParrots(new AnimalComparator(), new Senegal(), new Macaw());
        }
    }

    /// # Мнемоническое правило PECS
    /// ![Pecs](./doc-files/s8_pecs.png)
    ///
    /// ## Термины - инварианты, коварианты, контрварианты
    /// ![Variant](./doc-files/s8_variant.png)
    static abstract class PECS {
        abstract Dog getDog();
        abstract Animal getAnimal();

        /// ## Ковариантность
        /// - **можем** читать - ковариант может *produce* для чтения
        /// - **не можем** писать - ковариант не может *consume*, что мы пишем
        public void covariant() {
            List<? extends Dog> l = new ArrayList<>();

            // add - это CONSUMER (ПОТРЕБИТЕЛЬ) объектов - не позволяет писать
            // А вдруг l - это List<Chihuahua>?
//            l.add(new Object());
//            l.add(getAnimal());
//            l.add(getDog());
//            l.add(new Labrador());

            // get - это PRODUCER (ПРОИЗВОДИТЕЛЬ) объектов - позволяет читать
            // В коллекции точно "зарыта собака" - а это и Animal, и Object
            Object   o1 = l.getFirst();
            Animal   o2 = l.getFirst();
            Dog      o3 = l.getFirst();
//            Labrador o4 = l.getFirst(); // компилятор не знает, есть ли Labrador в коллекции
        }

        /// ## Контравариантность
        /// - **не можем** читать - контравариант не может *produce* для чтения
        /// - **можем** писать - контравариант может *consume*, что мы пишем
        public void contravariant() {
            List<? super Dog> l = new ArrayList<>();

            // add - это CONSUMER (ПОТРЕБИТЕЛЬ) объектов - позволяет писать до границы типа
            // Мы не знаем точный тип, но "любая собака" будет подтипом этого `?`.
//            l.add(new Object());
//            l.add(getAnimal());
            l.add(getDog());
            l.add(new Labrador());

            // get - это PRODUCER (ПРОИЗВОДИТЕЛЬ) объектов - не позволяет читать, кроме Object
            // Это может быть List<Dog>, List<Animal>, хоть List<Object>
            Object   o1 = l.getFirst();
//            Animal   o2 = l.getFirst();
//            Dog      o3 = l.getFirst();
//            Labrador o4 = l.getFirst();
        }
    }
}
