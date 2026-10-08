package academy.backend.market_pulse.cli;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import academy.backend.market_pulse.filter.*;
import academy.backend.market_pulse.model.Currency;
import academy.backend.market_pulse.model.Instrument;
import academy.backend.market_pulse.repository.InstrumentRepository;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "list", description = "Список инструментов")
public class ListCommand implements Callable<Integer> {

    @Option(names = "--type", description = "Фильтр по типу инструмента")
    private String type;

    @Option(names = "--ticker", description ="Фильтр по подстроке тикера")
    private String ticker;

    @Option(names = "--currency", description = "Фильтр по валюте (RUB, USD, EUR)")
    private Currency currency;

    @Option(names = "--price-op", description = "Операция фильтра цены (GT, GE, LT, LE, EQ)")
    private PriceOp priceOp;

    @Option(names = "--price", description = "Значение для операции фильтра цены")
    private BigDecimal price;

    private final InstrumentRepository repository;
    private final FilterFactory factory;

    public ListCommand(InstrumentRepository repository, FilterFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    @Override
    public Integer call() {
        InstrumentFilter filter;
        try {
            filter = factory.buildFilter(
                    new FilterCriteria(type,
                                        currency,
                                        ticker,
                                        priceOp,
                                        price));
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            return 1;
        }

        for (Instrument instrument : repository){
            if (filter.matches(instrument)){
                System.out.println(instrument.toString());
            }
        }

        return 0;
    }
}
