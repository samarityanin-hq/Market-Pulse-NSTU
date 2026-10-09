package academy.backend.interactive.seminar_3;

/// # Наша задача
///
/// 1. Сделать generic-обёртку над [java.util.HashMap] и использовать его в [academy.backend.market_pulse.factory.InstrumentFactories]:
///     - отдельный класс `class Registry<K, V>` с двумя типовыми параметрами - тип ключа и тип значения;
///     - доступны register/get;
///     - null-safety через `Optional`;
///
/// 2. Избавиться от `null` в [academy.backend.market_pulse.repository.InstrumentRepository#findByTicker(String)]
///     - возвращать `Optional`;
///     - красиво обработать `Optional` в местах использования;
///
/// # Не забудьте обратную связь
///
/// [Ссылка на опрос](https://polls.tbank.ru/s/cmtic4h3g000d0dwp235083y9)
///
/// ![Обратная связь](./doc-files/sA_outro.png)
///
///
public class SectionA_GenericsOptional_OutroAndTask {

}
