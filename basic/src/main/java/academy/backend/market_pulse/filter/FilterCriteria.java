package academy.backend.market_pulse.filter;

import academy.backend.market_pulse.model.Currency;

import java.math.BigDecimal;

public record FilterCriteria(String type,
                       Currency currency,
                       String ticker,
                       PriceOp priceOp,
                       BigDecimal price
){}
