CREATE TRIGGER `argos`.`item_location_AFTER_INSERT` AFTER INSERT ON `item_location`
FOR EACH ROW
begin
    update argos.item set existence = (select sum(quantity) from argos.item_location where item_id = row.item_id) where item_id = row.item_id;
end;