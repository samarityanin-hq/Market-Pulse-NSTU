package academy.backend.market_pulse.model;

import academy.backend.market_pulse.model.Instrument;
import main.java.academy.backend.market_pulse.model.Portfolio.Position;

public record PositionDTO(Instrument instrument, int quantity) implements Position {
    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }
}
