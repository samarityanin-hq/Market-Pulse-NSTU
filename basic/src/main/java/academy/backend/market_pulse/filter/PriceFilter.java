package academy.backend.market_pulse.filter;

import academy.backend.market_pulse.model.Instrument;
import academy.backend.market_pulse.model.Stock;

import java.math.BigDecimal;

public class PriceFilter implements InstrumentFilter{
    private final PriceOp operation;
    private final BigDecimal price;

    public PriceFilter(PriceOp operation, BigDecimal price) {
        this.operation = operation;
        this.price = price;
    }

    @Override
    public boolean matches(Instrument instrument) {
        if (!(instrument instanceof Stock)){
            return false;
        }

        int comparison = ((Stock) instrument).getDividendYield().compareTo(price);

        return switch (operation){
            case GT -> comparison > 0;
            case GE -> comparison >= 0;
            case LT -> comparison < 0;
            case LE -> comparison <= 0;
            case EQ -> comparison == 0;
        };
    }
}
