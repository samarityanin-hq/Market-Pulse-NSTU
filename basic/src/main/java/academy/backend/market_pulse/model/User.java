package main.java.academy.backend.market_pulse.model;

import academy.backend.market_pulse.model.Instrument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User implements Portfolio {
    private final String name;

    private final Map<Instrument, Integer> storage = new HashMap<>();

    public User(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Position[] getPositions() {
        List<PositionDTO> positions = new ArrayList<>();

        for (Map.Entry<Instrument, Integer> entry : storage.entrySet()){
            positions.add(new PositionDTO(entry.getKey(), entry.getValue()));
        }

        return positions.toArray(new PositionDTO[0]);
    }

    @Override
    public void addPosition(Instrument instrument, int quantity) {
        if (instrument == null){
            throw new IllegalArgumentException("Инструмент не может быть пустым");
        }
        if (quantity <= 0){
            throw new IllegalArgumentException("Колличество должно быть больше нуля");
        }

        storage.merge(instrument, quantity, Integer::sum);
    }
}
