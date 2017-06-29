CREATE KEYSPACE IF NOT EXISTS myretail WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}  AND durable_writes = true;


CREATE TABLE IF NOT EXISTS myretail.productprices (
   	productID bigint,
   	price float,
    currency text,
    PRIMARY KEY (productID)
);


INSERT INTO myretail.productprices (productID, price, currency) VALUES(13860428,13.49,'USD');