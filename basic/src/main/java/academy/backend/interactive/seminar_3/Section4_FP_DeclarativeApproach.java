package academy.backend.interactive.seminar_3;

import java.util.Arrays;

/// # Декларативность
/// Как опция
public class Section4_FP_DeclarativeApproach {

    /// ## Императивно - пишем алгоритм
    /// 1. Для i от 0 до размера массива
    /// 2. Если i'ый элемент делится на 2, то
    /// 3. Добавить значение к переменной.
    ///
    /// Нигде не сказано, что мы просто хотим сумму - надо догадаться по алгоритму.
    public static int imperativeWay(int[] numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                sum += numbers[i];
            }
        }

        return sum;
    }

    /// ## Декларативно - пишем, что хотим получить
    /// 1. Для всех чисел массива
    /// 2. Что делятся на 2
    /// 3. Хотим получить сумму.
    ///
    /// TODO-ASK: особенность стримов, взятая из ФП - ленивые вычисления.
    ///
    /// Подробнее про Stream API в будущих семинарах
    public static int declarativeWay(int[] numbers) {
        // TODO-SHOW: декларативный способ
        return 0;
    }

    public static void main() {
        int[] ns = {1, 2, 3, 4, 5, 6};
        System.out.println("Императивно: " + imperativeWay(ns));
        System.out.println("Декларативно: " + declarativeWay(ns));
    }

}
