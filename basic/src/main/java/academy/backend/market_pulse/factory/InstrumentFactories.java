package academy.backend.market_pulse.factory;

import java.util.Optional;
import java.util.ServiceLoader;

import academy.backend.market_pulse.model.Currency;
import academy.backend.market_pulse.model.Instrument;

/**
 * Реестр фабрик инструментов. Вместо загрузки каждой фабрики можно заставить java найти все самостоятельно
 * так же блоки инициализации из классов фабрик так же можно убрать нужен только публичный конструктор
 *
 * @author Ilya Traize
 */
public final class InstrumentFactories {

    //private static final Map<String, InstrumentFactory> REGISTRY = new HashMap<>();
    private static final Registry<String, InstrumentFactory> REGISTRY = new Registry<>();

    static {
        ServiceLoader<InstrumentFactory> loader = ServiceLoader.load(InstrumentFactory.class);
        for (InstrumentFactory factory : loader){
            String type = factory.getClass().getSimpleName().replace("Factory", "").toUpperCase();
            REGISTRY.register(type, factory);
        }
    }

    private InstrumentFactories() {}

    public static void register(String type, InstrumentFactory factory) {
        REGISTRY.register(type.toUpperCase(), factory);
    }

    public static Instrument create(String type, String ticker, String name, Currency currency) {
        Optional<InstrumentFactory> factory = REGISTRY.get(type.toUpperCase());
        if (factory.isEmpty()) {
            throw new IllegalArgumentException("Unknown instrument type: " + type);
        }
        return factory.get().create(ticker, name, currency);
    }


}
