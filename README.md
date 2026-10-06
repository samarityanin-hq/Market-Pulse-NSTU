# Market Pulse NSTU

## Семинар 2: фильтры и фабрики

### Команда `list`

Выводит инструменты из репозитория. Без опций выводит все.

```
list --type BOND                 # по типу (STOCK, BOND, ETF)
list --ticker AAP                # тикер содержит подстроку, без учёта регистра
list --currency USD              # по валюте (RUB, USD, EUR)
list --price-op GE --price 10    # акции с дивидендной доходностью >= 10
```

Правила:
- одновременно действует только один критерий, иначе ошибка;
- `--price-op` (GT, GE, LT, LE, EQ) и `--price` указываются вместе и считаются одним критерием;
- фильтр по цене применим только к `Stock` (сравнивается `dividendYield`), остальные типы не подходят.

**Реализация (паттерн Strategy):**
- `InstrumentFilter` — интерфейс с методом `matches(Instrument)`;
- `NoFilter`, `TypeFilter`, `TickerFilter`, `CurrencyFilter`, `PriceFilter` — по одному классу на правило;
- `PriceOp` — enum операций сравнения;
- `ListCommand.buildFilter()` собирает заданные фильтры в список и выбирает единственный; если их больше одного — `IllegalArgumentException`.

### Фабрики инструментов

Фабрики подключаются автоматически через `ServiceLoader`, ручная регистрация не нужна:
- реализации `InstrumentFactory` перечислены в `src/main/resources/META-INF/services/academy.backend.market_pulse.factory.InstrumentFactory`;
- `InstrumentFactories` при загрузке находит их и кладёт в реестр; тип определяется по имени класса (`StockFactory` → `STOCK`).

**Как добавить новый тип инструмента:**
1. Создать класс `<Тип>Factory`, реализующий `InstrumentFactory`, с публичным конструктором без аргументов.
2. Добавить его полное имя отдельной строкой в файл из `META-INF/services`.