/*
-- Query: SELECT `authorities`.`username`,
    `authorities`.`authority`
FROM `inventory`.`authorities`
LIMIT 0, 1000

-- Date: 2015-02-26 16:01
*/
USE `inventory`;

INSERT INTO `authorities` (`username`,`authority`) VALUES ('jcarlos','ROLE_ADMIN');
INSERT INTO `authorities` (`username`,`authority`) VALUES ('usuario','ROLE_USER');
