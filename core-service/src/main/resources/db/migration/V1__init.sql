create table categories (
    id              bigserial primary key,
    title           varchar(255) unique,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default  current_timestamp
);

insert into categories (title) values ('Books'), ('Prints'), ('Original Art');

create table products
(   id bigserial    primary key,
    title           varchar(255),
    category_id     bigint references categories (id),
    price           int,
    height          int,
    weight          int,
    description     varchar(255),
    image           varchar(100),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default  current_timestamp
);

insert into products (title, price, category_id, height, weight, description, image) values
('Bear', 800, 2, 50, 30, 'High-quality giclee print with nonfading ink on premium textured paper.', 'images/carts/bear.jpg'),
('Cat hairstyle', 25, 2, 20, 20, 'High-quality giclee print with nonfading ink on premium textured paper.', 'images/carts/cat_hairstyle.jpg'),
('Magic', 700, 2, 25, 25, 'High-quality giclee print with nonfading ink on premium textured paper.', 'images/carts/magic.jpg'),
('Morning coffee', 700, 2, 25, 25, 'High-quality giclee print with nonfading ink on premium textured paper.', 'images/carts/morningcoffee.jpg'),
('Peace', 500, 2, 30, 30, 'High-quality giclee print with nonfading ink on premium textured paper.', 'images/carts/peace.jpg'),
('Submarine', 1000, 2, 30, 50, 'High-quality giclee print with nonfading ink on premium textured paper.', 'images/carts/submarine.jpg');

create table orders
(
    id              bigserial primary key,
    username        varchar(255) not null,
    total_price     int not null,
    address         varchar(255),
    phone           varchar(255),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table  order_items
(
    id                  bigserial primary key,
    product_id          bigint not null references products (id),
    order_id            bigint not null references orders (id),
    quantity            int not null,
    price_per_product   int not null,
    price               int not null,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);