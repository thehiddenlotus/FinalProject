-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema checkaheaddb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `checkaheaddb` ;

-- -----------------------------------------------------
-- Schema checkaheaddb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `checkaheaddb` DEFAULT CHARACTER SET utf8 ;
USE `checkaheaddb` ;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(1000) NULL,
  `city` VARCHAR(500) NULL,
  `zip` INT NULL,
  `state` VARCHAR(10) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address_id` INT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `active` TINYINT NULL,
  `date_created` DATETIME NOT NULL,
  `role` VARCHAR(100) NULL,
  `email` VARCHAR(1000) NULL,
  `date_updated` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_user_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `location` ;

CREATE TABLE IF NOT EXISTS `location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address_id` INT NOT NULL,
  `creator_id` INT NOT NULL,
  `date_updated` DATETIME NULL,
  `name` VARCHAR(500) NULL,
  `date_created` DATETIME NULL,
  `description` TEXT NULL,
  `google_place_id` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_location_address1_idx` (`address_id` ASC),
  INDEX `fk_location_usercreator_idx` (`creator_id` ASC),
  CONSTRAINT `fk_location_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_location_usercreator`
    FOREIGN KEY (`creator_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `review` ;

CREATE TABLE IF NOT EXISTS `review` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `location_id` INT NOT NULL,
  `content` TEXT NULL,
  `active` TINYINT NULL,
  `date_created` DATETIME NOT NULL,
  `date_updated` DATETIME NULL,
  `date_visited` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_user_idx` (`user_id` ASC),
  INDEX `fk_comment_location1_idx` (`location_id` ASC),
  CONSTRAINT `fk_comment_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rating` ;

CREATE TABLE IF NOT EXISTS `rating` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `category` VARCHAR(500) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `review_rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `review_rating` ;

CREATE TABLE IF NOT EXISTS `review_rating` (
  `rating_id` INT NOT NULL,
  `review_id` INT NOT NULL,
  `rating_value` INT NULL,
  PRIMARY KEY (`rating_id`, `review_id`),
  INDEX `fk_review_rating_rating1_idx` (`rating_id` ASC),
  INDEX `fk_review_rating_review_idx` (`review_id` ASC),
  CONSTRAINT `fk_review_rating_rating1`
    FOREIGN KEY (`rating_id`)
    REFERENCES `rating` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_rating_review`
    FOREIGN KEY (`review_id`)
    REFERENCES `review` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_favorite`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_favorite` ;

CREATE TABLE IF NOT EXISTS `user_favorite` (
  `user_id` INT NOT NULL,
  `location_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `location_id`),
  INDEX `fk_user_has_location_location1_idx` (`location_id` ASC),
  INDEX `fk_user_has_location_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_location_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_location_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `review_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `review_comment` ;

CREATE TABLE IF NOT EXISTS `review_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `review_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `content` TEXT NULL,
  `date_created` DATETIME NULL,
  `date_updated` DATETIME NULL,
  `review_rating` INT NULL,
  `reply_id` INT NULL,
  `active` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_review_comment_review1_idx` (`review_id` ASC),
  INDEX `fk_review_comment_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_review_comment_review1`
    FOREIGN KEY (`review_id`)
    REFERENCES `review` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS checkahead@localhost;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'checkahead'@'localhost' IDENTIFIED BY 'checkahead';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'checkahead'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `checkaheaddb`;
INSERT INTO `address` (`id`, `address`, `city`, `zip`, `state`) VALUES (1, '3970 S Hannibal St', 'Aurora', 80013, 'CO');
INSERT INTO `address` (`id`, `address`, `city`, `zip`, `state`) VALUES (2, '4271 South Buckley Road', 'Aurora', 80013, 'CO');
INSERT INTO `address` (`id`, `address`, `city`, `zip`, `state`) VALUES (3, '12200 E Mississippi Ave', 'Aurora', 80012, 'CO');
INSERT INTO `address` (`id`, `address`, `city`, `zip`, `state`) VALUES (4, '12800 E Mississippi Ave', 'Aurora', 80012, 'CO');
INSERT INTO `address` (`id`, `address`, `city`, `zip`, `state`) VALUES (5, '16746 E Smoky Hill Rd', 'Centennial', 80015, 'CO');
INSERT INTO `address` (`id`, `address`, `city`, `zip`, `state`) VALUES (6, '3440 S Tower Rd', 'Aurora', 80013, 'CO');
INSERT INTO `address` (`id`, `address`, `city`, `zip`, `state`) VALUES (7, '1200 S Buckley Rd', 'Aurora', 80017, 'CO');
INSERT INTO `address` (`id`, `address`, `city`, `zip`, `state`) VALUES (8, '3898 S Eagle St', 'Aurora', 80014, 'CO');
INSERT INTO `address` (`id`, `address`, `city`, `zip`, `state`) VALUES (9, '13728 E Quincy Ave', 'Aurora', 80015, 'CO');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `checkaheaddb`;
INSERT INTO `user` (`id`, `address_id`, `username`, `password`, `active`, `date_created`, `role`, `email`, `date_updated`) VALUES (1, 1, 'admin', '$2a$10$9hwcGWMMTAHGf776ilxDR.BlOEYzhNLy38F1GjdLlC86naOrD9qFe\n\n', true, '2020-04-28', 'admin', 'admin@admin.com', NULL);
INSERT INTO `user` (`id`, `address_id`, `username`, `password`, `active`, `date_created`, `role`, `email`, `date_updated`) VALUES (2, 3, 'carefulShopper', '$2a$10$RrDMn0It5d0rz2OfC5TkOu2rGO3z4KIEss6YqQTVfPS/JLQTueUSW\n\n', true, '2020-04-19', 'user', 'rob@robsoftheworld.com', NULL);
INSERT INTO `user` (`id`, `address_id`, `username`, `password`, `active`, `date_created`, `role`, `email`, `date_updated`) VALUES (3, 4, 'new', '$2a$10$JPluL5Brfa1sc.2Iw8YL/.ZsxxCJ2IWWEV2Gy8.2O5ucd4SHau8AK', true, '2020-04-28', 'admin', 'new@new.com', NULL);
INSERT INTO `user` (`id`, `address_id`, `username`, `password`, `active`, `date_created`, `role`, `email`, `date_updated`) VALUES (4, 8, 'JDavis', 'JDavis', true, '2020-04-10', 'user', 'jaydavis@gmail.com', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `location`
-- -----------------------------------------------------
START TRANSACTION;
USE `checkaheaddb`;
INSERT INTO `location` (`id`, `address_id`, `creator_id`, `date_updated`, `name`, `date_created`, `description`, `google_place_id`) VALUES (1, 2, 3, '2020-04-27', 'King Soopers', '2020-04-27', 'Has pharmacy, deli, frozen products, grab and go.', 'ChIJ5ZyMLQGJbIcRg8wCW2q6dx8');
INSERT INTO `location` (`id`, `address_id`, `creator_id`, `date_updated`, `name`, `date_created`, `description`, `google_place_id`) VALUES (2, 5, 3, '2020-04-27', 'Walmart ', '2020-04-27', 'Grocery delivery and fresh produce', 'ChIJgXdlxR6JbIcR8l3Vcs6w2gM');
INSERT INTO `location` (`id`, `address_id`, `creator_id`, `date_updated`, `name`, `date_created`, `description`, `google_place_id`) VALUES (3, 6, 3, '2020-02-15', 'Natural Grocers Aurora South', '2020-02-15', '100% Organic Produce, Body Care, Books, Bulk Foods, Dairy Products, Dietary Supplements, Frozen Products, Grab & Go, Grocery, Household Products, Organic Pet Products, Meat & Seafood', 'ChIJ956jnEWIbIcRqxLVZjPVHNY');
INSERT INTO `location` (`id`, `address_id`, `creator_id`, `date_updated`, `name`, `date_created`, `description`, `google_place_id`) VALUES (4, 7, 3, '2020-02-17', 'Safeway', '2020-04-27', 'Pharmacy, coinstar, FedEx dropoff, grocery delivery, Redbox and Western Union', 'ChIJU5F43GRibIcR9uY-58eK6KI');
INSERT INTO `location` (`id`, `address_id`, `creator_id`, `date_updated`, `name`, `date_created`, `description`, `google_place_id`) VALUES (5, 9, 4, '2020-03-27', 'Europa', '2020-02-12', 'Food products imported from all over Europe in a well-stocked storefront with a deli.', 'ChIJLUjJGmKIbIcR8RUORVd5pKU');

COMMIT;


-- -----------------------------------------------------
-- Data for table `review`
-- -----------------------------------------------------
START TRANSACTION;
USE `checkaheaddb`;
INSERT INTO `review` (`id`, `user_id`, `location_id`, `content`, `active`, `date_created`, `date_updated`, `date_visited`) VALUES (1, 1, 1, 'Looked great and speedy checkout', true, '2020-04-27', NULL, '2020-04-27');
INSERT INTO `review` (`id`, `user_id`, `location_id`, `content`, `active`, `date_created`, `date_updated`, `date_visited`) VALUES (2, 2, 2, 'Fast as usual and not too busy', true, '2020-04-27', NULL, '2020-04-27');
INSERT INTO `review` (`id`, `user_id`, `location_id`, `content`, `active`, `date_created`, `date_updated`, `date_visited`) VALUES (3, 3, 1, 'Not the best time to go at 6pm', true, '2020-04-27', NULL, '2020-04-27');
INSERT INTO `review` (`id`, `user_id`, `location_id`, `content`, `active`, `date_created`, `date_updated`, `date_visited`) VALUES (4, 3, 2, 'Lack of fresh produce this time around', true, '2020-04-27', NULL, '2020-04-27');
INSERT INTO `review` (`id`, `user_id`, `location_id`, `content`, `active`, `date_created`, `date_updated`, `date_visited`) VALUES (5, 3, 1, 'It was great', true, '2020-04-27', NULL, '2020-04-27');
INSERT INTO `review` (`id`, `user_id`, `location_id`, `content`, `active`, `date_created`, `date_updated`, `date_visited`) VALUES (6, 2, 1, 'It was a bad experience', true, '2020-04-27', NULL, '2020-04-27');
INSERT INTO `review` (`id`, `user_id`, `location_id`, `content`, `active`, `date_created`, `date_updated`, `date_visited`) VALUES (7, 1, 2, 'It was the best shopping experience of my life! Go early', true, '2020-04-27', NULL, '2020-04-27');
INSERT INTO `review` (`id`, `user_id`, `location_id`, `content`, `active`, `date_created`, `date_updated`, `date_visited`) VALUES (8, 2, 3, 'Favorite store', true, '2020-04-27', NULL, '2020-04-27');
INSERT INTO `review` (`id`, `user_id`, `location_id`, `content`, `active`, `date_created`, `date_updated`, `date_visited`) VALUES (9, 3, 3, 'This is my favorite', true, '2020-04-27', NULL, '2020-04-27');
INSERT INTO `review` (`id`, `user_id`, `location_id`, `content`, `active`, `date_created`, `date_updated`, `date_visited`) VALUES (10, 3, 4, 'Nothing really stood out', true, '2020-04-27', NULL, '2020-04-27');
INSERT INTO `review` (`id`, `user_id`, `location_id`, `content`, `active`, `date_created`, `date_updated`, `date_visited`) VALUES (11, 4, 5, 'Great little Russian market', true, '2020-04-25', NULL, '2020-04-25');
INSERT INTO `review` (`id`, `user_id`, `location_id`, `content`, `active`, `date_created`, `date_updated`, `date_visited`) VALUES (12, 2, 5, 'Favorite neighborhood market', true, '2020-04-29', NULL, '2020-04-29');

COMMIT;


-- -----------------------------------------------------
-- Data for table `rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `checkaheaddb`;
INSERT INTO `rating` (`id`, `category`) VALUES (1, 'cleanliness');
INSERT INTO `rating` (`id`, `category`) VALUES (2, 'traffic');
INSERT INTO `rating` (`id`, `category`) VALUES (3, 'checkout');
INSERT INTO `rating` (`id`, `category`) VALUES (4, 'stock');

COMMIT;


-- -----------------------------------------------------
-- Data for table `review_rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `checkaheaddb`;
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (1, 1, 5);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (2, 1, 7);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (3, 1, 8);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (4, 1, 7);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (1, 2, 5);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (2, 2, 7);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (3, 2, 9);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (4, 2, 8);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (1, 3, 5);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (2, 3, 3);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (3, 3, 2);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (4, 3, 5);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (1, 4, 3);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (2, 4, 5);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (3, 4, 6);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (4, 4, 7);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (1, 9, 6);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (2, 9, 7);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (3, 9, 6);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (4, 9, 9);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (1, 10, 2);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (2, 10, 6);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (3, 10, 6);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (4, 10, 6);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (1, 5, 8);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (2, 5, 7);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (3, 5, 8);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (4, 5, 7);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (1, 6, 9);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (2, 6, 8);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (3, 6, 7);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (4, 6, 8);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (1, 7, 6);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (2, 7, 7);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (3, 7, 8);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (4, 7, 9);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (1, 8, 7);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (2, 8, 6);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (3, 8, 8);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (4, 8, 9);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (1, 11, 8);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (2, 11, 9);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (3, 11, 9);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (4, 11, 9);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (1, 12, 9);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (2, 12, 9);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (3, 12, 9);
INSERT INTO `review_rating` (`rating_id`, `review_id`, `rating_value`) VALUES (4, 12, 9);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_favorite`
-- -----------------------------------------------------
START TRANSACTION;
USE `checkaheaddb`;
INSERT INTO `user_favorite` (`user_id`, `location_id`) VALUES (3, 1);
INSERT INTO `user_favorite` (`user_id`, `location_id`) VALUES (2, 1);
INSERT INTO `user_favorite` (`user_id`, `location_id`) VALUES (3, 3);
INSERT INTO `user_favorite` (`user_id`, `location_id`) VALUES (2, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `review_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `checkaheaddb`;
INSERT INTO `review_comment` (`id`, `review_id`, `user_id`, `content`, `date_created`, `date_updated`, `review_rating`, `reply_id`, `active`) VALUES (1, 2, 1, 'During my visit I saw many things that During my visit I saw many things that During my visit I saw many things that During my visit I saw many things that During my visit I saw many things that During my visit I saw many things that During my visit I saw many things that ', '2020-04-27', NULL, 0, NULL, true);
INSERT INTO `review_comment` (`id`, `review_id`, `user_id`, `content`, `date_created`, `date_updated`, `review_rating`, `reply_id`, `active`) VALUES (2, 2, 2, 'I found it very unclean this time around', '2020-04-27', NULL, 4, 1, true);
INSERT INTO `review_comment` (`id`, `review_id`, `user_id`, `content`, `date_created`, `date_updated`, `review_rating`, `reply_id`, `active`) VALUES (3, 2, 1, 'The prices seem pretty decent, and the store is well kept', '2020-04-27', NULL, 9, 1, true);
INSERT INTO `review_comment` (`id`, `review_id`, `user_id`, `content`, `date_created`, `date_updated`, `review_rating`, `reply_id`, `active`) VALUES (4, 1, 3, 'This was the greatests time at the store This was the greatests time at the storeThis was the greatests time at the storeThis was the greatests time at the storeThis was the greatests time at the storeThis was the greatests time at the storeThis was the greatests time at the store', '2020-04-27', NULL, 0, NULL, true);
INSERT INTO `review_comment` (`id`, `review_id`, `user_id`, `content`, `date_created`, `date_updated`, `review_rating`, `reply_id`, `active`) VALUES (5, 1, 2, 'I had the same experience', '2020-04-27', NULL, 3, 4, true);
INSERT INTO `review_comment` (`id`, `review_id`, `user_id`, `content`, `date_created`, `date_updated`, `review_rating`, `reply_id`, `active`) VALUES (6, 10, 4, 'Friendly staff with a distinct Russian-style of tending to customers.', '2020-04-27', NULL, 9, NULL, true);
INSERT INTO `review_comment` (`id`, `review_id`, `user_id`, `content`, `date_created`, `date_updated`, `review_rating`, `reply_id`, `active`) VALUES (7, 11, 3, 'I am absolutely in love with this place! It reminds of all the wonderful treats my grandmother used to bring me home from Lithuania and Russia', '2020-04-28', NULL, 7, 10, true);

COMMIT;

