package academy.backend.interactive.seminar_3;

/// # Функции - сущности первого порядка.
public class Section3_FP_FirstClassCitizen {

    /// ## "Функция" - реализация **функционального интерфейса**.
    interface Summator {
        int sum(int a, int b);
    }

    interface Multiplicator {
        int mul(int x, int y);
    }

    /// ## Передача функции, как аргумента - всё, как в ООП.
    public static int useFun(Summator f, int arg) {
        final int v = 10;
        int sum = f.sum(v, arg);
        System.out.println("Результат работы: " + sum);
        return sum;
    }

    public static void main(String... args) {
        // Варианты вызова.
        // ООП-стиль

        final var fOOP = new Summator() {
            @Override
            public int sum(int a, int b) {
                return a + b;
            }
        };
        useFun(fOOP, 1);

        // ФП-стиль

        // TODO-SHOW: передача статического метода
        // TODO-SHOW: передача метода экземпляра
        // TODO-SHOW: передача лямбды
        // TODO-SHOW: передача лямбды с захватом переменной
        // TODO-SHOW: универсальный интерфейсы
    }

    public static int mySum(int arg1, int arg2) {
        // потому что кто нам запретит?
        return arg1 - arg2;
    }

    static class SomeClass {
        private final int addend;

        public SomeClass(int addend) {
            this.addend = addend;
        }

        public int formula(int x, int y) {
            return addend + x - y;
        }
    }
}
