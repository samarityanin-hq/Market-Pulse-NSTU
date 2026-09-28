package academy.backend.market_pulse.demo;

import java.math.BigDecimal;
import java.util.List;

import academy.backend.market_pulse.model.Bond;
import academy.backend.market_pulse.model.Currency;
import academy.backend.market_pulse.model.Etf;
import academy.backend.market_pulse.model.Instrument;
import academy.backend.market_pulse.model.Quote;
import academy.backend.market_pulse.model.Stock;
import main.java.academy.backend.market_pulse.model.Portfolio;
import main.java.academy.backend.market_pulse.model.PositionDTO;
import main.java.academy.backend.market_pulse.model.User;

public class Main {

    public static void main(String[] args) {
        Stock sber = new Stock("SBER", "Сбербанк", Currency.RUB,
                "Financials", new BigDecimal("6.5"));
        Bond ofz = new Bond("SU26238RMFS4", "ОФЗ-26238", Currency.RUB,
                new BigDecimal("7.1"), 2035);
        Etf tmos = new Etf("TMOS", "Тинькофф iMOEX", Currency.RUB, "MOEX");

        // Полиморфизм подтипов: getDescription() вызывается разный для каждого
        // конкретного типа, хотя обращаемся мы к ним через общий тип Instrument.
        List<Instrument> instruments = List.of(sber, ofz, tmos);
        for (Instrument instrument : instruments) {
            System.out.println(instrument);
        }

        // Quote — агрегация: одна и та же акция может быть частью любого числа
        // котировок. getDividends() здесь корректен, т.к. цена уже известна.
        Quote sberQuote = new Quote(sber, new BigDecimal("278.50"), new BigDecimal("1.2"));
        System.out.println(sberQuote);
        System.out.println("Дивиденды по котировке: " + sberQuote.getDividends());

        // Bond и Etf дивидендов не платят — Quote.getDividends() честно
        // возвращает ZERO, не нарушая LSP (метод не объявлен в Instrument).
        Quote ofzQuote = new Quote(ofz, new BigDecimal("980.00"), new BigDecimal("-0.3"));
        System.out.println(ofzQuote);
        System.out.println("Дивиденды по котировке: " + ofzQuote.getDividends());

        // TODO: построить Portfolio, добавить позиции (sber x10, ofz x5, tmos x3)
        // и вывести список позиций.

        User user = new User("Илья");
        user.addPosition(sber, 10);
        user.addPosition(ofz, 5);
        user.addPosition(tmos, 3);
        Portfolio.Position[]  pos = user.getPositions();

        System.out.println("\nПортфель пользователя: " + user.getName());

        for (Portfolio.Position position : pos){
            System.out.println("Инструмент:" + position.getInstrument());
            System.out.println("Кол-во: " + position.getQuantity());
        }
    }
}
