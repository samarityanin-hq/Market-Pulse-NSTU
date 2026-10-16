package academy.backend.market_pulse.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import academy.backend.market_pulse.model.Currency;
import academy.backend.market_pulse.model.Instrument;
import academy.backend.market_pulse.utils.Registry;

/**
 * Реестр фабрик инструментов. Вместо загрузки каждой фабрики можно заставить java найти все самостоятельно
 * так же блоки инициализации из классов фабрик так же можно убрать нужен только публичный конструктор
 *
 * @author Ilya Traize
 */
public final class InstrumentFactories {

    private static final Registry<String, InstrumentFactory> REGISTRY = new Registry<>();

    static {
        ServiceLoader<InstrumentFactory> loader = ServiceLoader.load(InstrumentFactory.class);
        for (InstrumentFactory factory : loader){
            String type = factory.getClass().getSimpleName().replace("Factory", "").toUpperCase();
            register(type, factory);
        }
    }

    private InstrumentFactories() {}

    public static void register(String type, InstrumentFactory factory) {
        REGISTRY.register(type.toUpperCase(), factory);
    }

    public static Instrument create(String type, String ticker, String name, Currency currency) {
        return REGISTRY.get(type.toUpperCase())
                .orElseThrow(() -> new IllegalArgumentException("Unknown instrument type: " + type))
                .create(ticker, name, currency);
    }


}
