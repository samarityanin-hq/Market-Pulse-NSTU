package academy.backend.market_pulse.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import academy.backend.market_pulse.model.Currency;
import academy.backend.market_pulse.model.Instrument;

/**
 * Реестр фабрик инструментов. Вместо загрузки каждой фабрики можно заставить java найти все самостоятельно
 * так же блоки инициализации из классов фабрик так же можно убрать нужен только публичный конструктор
 */
public final class InstrumentFactories {

    private static final Map<String, InstrumentFactory> REGISTRY = new HashMap<>();

    static {
        ServiceLoader<InstrumentFactory> loader = ServiceLoader.load(InstrumentFactory.class);
        for (InstrumentFactory factory : loader){
            String type = factory.getClass().getSimpleName().replace("Factory", "").toUpperCase();
            REGISTRY.put(type, factory);
        }
    }

    private InstrumentFactories() {}

    public static void register(String type, InstrumentFactory factory) {
        REGISTRY.put(type.toUpperCase(), factory);
    }

    public static Instrument create(String type, String ticker, String name, Currency currency) {
        InstrumentFactory factory = REGISTRY.get(type.toUpperCase());
        if (factory == null) {
            throw new IllegalArgumentException("Unknown instrument type: " + type);
        }
        return factory.create(ticker, name, currency);
    }


}
