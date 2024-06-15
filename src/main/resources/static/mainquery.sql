DROP DATABASE IF EXISTS `connectz`;

CREATE DATABASE `connectz` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `connectz`;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users`
(
    `user_id`  INT NOT NULL,
    `username` VARCHAR(50) NOT NULL,
    `password` VARCHAR(68),
    `active`   INT DEFAULT 1,
    PRIMARY KEY(`user_id`)
)
    engine=innodb;

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles`
(
    `role_id` INT NOT NULL auto_increment,
    `user_id` INT NOT NULL,
    `role`    VARCHAR(50) NOT NULL DEFAULT "user",
    PRIMARY KEY(`role_id`),
    UNIQUE KEY `authorities_idx_1` (`user_id`, `role`),
    CONSTRAINT `authorities_idfk_1` FOREIGN KEY (`user_id`) REFERENCES `users`(
                                                                               `user_id`)
)
    engine=innodb;

DROP TABLE IF EXISTS `media`;

CREATE TABLE `media`
(
    `media_id` INT NOT NULL,
    `title`    VARCHAR(256) DEFAULT "noname",
    PRIMARY KEY(`media_id`)
)
    engine=innodb;

DROP TABLE IF EXISTS `entry`;

CREATE TABLE `entry`
(
    `user_id`  INT NOT NULL,
    `media_id` INT NOT NULL,
    `score`    INT,
    `status`   VARCHAR(20) NOT NULL,
    PRIMARY KEY `entries_idx_1` (`user_id`, `media_id`),
    KEY `entries_idx` (`user_id`),
    CONSTRAINT `entries_idfk_user_1` FOREIGN KEY (`user_id`) REFERENCES `users`
        (`user_id`),
    CONSTRAINT `entries_idfk_media_1` FOREIGN KEY (`media_id`) REFERENCES
        `media`(`media_id`)
)
    engine=innodb;