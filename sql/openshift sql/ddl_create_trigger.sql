DELIMITER $$

DROP TRIGGER IF EXISTS inventory.item_location_AFTER_INSERT$$
USE `inventory`$$
CREATE TRIGGER `inventory`.`item_location_AFTER_INSERT` AFTER INSERT ON `inventory`.`item_location`
FOR EACH ROW
begin
    update inventory.item set existence = (select sum(quantity) from inventory.item_location where item_id = new.item_id) where item_id = new.item_id;
end;$$

DELIMITER ;