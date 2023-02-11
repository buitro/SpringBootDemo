INSERT INTO brands(id, description) VALUES(1, 'Zara');

INSERT INTO products(id, brand_id, description) VALUES(35455, 1L, 'Vestido estampado floral');

INSERT INTO prices(price_id, brand_id, start_date, end_date, product_id, priority, price, curr)
VALUES(1L, 1L, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 35455, 0, 35.50, 'EUR');
INSERT INTO prices(price_id, brand_id, start_date, end_date, product_id, priority, price, curr)
VALUES(2L, 1L, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 35455, 1, 25.45, 'EUR');
INSERT INTO prices(price_id, brand_id, start_date, end_date, product_id, priority, price, curr)
VALUES(3L, 1L, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 35455, 1, 30.50, 'EUR');
INSERT INTO prices(price_id, brand_id, start_date, end_date, product_id, priority, price, curr)
VALUES(4L, 1L, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 35455, 1, 38.95, 'EUR');
