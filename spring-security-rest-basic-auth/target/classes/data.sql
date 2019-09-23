DROP TABLE IF EXISTS stock;
 
CREATE TABLE stock (
  stock_code VARCHAR(10)  PRIMARY KEY,
  stock_name VARCHAR(250) NOT NULL,
  sector VARCHAR(250) NOT NULL,
  price DECIMAL (20,2),
  create_time timestamp DEFAULT current_timestamp,
  update_time timestamp as current_timestamp
);


insert into stock (stock_code, stock_name, sector, price) values ('BDO','BANCO DE ORO','FINA',139.70);
insert into stock (stock_code, stock_name, sector, price) values ('JFC','JOLLIBEE FOOD CORP','SERV',220);
insert into stock (stock_code, stock_name, sector, price) values ('ALI','AYALA LAND INC','PROP',47.90);