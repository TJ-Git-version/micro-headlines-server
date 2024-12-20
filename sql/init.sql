create database micro_headlines character set utf8;

create table `news_user`
(
    `id`               int auto_increment,
    `username`         varchar(64) not null comment '用户名',
    `nickname`         varchar(64)  default '' comment '昵称',
    `password`         varchar(32) not null comment '密码',
    `passwordSalt`     varchar(64) not null comment '加密盐',
    `email`            varchar(100) default '' comment '邮箱',
    `phoneNumber`      varchar(15)  default '' comment '手机号',
    `avatar`           varchar(255) default '' comment '头像',
    `status`           tinyint      default 1 comment '账户状态',
    `registrationDate` TIMESTAMP    DEFAULT CURRENT_TIMESTAMP comment '注册时间',
    `lastLoginTime`    TIMESTAMP    DEFAULT CURRENT_TIMESTAMP comment '最后登录时间',
    `loginCount`       bigint       default 0 comment '登录次数',
    `accountLockTim`   bigint       default 0 comment '锁定时间',
    primary key (`id`),
    unique key (`username`)
) comment '用户表';

create table `news`
(
    `id`          int auto_increment,
    `name`        varchar(128) not null comment '新闻标题',
    `content`     text         not null comment '新闻内容',
    `summary`     varchar(500) default '' comment '摘要',
    `images`      varchar(128) default '' comment '新闻封面',
    `views`       int          default 0 comment '阅读量',
    `likes`       int          default 0 comment '点赞数',
    `comments`    int          default 0 comment '评论数',
    `status`      int          default 0 comment '新闻状态',
    `isPinned`    tinyint      default 0 comment '是否置顶',
    `isOriginal`  tinyint      default 0 comment '是否原创',
    `editorID`    int          not null comment '创建人',
    `reviewTime`  bigint       default 0 comment '审核时间',
    `publishTime` TIMESTAMP    DEFAULT CURRENT_TIMESTAMP comment '发布时间',
    `updateTime`  bigint       default 0 comment '最后一次修改时间',
    `isDeleted`   tinyint      default 1 comment '是否被删除',
    primary key (`id`)
) comment '新闻信息';

create table `news_classify_relation`
(
    `newsId`     int not null comment '新闻id',
    `classifyId` int not null comment '分类id',
    primary key (`newsId`, `classifyId`)
) comment '新闻-分类-关联';

create table `news_classify`
(
    `id`         int auto_increment,
    `name`       varchar(32) not null comment '新闻分类名称',
    `remark`     varchar(128) default '' comment '备注',
    `createTime` TIMESTAMP    DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    primary key (`id`),
    unique key (`name`)
) comment '新闻分类';
