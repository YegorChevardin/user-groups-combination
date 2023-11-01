-- -----------------------------------------------------
-- Table `teams`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `teams`
(
    `id`   INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `users`
(
    `id`          INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(45)  NOT NULL,
    `second_name` VARCHAR(45)  NOT NULL,
    `team_id`    INT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    INDEX `fk_users_teams_idx` (`team_id` ASC) VISIBLE,
    CONSTRAINT `fk_users_teams`
        FOREIGN KEY (`team_id`)
            REFERENCES `teams` (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `users_combinations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `users_combinations`
(
    `id`             INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `first_user_id`  INT UNSIGNED NOT NULL,
    `second_user_id` INT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    INDEX `fk_users_combinations_users1_idx` (`first_user_id` ASC) VISIBLE,
    INDEX `fk_users_combinations_users2_idx` (`second_user_id` ASC) VISIBLE,
    CONSTRAINT `fk_users_combinations_users1`
        FOREIGN KEY (`first_user_id`)
            REFERENCES `users` (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_users_combinations_users2`
        FOREIGN KEY (`second_user_id`)
            REFERENCES `users` (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `marks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marks`
(
    `id`   INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `date` DATE,
    `mark` INT,
    `user_id` INT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_users_ids`
        FOREIGN KEY (`user_id`)
            REFERENCES `users` (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;
