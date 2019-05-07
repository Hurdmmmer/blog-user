CREATE TABLE `blog_user`
(
    `id`       bigint(20)   NOT NULL AUTO_INCREMENT,
    `name`     varchar(20)  NOT NULL COMMENT '昵称',
    `username` varchar(20)  NOT NULL COMMENT '账号',
    `password` varchar(200)  NOT NULL COMMENT '密码',
    `email`    varchar(50)  NOT NULL COMMENT '邮箱',
    `avatar`   varchar(200) NOT NULL DEFAULT '' COMMENT '头像地址',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- 用户角色关系表
CREATE TABLE `b_user_authority`
(
    `id`           int(11)    NOT NULL AUTO_INCREMENT,
    `user_id`      bigint(20) NOT NULL COMMENT '用户id',
    `authority_id` bigint(20) NOT NULL COMMENT '权限id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 角色表
CREATE TABLE `blog_authority`
(
    `id`   bigint(20)  NOT NULL AUTO_INCREMENT,
    `name` varchar(20) NOT NULL COMMENT '权限名称',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
