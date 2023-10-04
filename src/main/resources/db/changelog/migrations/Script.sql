-- liquibase formatted sql
-- changeset change:1
create table netology.CUSTOMERS
(
    id           int auto_increment not null,
    name         varchar(255)       not null,
    surname      varchar(255)       not null,
    age          int                not null,
    phone_number varchar(255)       not null,
    PRIMARY KEY (id)
);


-- rollback drop table netology.CUSTOMERS
-- changeset change:2

insert into netology.CUSTOMERS(name, surname, age, phone_number)
    value ('Ivan', 'Ivanov', 32, '222');

insert into netology.CUSTOMERS(name, surname, age, phone_number)
    value ('alexey', 'Ivanov', 21, '111');

insert into netology.CUSTOMERS(name, surname, age, phone_number)
    value ('Alexey', 'Ivanov', 44, '333');

-- changeset change:3

create table netology.ORDERS
(
    id           int auto_increment not null,
    date         varchar(255)       not null,
    customer_id  int                not null,
    product_name varchar(255)       not null,
    amount       varchar(255)       not null,
    PRIMARY KEY (id)
);
-- rollback drop table netology.ORDERS

-- changeset change:4

insert into netology.ORDERS(date, customer_id, product_name, amount)
    value ('1.01.2010', 1, 'chair', '10 000 RUR');
insert into netology.ORDERS(date, customer_id, product_name, amount)
    value ('2.01.2010', 2, 'table', '10 000 RUR');
insert into netology.ORDERS(date, customer_id, product_name, amount)
    value ('3.01.2010', 3, 'dishwasher', '10 000 RUR');



