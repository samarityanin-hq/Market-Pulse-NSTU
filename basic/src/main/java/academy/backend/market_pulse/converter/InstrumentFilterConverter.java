package academy.backend.market_pulse.converter;

import academy.backend.market_pulse.cli.ListCommand;
import academy.backend.market_pulse.filter.*;
import academy.backend.market_pulse.model.Currency;
import academy.backend.market_pulse.model.Stock;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Bogdan Kolganov
 */
public final class InstrumentFilterConverter {
    public static InstrumentFilter convert(ListCommand.ListCommandFilter filter, List<Object> params) {
        return switch (filter) {
            case BY_TYPE -> {
                final String type = (String) params.getFirst();
                yield (instrument) -> instrument.getType().equals(type.toUpperCase());
            }

            case BY_PRICE -> {
                final BigDecimal price = (BigDecimal) params.get(0);
                final EqualsOperator price_op = (EqualsOperator) params.get(1);
                yield (instrument) -> switch (instrument) {
                    case Stock stock -> compareByParam(price, stock.getDividendYield(), price_op);
                    default -> false;
                };
            }

            case BY_TICKER -> {
                final String ticker = (String) params.getFirst();
                yield (instrument) -> instrument.getTicker()
                        .toUpperCase()
                        .contains(ticker.toUpperCase());
            }

            case BY_CURRENCY -> {
                final Currency currency = (Currency) params.getFirst();
                yield (instrument) -> instrument.getCurrency().equals(currency);
            }

            case WITHOUT -> (instrument) -> true;
        };
    }

    public enum EqualsOperator {
        GE, EQ, LE, LS, GR
    }

    private static boolean compareByParam(BigDecimal price, BigDecimal instrumentPrice, EqualsOperator param) {
        return switch (param) {
            case GE -> instrumentPrice.compareTo(price) >= 0;
            case EQ -> instrumentPrice.compareTo(price) == 0;
            case LE -> instrumentPrice.compareTo(price) <= 0;
            case LS -> instrumentPrice.compareTo(price) < 0;
            case GR -> instrumentPrice.compareTo(price) > 0;
        };
    }
}
