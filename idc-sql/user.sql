create table user
(
    id          int auto_increment
        primary key,
    username    varchar(255) null,
    email       varchar(255) not null,
    file_struct text         null,
    password    varchar(255) not null,
    constraint id
        unique (id)
);

INSERT INTO idc.user (id, username, email, file_struct, password) VALUES (4, '2260434463@qq.com', '2260434463@qq.com', null, '123456');
