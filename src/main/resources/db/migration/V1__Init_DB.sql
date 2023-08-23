create table cafe (cafe_id bigint not null, address varchar(511), name_cafe varchar(63), phone varchar(63), primary key (cafe_id)); engine=InnoDB
create table hibernate_sequence (next_val bigint); engine=InnoDB
    insert into hibernate_sequence values ( 1 );
    insert into hibernate_sequence values ( 1 );
    insert into hibernate_sequence values ( 1 );
    insert into hibernate_sequence values ( 1 );
create table pizza (id bigint not null, description varchar(2048), pizza_name varchar(63), price double precision, size varchar(45), cafe_id bigint, primary key (id)); engine=InnoDB
create table pizza_order (order_id bigint not null, address_delivery varchar(255), name_customer varchar(255), phone_customer varchar(63), id bigint, primary key (order_id)); engine=InnoDB
create table user_role (user_id bigint not null, roles varchar(255)); engine=InnoDB
create table usr (user_id bigint not null, activation_code varchar(511), active bit not null, email varchar(255), password varchar(255), username varchar(255), primary key (user_id)); engine=InnoDB
alter table pizza add constraint pizza_cafe_fk foreign key (cafe_id) references cafe (cafe_id);
alter table pizza_order add constraint pizza_order_fk foreign key (id) references pizza (id);
alter table user_role add constraint user_role_user_id_fk foreign key (user_id) references usr (user_id);


