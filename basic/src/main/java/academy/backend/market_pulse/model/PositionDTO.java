package main.java.academy.backend.market_pulse.model;

import academy.backend.market_pulse.model.Instrument;

public record PositionDTO(Instrument instrument, int quantity) implements Portfolio.Position {
    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }
}
