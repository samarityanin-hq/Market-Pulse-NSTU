package academy.backend.market_pulse.cli;

import academy.backend.market_pulse.service.QuoteService;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.Queue;
import java.util.concurrent.Callable;

@Command(name = "quote", description = "Выводит котировок по перечисленным тикерам")
public class QuoteCommand implements Callable<Integer> {

    @CommandLine.Parameters(description = "Очередь тикеров")
    private Queue<String> quotes;

    private final QuoteService service;

    public QuoteCommand(QuoteService service){
        this.service = service;
    }

    @Override
    public Integer call() throws Exception {

        while (!quotes.isEmpty()){
            System.out.println(service.getQuote(quotes.poll()));
        }

        return 0;
    }
}
