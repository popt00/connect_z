DROP DATABASE IF EXISTS `connectz`;
CREATE DATABASE `connectz`;
USE `connectz`;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
	`user_id` 	int			NOT NULL,
    `name`		varchar(50)	NOT NULL,
    `password`	varchar(50),
    PRIMARY KEY(`user_id`)
) Engine=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
	`user_id` 	int			NOT NULL,
    `role`		varchar(50)	NOT NULL,
    UNIQUE KEY `authorities_idx_1` (`user_id`,`role`),
    CONSTRAINT `authorities_idfk_1`
		FOREIGN KEY (`user_id`)
        REFERENCES `users`(`user_id`)
) Engine=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `media`;
CREATE TABLE `media`(
	`media_id`	int			NOT NULL,
    `title`		varchar(50)
) Engine=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `entry`;
CREATE TABLE `entry` (
	`user_id` 	int			NOT NULL,
    `media_id`	int			NOT NULL,
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
