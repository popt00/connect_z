DROP DATABASE IF EXISTS `connectz`;
CREATE DATABASE `connectz`;
USE `connectz`;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
                         `user_id`   BIGINT NOT NULL AUTO_INCREMENT,
                         `anilist_user_id` 	BIGINT			NOT NULL,
                         `username`		varchar(50)	NOT NULL,
                         `password`	varchar(50),
                         `active`    int         DEFAULT 1,
                         PRIMARY KEY(`user_id`),
                         UNIQUE KEY(`anilist_user_id`)
) Engine=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
                        `role_id`   BIGINT         NOT NULL AUTO_INCREMENT,
                         `user_id` 	BIGINT			NOT NULL,
                         `role`		varchar(50)	NOT NULL,
                        PRIMARY KEY(`role_id`),
                         UNIQUE KEY `authorities_idx_1` (`user_id`,`role`),
                         CONSTRAINT `authorities_idfk_1`
                             FOREIGN KEY (`user_id`)
                                 REFERENCES `users`(`user_id`)
) Engine=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `media`;
CREATE TABLE `media`(
                        `media_id`	BIGINT NOT NULL AUTO_INCREMENT,
                        `anilist_media_id` BIGINT not null,
                        `title`		varchar(100) default "okay",
                        PRIMARY KEY(`media_id`),
                        UNIQUE KEY(`anilist_media_id`)
) Engine=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `entry`;
CREATE TABLE `entry` (
                         `user_id` 	BIGINT			NOT NULL,
                         `media_id`	BIGINT			NOT NULL,
                         `score`		int,
                         `status`	varchar(20)	NOT NULL,
                         PRIMARY KEY `entries_idx_1` (`user_id`,`media_id`),
                         KEY `entries_idx` (`user_id`),

                         CONSTRAINT `entries_idfk_user_1`
                             FOREIGN KEY (`user_id`)
                                 REFERENCES `users`(`user_id`),
                         CONSTRAINT `entries_idfk_media_1`
                             FOREIGN KEY (`media_id`)
                                 REFERENCES `media`(`media_id`)
) Engine=InnoDB DEFAULT CHARSET=latin1;
