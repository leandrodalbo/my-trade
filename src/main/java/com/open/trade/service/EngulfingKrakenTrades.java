package com.open.trade.service;

import com.open.trade.configuration.KrakenProps;
import com.open.trade.data.Candle;
import com.open.trade.exchangecall.KrakenCall;
import com.open.trade.model.Opportunity;
import com.open.trade.model.Speed;
import com.open.trade.repository.OpportunityRepository;
import com.open.trade.strategy.EngulfingCandleStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EngulfingKrakenSearch implements FetchNewTrades {
    private final Logger logger = LoggerFactory.getLogger(EngulfingKrakenSearch.class);

    private final OpportunityRepository repository;
    private final EngulfingCandleStrategy strategy;
    private final KrakenProps props;
    private final KrakenCall krakenCall;


    public EngulfingKrakenSearch(OpportunityRepository repository, EngulfingCandleStrategy strategy, KrakenProps props, KrakenCall krakenCall) {
        this.repository = repository;
        this.strategy = strategy;
        this.props = props;
        this.krakenCall = krakenCall;
    }

    @Override
    public void searchEntries(Speed speed) {
        this.props.symbols().forEach(symbol -> {
            saveInfo(symbol, krakenCall.engulfingCandles(symbol, speed), speed);
        });
    }

    private void saveInfo(String symbol, Mono<Candle[]> values, Speed speed) {
        values.subscribe(
                it -> {
                    if (strategy.isEngulfing(it)) {

                        repository.findBySymbol(symbol)
                                .doOnError(e -> {
                                    logger.warn(e.getMessage());
                                    logger.warn("SAVING NEW OPPORTUNITY");

                                    Candle c1 = it[1];

                                    repository.save(Opportunity.of(
                                            symbol,
                                            speed,
                                            false,
                                            0f,
                                            0f,
                                            0f,
                                            speed,
                                            true,
                                            c1.close(),
                                            c1.close() * props.stop(),
                                            c1.close() * props.profit()
                                    ));
                                })
                                .subscribe(opportunity -> {

                                    Candle c1 = it[1];
                                    repository.save(Opportunity.of(
                                            symbol,
                                            speed,
                                            opportunity.binanceengulfing(),
                                            opportunity.binanceprice(),
                                            opportunity.binancestop(),
                                            opportunity.binanceprofit(),
                                            speed,
                                            true,
                                            c1.close(),
                                            c1.close() * props.stop(),
                                            c1.close() * props.profit()
                                    ));
                                });
                    }
                }
        );
    }
}