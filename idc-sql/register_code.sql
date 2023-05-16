create table register_code
(
    id          int auto_increment
        primary key,
    code        varchar(255) null,
    email       text         null,
    create_time timestamp    null,
    constraint id
        unique (id)
);

INSERT INTO idc.register_code (id, code, email, create_time) VALUES (2, '248053', '2260434463@qq.com', '2023-05-11 00:00:00');
