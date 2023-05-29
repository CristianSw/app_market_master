create table categories
(
    id         bigserial primary key,
    title      varchar(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into categories (title)
values ('Memory'),
       ('CPU'),
       ('GPU'),
       ('Monitor'),
       ('Mouse'),
       ('Keyboard');


create table products
(
    id          bigserial primary key,
    title       varchar(255),
    category_id bigint references categories (id),
    price       numeric(8,2),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

insert into products (title, price, category_id)
values ('HDD', 2500.00, 1),
       ('SSD', 3500.00, 1),
       ('Ryzen3', 12000.00, 2),
       ('Ryzen5', 25000.00, 2),
       ('Ryzen7', 35000.00, 2),
       ('Ryzen9', 120000.00, 2),
       ('Flash Disk', 1500.00, 1),
       ('Flash microSD', 1000.00, 1),
       ('LCD монитор', 12000.00, 4),
       ('GeForce RTX2070', 75000.00, 3),
       ('Mouse', 350.00, 5),
       ('Keyboard', 1000.00, 6);
create table orders
(
    id              bigserial primary key,
    username        varchar(255) not null ,
    total_price     numeric(8, 2),
    address         varchar(255),
    phone           varchar(255),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table orders_items
(
    id                      bigserial primary key,
    order_id                bigint references orders (id),
    product_id              bigint references products (id),
    price_per_product       numeric(8, 2),
    quantity                int,
    price                   numeric(8, 2),
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

