create sequence product_id_seq start with 1 increment by 50;

create table products
(
    id              bigint default nextval('product_id_seq') not null,
    code            TEXT NOT NULL UNIQUE,
    name            TEXT NOT NULL,
    description     TEXT,
    image_url       TEXT,
    price           NUMERIC NOT NULL,
    PRIMARY KEY     (id)
)


