/*
-- Query: SELECT `users`.`username`,
    `users`.`password`,
    `users`.`enabled`
FROM `inventory`.`users`
LIMIT 0, 1000

-- Date: 2015-02-26 16:06
*/

USE `inventory`;

INSERT INTO `users` (`username`,`password`,`enabled`) VALUES ('jcarlos','$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.',1);
INSERT INTO `users` (`username`,`password`,`enabled`) VALUES ('usuario','$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.',1);
