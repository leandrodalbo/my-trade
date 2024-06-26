DROP TABLE IF EXISTS opportunity;
CREATE TABLE opportunity (
    opportunityid           varchar(50) PRIMARY KEY NOT NULL,
    binanceengulfing        BOOLEAN NOT NULL,
    binanceengulfingtime    BIGINT NOT NULL,
    binancema               BOOLEAN NOT NULL,
    binancematime           BIGINT NOT NULL,
    binanceprice            DECIMAL,
    binancestop             DECIMAL,
    binanceprofit           DECIMAL,
    krakenengulfing         BOOLEAN NOT NULL,
    krakenengulfingtime     BIGINT NOT NULL,
    krakenma                BOOLEAN NOT NULL,
    krakenmatime            BIGINT NOT NULL,
    krakenprice             DECIMAL,
    krakenstop              DECIMAL,
    krakenprofit            DECIMAL,
    version                 INTEGER NOT NULL
);
