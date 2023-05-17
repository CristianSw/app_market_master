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
    price       int,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table users
(
    id       bigserial primary key,
    username varchar(36) not null,
    password varchar(80) not null
);

create table roles
(
    id   bigserial primary key,
    name varchar(50) not null
);

create table users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password)
values ('bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i'),
       ('john', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');

insert into users_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2);

insert into products (title, price, category_id)
values ('HDD', 2500, 1),
       ('SSD', 3500, 1),
       ('Ryzen3', 12000, 2),
       ('Ryzen5', 25000, 2),
       ('Ryzen7', 35000, 2),
       ('Ryzen9', 120000, 2),
       ('Flash Disk', 1500, 1),
       ('Flash microSD', 1000, 1),
       ('LCD монитор', 12000, 4),
       ('GeForce RTX2070', 75000, 3),
       ('Mouse', 350, 5),
       ('Keyboard', 1000, 6);
create table orders
(
    id              bigserial primary key,
    user_id         bigint references users (id),
    total_price     numeric(8, 2),
    address         varchar(255),
    phone           varchar(255),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table orders_items
(
    id                      bigserial primary key,
    order_id                bigint references users (id),
    product_id              bigint references products (id),
    price_per_product       numeric(8, 2),
    quantity                int,
    price                   numeric(8, 2),
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);
