package academy.backend.market_pulse.service;

import academy.backend.market_pulse.model.Quote;

import java.util.Optional;

public interface QuoteSource {
    public Optional<Quote> fetch(String ticker);
}
