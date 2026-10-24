package academy.backend.interactive.seminar_5;

import academy.backend.market_pulse.cli.AddCommand;
import academy.backend.market_pulse.cli.ListCommand;
import academy.backend.market_pulse.cli.MarketPulseCli;
import academy.backend.market_pulse.cli.SearchCommand;
import academy.backend.market_pulse.model.Bond;
import academy.backend.market_pulse.model.Currency;
import academy.backend.market_pulse.model.Etf;
import academy.backend.market_pulse.model.Stock;
import academy.backend.market_pulse.repository.InstrumentRepository;
import picocli.CommandLine;

import java.math.BigDecimal;

/// # Демонстрационная практика
///
/// ## Переделка репозиториев инструментов - `Map`
/// Переведём [academy.backend.market_pulse.repository.InstrumentRepository] на [java.util.LinkedHashMap].
///
/// ## Новая команда `watch` - `Set`
/// - `watch TICKERNAME` - добавляет тикер в список отслеживаемых
/// - если уже есть в списке, то сообщает, что тикер уже отслеживается
/// - `watch` - выводит список всех отслеживаемых тикеров
///
public class Section04_Demo {

    /// Самопроверка
    static void main() {
        InstrumentRepository repository = new InstrumentRepository();
        fillRepository(repository);

        CommandLine cli = new CommandLine(new MarketPulseCli())
                .addSubcommand(new SearchCommand(repository))
                .addSubcommand(new AddCommand(repository))
                .addSubcommand(new ListCommand(repository));
        // TODO: добавить WatchCommand(watchList)

        cli.execute("watch", "SBER");
        cli.execute("watch", "SU26238RMFS4");
        cli.execute("watch", "SBER");
        cli.execute("watch");
    }

    private static void fillRepository(InstrumentRepository repository) {
        Stock sber = new Stock("SBER", "Сбербанк", Currency.RUB,
                "Financials", new BigDecimal("6.5"));
        Bond ofz = new Bond("SU26238RMFS4", "ОФЗ-26238", Currency.RUB,
                new BigDecimal("7.1"), 2035);
        Etf tmos = new Etf("TMOS", "Тинькофф iMOEX", Currency.RUB, "MOEX");

        repository.add(sber);
        repository.add(ofz);
        repository.add(tmos);
    }
}
