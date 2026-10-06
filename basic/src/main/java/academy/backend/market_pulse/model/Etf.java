package academy.backend.market_pulse.model;

import java.util.Objects;

public class Etf extends Instrument {

    private final String trackingIndex;

    public Etf(String ticker, String name, Currency currency, String trackingIndex) {
        super(ticker, name, currency);
        Objects.requireNonNull(trackingIndex, "trackingIndex не может быть null");

        this.trackingIndex = trackingIndex;
    }

    public String getTrackingIndex() {
        return trackingIndex;
    }

    @Override
    public String getDescription() {
        return "ETF, отслеживает индекс: " + trackingIndex;
    }

    @Override
    public String getType() {
        return "ETF";
    }
}
