create SCHEMA `library` DEFAULT CHARACTER SET utf8;

USE `library`;

SET FOREIGN_KEY_CHECKS = 0;

-- drop tables
drop table IF EXISTS `book_author` CASCADE;
drop table IF EXISTS `book_genre` CASCADE;
drop table IF EXISTS `book` CASCADE;
drop table IF EXISTS `author` CASCADE;
drop table IF EXISTS `genre` CASCADE;

-- genre table
create TABLE `genre`
(
    `id`   BIGINT       NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,

    CONSTRAINT `pk_genreId` PRIMARY KEY (`id`),
    UNIQUE (name)
) ENGINE = InnoDB;

-- author table
create TABLE `author`
(
    `id`      BIGINT       NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(100) NOT NULL,
    `surname` VARCHAR(100) NOT NULL,

    CONSTRAINT `pk_authorId` PRIMARY KEY (`id`),
    UNIQUE (name, surname)
) ENGINE = InnoDB;

-- book table
create TABLE `book`
(
    `id`   BIGINT       NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    `isbn` VARCHAR(100) NOT NULL,
    `date` DATETIME     NOT NULL,

    CONSTRAINT `pk_bookId` PRIMARY KEY (`id`)
) ENGINE = InnoDB;

-- book_genre table
create TABLE `book_genre`
(
    `bookId`  BIGINT NOT NULL,
    `genreId` BIGINT NOT NULL,

    CONSTRAINT `fk_book_genre_bookId`   FOREIGN KEY (`bookId`)  REFERENCES `book`   (`id`) ON delete CASCADE,
    CONSTRAINT `fk_book_genre_genreId`  FOREIGN KEY (`genreId`) REFERENCES `genre`  (`id`) ON delete CASCADE
) ENGINE = InnoDB;

-- book_author table
create TABLE `book_author`
(
    `bookId`    BIGINT NOT NULL,
    `authorId`  BIGINT NOT NULL,

    CONSTRAINT `fk_book_author_bookId`   FOREIGN KEY (`bookId`)     REFERENCES `book`   (`id`) ON delete CASCADE,
    CONSTRAINT `fk_book_author_authorId` FOREIGN KEY (`authorId`)   REFERENCES `author` (`id`) ON delete CASCADE
) ENGINE = InnoDB;

SET FOREIGN_KEY_CHECKS = 1;