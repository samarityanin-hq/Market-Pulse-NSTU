package academy.backend.market_pulse.service;

import academy.backend.market_pulse.model.Quote;

import java.util.Map;
import java.util.Optional;

/**
 * Заглушка внешнего источника котировок.
 */
public class FakeQuoteSource implements QuoteSource {

    private final Map<String, Quote> data;

    public FakeQuoteSource(Map<String, Quote> data) {
        this.data = data;
    }

    @Override
    public Optional<Quote> fetch(String ticker) {
        System.out.println("-- Запрошена котировка:" + ticker);
        return Optional.ofNullable(data.get(ticker.toUpperCase()));
    }
}

