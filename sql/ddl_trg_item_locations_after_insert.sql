USE `argos`;

DELIMITER $$

DROP TRIGGER IF EXISTS argos.item_location_AFTER_INSERT$$
USE `argos`$$
CREATE TRIGGER `argos`.`item_location_AFTER_INSERT` AFTER INSERT ON `item_location`
FOR EACH ROW
begin
    update argos.item set existence = (select sum(quantity) from argos.item_location where item_id = new.item_id) where item_id = new.item_id;
end;$$
DELIMITER ;
