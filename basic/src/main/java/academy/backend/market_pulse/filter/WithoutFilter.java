package academy.backend.market_pulse.filter;

import academy.backend.market_pulse.model.Instrument;

/**
 * @author Bogdan Kolganov
 */
public class WithoutFilter implements InstrumentFilter {
    public WithoutFilter() { }

    @Override
    public boolean matches(Instrument instrument) {
        return true;
    }
}
