package academy.backend.market_pulse.factory;

import academy.backend.market_pulse.model.Bond;
import academy.backend.market_pulse.model.Currency;
import academy.backend.market_pulse.model.Instrument;

import java.math.BigDecimal;
import java.util.Objects;

public class BondFactory implements InstrumentFactory {

    public BondFactory(){}

    @Override
    public Instrument create(String ticker, String name, Currency currency) {
        // TODO: создать Bond(ticker, name, currency, couponRate, maturityYear) — couponRate/
        // maturityYear через CLI пока не собираются, использовать значения по умолчанию.

        return new Bond(ticker, name, currency, new BigDecimal(0), 1);
    }
}
