package academy.backend.market_pulse.factory;

import academy.backend.market_pulse.model.Currency;
import academy.backend.market_pulse.model.Etf;
import academy.backend.market_pulse.model.Instrument;

import java.util.Objects;

public class EtfFactory implements InstrumentFactory {

    public EtfFactory(){}

    @Override
    public Instrument create(String ticker, String name, Currency currency) {
        // TODO: создать Etf(ticker, name, currency, trackingIndex) — trackingIndex через CLI пока
        // не собирается, использовать значение по умолчанию.
        return new Etf(ticker, name, currency, "default");
    }
}
