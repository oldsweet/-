create table file
(
    id          int auto_increment
        primary key,
    filename    varchar(255)         not null,
    intro       text                 null,
    url         text                 null,
    user_id     int                  not null,
    update_time datetime             not null,
    create_time datetime             not null,
    is_dir      tinyint(1) default 0 not null,
    storage     varchar(255)         null,
    constraint id
        unique (id)
);

INSERT INTO idc.file (id, filename, intro, url, user_id, update_time, create_time, is_dir, storage) VALUES (105, 'install.exe', '', 'https://learn-interactive-video.oss-cn-hangzhou.aliyuncs.com/1_install.exe', 1, '2023-05-11 12:18:26', '2023-05-11 12:18:26', 0, null);
INSERT INTO idc.file (id, filename, intro, url, user_id, update_time, create_time, is_dir, storage) VALUES (106, 'install.res.1028.dll', '', 'https://learn-interactive-video.oss-cn-hangzhou.aliyuncs.com/1_install.res.1028.dll', 1, '2023-05-11 12:19:15', '2023-05-11 12:19:15', 0, null);
INSERT INTO idc.file (id, filename, intro, url, user_id, update_time, create_time, is_dir, storage) VALUES (109, 'globdata.ini', '', 'https://learn-interactive-video.oss-cn-hangzhou.aliyuncs.com/1_globdata.ini', 1, '2023-05-11 14:25:19', '2023-05-11 14:25:19', 0, null);
INSERT INTO idc.file (id, filename, intro, url, user_id, update_time, create_time, is_dir, storage) VALUES (113, 'eula.1040.txt', '', 'https://learn-interactive-video.oss-cn-hangzhou.aliyuncs.com/1_eula.1040.txt', 1, '2023-05-11 14:26:41', '2023-05-11 14:26:41', 0, null);
INSERT INTO idc.file (id, filename, intro, url, user_id, update_time, create_time, is_dir, storage) VALUES (114, 'eula.1033.txt', '', 'https://learn-interactive-video.oss-cn-hangzhou.aliyuncs.com/1_eula.1033.txt', 1, '2023-05-11 14:27:03', '2023-05-11 14:27:03', 0, null);
INSERT INTO idc.file (id, filename, intro, url, user_id, update_time, create_time, is_dir, storage) VALUES (115, 'eula.1031.txt', '', 'https://learn-interactive-video.oss-cn-hangzhou.aliyuncs.com/1_eula.1031.txt', 1, '2023-05-11 14:27:21', '2023-05-11 14:27:21', 0, null);
INSERT INTO idc.file (id, filename, intro, url, user_id, update_time, create_time, is_dir, storage) VALUES (117, 'eula.2052.txt', '', 'https://learn-interactive-video.oss-cn-hangzhou.aliyuncs.com/1_eula.2052.txt', 1, '2023-05-11 14:33:30', '2023-05-11 14:33:30', 0, null);
INSERT INTO idc.file (id, filename, intro, url, user_id, update_time, create_time, is_dir, storage) VALUES (118, 'eula.3082.txt', '', 'https://idc-1304582408.cos.ap-nanjing.myqcloud.com/1_eula.3082.txt', 1, '2023-05-11 15:18:19', '2023-05-11 15:18:19', 0, null);
INSERT INTO idc.file (id, filename, intro, url, user_id, update_time, create_time, is_dir, storage) VALUES (120, 'install.res.1033.dll', '', 'https://idc-1304582408.cos.ap-nanjing.myqcloud.com/1_install.res.1033.dll', 1, '2023-05-11 15:20:47', '2023-05-11 15:20:47', 0, null);
INSERT INTO idc.file (id, filename, intro, url, user_id, update_time, create_time, is_dir, storage) VALUES (128, 'globdata.ini', '', 'https://learn-interactive-video.oss-cn-hangzhou.aliyuncs.com/1_globdata.ini', 1, '2023-05-11 17:07:14', '2023-05-11 17:07:14', 0, 'aliyunoss');
INSERT INTO idc.file (id, filename, intro, url, user_id, update_time, create_time, is_dir, storage) VALUES (130, 'eula.1041.txt', '', 'https://idc-1304582408.cos.ap-nanjing.myqcloud.com//1_eula.1041.txt', 1, '2023-05-11 17:12:23', '2023-05-11 17:12:23', 0, 'tecentyunoss');
INSERT INTO idc.file (id, filename, intro, url, user_id, update_time, create_time, is_dir, storage) VALUES (131, 'eula.2052.txt', '', 'https://idc-1304582408.cos.ap-nanjing.myqcloud.com//1_eula.2052.txt', 1, '2023-05-11 17:13:47', '2023-05-11 17:13:47', 0, 'tecentyunoss');
INSERT INTO idc.file (id, filename, intro, url, user_id, update_time, create_time, is_dir, storage) VALUES (132, 'eula.3082.txt', '', 'https://idc-1304582408.cos.ap-nanjing.myqcloud.com//1_eula.3082.txt', 1, '2023-05-11 17:21:47', '2023-05-11 17:21:47', 0, 'tecentyunoss');
INSERT INTO idc.file (id, filename, intro, url, user_id, update_time, create_time, is_dir, storage) VALUES (133, 'globdata.ini', '', 'https://learn-interactive-video.oss-cn-hangzhou.aliyuncs.com/1_globdata.ini', 1, '2023-05-11 17:53:45', '2023-05-11 17:53:45', 0, 'aliyunoss');
INSERT INTO idc.file (id, filename, intro, url, user_id, update_time, create_time, is_dir, storage) VALUES (134, 'install.res.3082.dll', '', 'https://learn-interactive-video.oss-cn-hangzhou.aliyuncs.com/4_install.res.3082.dll', 4, '2023-05-11 20:05:22', '2023-05-11 20:05:22', 0, 'aliyunoss');
INSERT INTO idc.file (id, filename, intro, url, user_id, update_time, create_time, is_dir, storage) VALUES (135, '移动边缘网络中计算迁移与内容缓存研究综述_张开元.pdf', '', 'https://idc-1304582408.cos.ap-nanjing.myqcloud.com//4_移动边缘网络中计算迁移与内容缓存研究综述_张开元.pdf', 4, '2023-05-11 20:05:36', '2023-05-11 20:05:36', 0, 'tecentyunoss');
