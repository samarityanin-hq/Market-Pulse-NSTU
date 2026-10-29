package academy.backend.market_pulse.service;

import academy.backend.market_pulse.model.Quote;

import java.util.HashMap;
import java.util.Map;


public class QuoteService {
    private final Map<String, Quote> cache = new HashMap<>();
    private final QuoteSource source;

    public QuoteService(QuoteSource source){
        this.source = source;
    }

    public Quote getQuote(String ticker){
        return cache.computeIfAbsent(ticker, t -> source.fetch(t)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Тикер с названием: %s не найден", t))
                ));


    }


}
