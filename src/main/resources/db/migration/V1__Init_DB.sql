create table cafe (
    cafe_id bigint not null,
    address varchar(255),
    name_cafe varchar(255),
    phone varchar(255),
    primary key (cafe_id));

create table hibernate_sequence (next_val bigint);
    insert into hibernate_sequence values ( 1 );
    insert into hibernate_sequence values ( 1 );
    insert into hibernate_sequence values ( 1 );
    insert into hibernate_sequence values ( 1 );

create table pizza (
    id bigint not null,
    description varchar(255),
    pizza_name varchar(255),
    price double precision,
    size varchar(255),
    cafe_id bigint,
    primary key (id));

create table pizza_order (
    order_id bigint not null,
    address_delivery varchar(255),
    name_customer varchar(255),
    phone_customer varchar(255),
    id bigint,
    primary key (order_id));

create table user_role (
    user_id bigint not null,
    roles varchar(255));
create table usr (
    user_id bigint not null,
    activation_code varchar(255),
    active bit not null,
    email varchar(255),
    password varchar(255),
    username varchar(255),
    primary key (user_id));

alter table if exists pizza
    add constraint FKr7rg24y6ks9lrq1jf65txpqya
    foreign key (cafe_id) references cafe (cafe_id);

alter table if exists pizza_order
    add constraint FKj2mmllsnnsemmiur0a8peaean
    foreign key (id) references pizza (id);

alter table if exists user_role
    add constraint FKfpm8swft53ulq2hl11yplpr5
    foreign key (user_id) references usr (user_id);
