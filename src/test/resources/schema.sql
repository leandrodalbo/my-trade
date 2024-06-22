DROP TABLE IF EXISTS opportunity;
CREATE TABLE opportunity (
    symbolspeed       varchar(50) PRIMARY KEY NOT NULL,
    binanceengulfing  BOOLEAN NOT NULL,
    binancema         BOOLEAN NOT NULL,
    binanceprice      DECIMAL,
    binancestop       DECIMAL,
    binanceprofit     DECIMAL,
    krakenengulfing   BOOLEAN NOT NULL,
    krakenma          BOOLEAN NOT NULL,
    krakenprice       DECIMAL,
    krakenstop        DECIMAL,
    krakenprofit      DECIMAL,
    ondatetime        BIGINT NOT NULL,
    version           INTEGER NOT NULL
);
