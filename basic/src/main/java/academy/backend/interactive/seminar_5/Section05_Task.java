package academy.backend.interactive.seminar_5;

import academy.backend.market_pulse.cli.AddCommand;
import academy.backend.market_pulse.cli.ListCommand;
import academy.backend.market_pulse.cli.MarketPulseCli;
import academy.backend.market_pulse.cli.SearchCommand;
import academy.backend.market_pulse.factory.InstrumentFactories;
import academy.backend.market_pulse.model.*;
import academy.backend.market_pulse.repository.InstrumentRepository;
import academy.backend.market_pulse.service.FakeQuoteSource;
import academy.backend.market_pulse.service.QuoteSource;
import picocli.CommandLine;

import java.math.BigDecimal;
import java.util.Map;

/// # Да, вы этого так ждали - самостоятельная работа!
///
/// ## Новая команда `quote`
///
/// - `quote TICKER` или `quote TICKER1 TICKER2` выводит котировки по перечисленным тикерам;
/// - источник котировок [academy.backend.market_pulse.service.QuoteSource]. Используем фейковую реализацию [academy.backend.market_pulse.service.FakeQuoteSource].
/// - реализация логики команды в новом классе `QuoteService`;
/// - `QuoteService` должен кэшировать результаты - для каждого тикера обращаемся в QuoteSource **не более** одного раза за всю работу программы;
/// - для неизвестных тикеров должно выводиться соответствующее сообщение;
///
/// **Рекомендации по реализации `QuoteService`**
/// - использование `Queue` для создания очереди из тикеров для запроса в `QuoteSource`;
/// - использование `Map` для кэширования;
///
/// # Не забываем обратную связь
/// [Ссылка на опрос](https://polls.tbank.ru/s/cmtic4h3g000d0dwp235083y9)
///
/// ![](./doc-files/s05_outro.png)
public class Section05_Task {

    /// Самопроверка
    static void main() {
        InstrumentRepository repository = new InstrumentRepository();
        QuoteSource quoteSource = new FakeQuoteSource(sampleQuotes());

        CommandLine cli = new CommandLine(new MarketPulseCli())
                .addSubcommand(new SearchCommand(repository))
                .addSubcommand(new AddCommand(repository))
                .addSubcommand(new ListCommand(repository));
        // TODO: добавить QuoteCommand(quoteService)

        cli.execute("quote", "SBER");
        cli.execute("quote", "SBER", "VTB", "VTB");
        cli.execute("quote", "VTB", "UNKNOWN");
        cli.execute("quote", "GAZP");
    }

    private static Map<String, Quote> sampleQuotes() {
        Instrument sber = InstrumentFactories.create("STOCK", "SBER", "Сбербанк", Currency.RUB);
        Instrument gazp = InstrumentFactories.create("STOCK", "GAZP", "Газпром", Currency.RUB);
        Instrument vtb = InstrumentFactories.create("STOCK", "VTB", "ВТБ", Currency.RUB);
        return Map.of(
                "SBER", new Quote(sber, new BigDecimal("250.00"), new BigDecimal("1.20")),
                "GAZP", new Quote(gazp, new BigDecimal("120.50"), new BigDecimal("-0.80")),
                "VTB", new Quote(vtb,   new BigDecimal("180.77"), new BigDecimal("2.01"))
        );
    }
}
