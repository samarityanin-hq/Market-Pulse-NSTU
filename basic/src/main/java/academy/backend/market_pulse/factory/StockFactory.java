package academy.backend.market_pulse.factory;

import academy.backend.market_pulse.model.Currency;
import academy.backend.market_pulse.model.Instrument;
import academy.backend.market_pulse.model.Stock;

import java.math.BigDecimal;

public class StockFactory implements InstrumentFactory {

    public StockFactory(){}

    @Override
    public Instrument create(String ticker, String name, Currency currency) {
        // TODO: создать Stock(ticker, name, currency, sector, dividendYield) — sector/dividendYield
        // через CLI пока не собираются, использовать значения по умолчанию.
        return new Stock(ticker, name, currency, "Дефолт", BigDecimal.ZERO);
    }
}
