package academy.backend.market_pulse.filter;

import java.util.ArrayList;
import java.util.List;

public class FilterFactory {

    public InstrumentFilter buildFilter(FilterCriteria criteria){
        List<InstrumentFilter> filters = new ArrayList<>();

        if (criteria.type() != null) filters.add(new TypeFilter(criteria.type()));
        if (criteria.currency() != null) filters.add(new CurrencyFilter(criteria.currency()));
        if (criteria.ticker() != null) filters.add(new TickerFilter(criteria.ticker()));
        if (criteria.priceOp() != null || criteria.price() != null){
            if (criteria.priceOp() == null || criteria.price() == null){
                throw new IllegalArgumentException("--price-op и --price, указываются вместе");
            }
            filters.add(new PriceFilter(criteria.priceOp(), criteria.price()));
        }

        if (filters.size() > 1){
            throw new IllegalArgumentException("Можно указать только один фильтр");
        }

        return filters.isEmpty() ? new NoFilter() : filters.getFirst();
    }

}
