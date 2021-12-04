-- customer_phone definition

-- Drop table

--DROP TABLE IF EXISTS customer_phone;

--CREATE TABLE customer_phone (
 --   customer_id int4 NOT NULL,
 --   phonenumber_id int4 NOT NULL,
--CONSTRAINT uk_4lkeq0sskkbl78q8gxdamj6r UNIQUE (phonenumber_id));


-- customer_phone foreign keys

--ALTER TABLE customer_phone ADD CONSTRAINT fk13nil2w1i4isllk6ek7ypwebu FOREIGN KEY (phonenumber_id) REFERENCES phonenumber(id);
--ALTER TABLE customer_phone ADD CONSTRAINT fkni7c19gxj6a4t4eu60khpbi9u FOREIGN KEY (customer_id) REFERENCES customer(id);
