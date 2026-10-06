package academy.backend.market_pulse.filter;

import academy.backend.market_pulse.model.Instrument;

public class TickerFilter implements InstrumentFilter{
    private final String subStr;

    public TickerFilter(String subStr) {
        this.subStr = subStr.toLowerCase();
    }

    @Override
    public boolean matches(Instrument instrument) {
        return instrument.getTicker().toLowerCase().contains(subStr);
    }
}
