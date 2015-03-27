USE `inventory`;

INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('1', 'Ambiente', '', NULL);
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('2', 'Electrodomésticos', 'Electrodomésticos', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('3', 'Campanas', 'Campanas', '2');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('4', 'Estufas', 'Estufas', '2');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('5', 'Refrigeradores', 'Refrigeradores', '2');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('6', 'Licuadoras', 'Licuadoras', '2');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('7', 'Tostadores', 'Tostadores', '2');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('8', 'Extractores', 'Extractores', '2');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('9', 'Batidora', 'Batidora', '2');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('10', 'Hornos', 'Hornos', '2');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('11', 'Sandwicheras', 'Sandwicheras', '2');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('12', 'Parrillas eléctricas', 'Parrillas eléctricas', '2');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('13', 'Dispensador de agua', 'Dispensador de agua', '2');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('14', 'Cafeteras', 'Cafeteras', '2');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('15', 'Lavadora', 'Lavadora', '2');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('16', 'Climas', 'Climas (calefactor, ventilador, aire acondicionado)', '2');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('17', 'Cocina', 'Cocina', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('18', 'Canastos', 'Canastos', '17');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('19', 'Utensilios de cocina', 'Utensilios de cocina', '17');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('20', 'Ollas', 'Ollas', '17');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('21', 'Contenedores', 'Contenedores', '17');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('22', 'Plásticos', 'Plásticos', '21');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('23', 'Cerámica', 'Cerámica', '21');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('24', 'Madera', 'Madera', '21');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('25', 'Cristal', 'Cristal', '21');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('26', 'Metal', 'Metal', '21');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('27', 'Especieros', 'Especieros', '17');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('28', 'Repostería', 'Repostería', '17');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('29', 'Jarras', 'Jarras', '17');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('30', 'Fruteros', 'Fruteros', '17');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('31', 'Paneras', 'Paneras', '17');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('32', 'Dispensador de agua', 'Dispensador de agua', '17');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('33', 'Tablas de picar', 'Tablas de picar', '17');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('34', 'Trapos de cocina', 'Trapos de cocina', '17');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('35', 'Guantes y agarraderas', 'Guantes y agarraderas', '17');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('36', 'Contenedores', 'Contenedores', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('37', 'Metal', 'Metal', '36');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('38', 'Plástico', 'Plástico', '36');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('39', 'Madera', 'Madera', '36');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('40', 'Instalaciones', 'Instalaciones', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('41', 'Eléctrico', 'Eléctrico', '40');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('42', 'Sanitario', 'Sanitario', '40');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('43', 'Hidráulico', 'Hidráulico', '40');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('44', 'Funerario- Cementerio', 'Funerario- Cementerio', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('45', 'Religioso', 'Religioso', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('46', 'Hospital', 'Hospital', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('47', 'Escolar', 'Escolar', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('48', 'Artículos de escritorio y oficina', 'Artículos de escritorio y oficina', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('49', 'Lotes de impresos,audio y video', 'Lotes de impresos,audio y video', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('50', 'Lote de Archivo muerto', 'Lote de Archivo muerto', '49');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('51', 'Lote de revistas', 'Lote de revistas', '49');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('52', 'Lote de periódico', 'Lote de periódico', '49');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('53', 'Libros', 'Libros', '49');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('54', 'DVD', 'DVD', '49');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('55', 'CD', 'CD', '49');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('56', 'Acetatos', 'Acetatos', '49');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('57', 'Casettes', 'Casettes', '49');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('58', 'VHS', 'VHS', '49');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('59', 'BETTA', 'BETTA', '49');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('60', 'Baño', 'Baño', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('61', 'Cuadros', 'Cuadros', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('62', 'Repisas', 'Repisas', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('63', 'Portaretratos', 'Portaretratos', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('64', 'Bebés', 'Bebés', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('65', 'Juguetes y juegos', 'Juguetes y juegos', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('66', 'Electrónicos', 'Electrónicos', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('67', 'Plasmas', 'Plasmas', '66');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('68', 'Tv', 'Tv', '66');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('69', 'Pantallas', 'Pantallas', '66');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('70', 'Estereos', 'Estereos', '66');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('71', 'Grabadoras', 'Grabadoras', '66');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('72', 'Radios', 'Radios', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('73', 'Antiguos', 'Antiguos', '72');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('74', 'Contemporáneos', 'Contemporáneos', '72');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('75', 'Bocinas', 'Bocinas', '66');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('76', 'Consolas', 'Consolas', '66');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('77', 'Reproductor de DVD', 'Reproductor de DVD', '66');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('78', 'Videocaseteras', 'Videocaseteras', '66');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('79', 'Teléfonos', 'Teléfonos', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('80', 'Fijos', 'Fijos', '79');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('81', 'Fax', 'Fax', '79');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('82', 'Interfone', 'Interfone', '79');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('83', 'Pared', 'Pared', '79');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('84', 'Públicos', 'Públicos', '79');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('85', 'Percheros', 'Percheros', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('86', 'Espejos', 'Espejos', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('87', 'Pie', 'Pie', '86');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('88', 'Pared', 'Pared', '86');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('89', 'Mesa', 'Mesa', '86');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('90', 'Relojes', 'Relojes', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('91', 'Pie', 'Pie', '90');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('92', 'Pared', 'Pared', '90');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('93', 'Mesa', 'Mesa', '90');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('94', 'Celosías', 'Celosías', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('95', 'Chimeneas', 'Chimeneas', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('96', 'Tapetes', 'Tapetes', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('97', 'Chicos', 'Chicos', '96');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('98', 'Medianos', 'Medianos', '96');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('99', 'Grandes', 'Grandes', '96');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('100', 'Unilas', 'Unilas', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('101', 'Letreros', 'Letreros', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('102', 'Luminosos', 'Luminosos', '101');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('103', 'Señalética', 'Señalética', '101');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('104', 'Figuras Decorativas', 'Figuras Decorativas', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('105', 'Floreros', 'Floreros', '104');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('106', 'Esculturas', 'Esculturas', '104');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('107', 'Figuras Porcelanas', 'Figuras Porcelanas', '104');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('108', 'Jarrones', 'Jarrones', '104');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('109', 'Cristalería', 'Cristalería', '104');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('110', 'Mobiles colgantes', 'Mobiles colgantes', '104');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('111', 'Esferas', 'Esferas', '104');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('112', 'Ceniceros', 'Ceniceros', '104');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('113', 'Luminarias', 'Luminarias', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('114', 'Lámparas', 'Lámparas', '113');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('115', 'Linternas', 'Linternas', '114');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('116', 'De pie', 'De pie', '114');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('117', 'Mesa', 'Mesa', '114');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('118', 'De buró', 'De buró', '114');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('119', 'Arbotantes', 'Arbotantes', '114');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('120', 'Colgantes', 'Colgantes', '114');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('121', 'Velas', 'Velas', '113');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('122', 'Porta velas', 'Porta velas', '121');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('123', 'Candiles', 'Candiles', '121');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('124', 'Faroles', 'Faroles', '121');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('125', 'Antorchas', 'Antorchas', '121');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('126', 'Telas, telones', 'Telas, telones', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('127', 'Catres', 'Catres', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('128', 'Ocios talleres', 'Ocios talleres', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('129', 'Herramientas-Máquinas', 'Herramientas-Máquinas', '128');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('130', 'Flores y plantas artificiales', 'Flores y plantas artificiales', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('131', 'Flores', 'Flores', '130');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('132', 'Plantas', 'Plantas', '130');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('133', 'Macetas', 'Macetas', '130');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('134', 'Botes de basura', 'Botes de basura', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('135', 'Baules', 'Baules', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('136', 'Deportivos', 'Deportivos', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('137', 'Servicio de bar', 'Servicio de bar', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('138', 'Servicio de cafetería', 'Servicio de cafetería', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('139', 'Servicio de mesa', 'Servicio de mesa', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('140', 'Vasos', 'Vasos', '139');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('141', 'Copas', 'Copas', '139');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('142', 'Vajillas', 'Vajillas', '139');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('143', 'Cubiertos', 'Cubiertos', '139');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('144', 'Servilleteros', 'Servilleteros', '139');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('145', 'Bajo platos', 'Bajo platos', '139');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('146', 'Charolas', 'Charolas', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('147', 'Blancos', 'Blancos', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('148', 'Cama', 'Cama', '147');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('149', 'Sábanas', 'Sábanas', '148');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('150', 'King size', 'King size', '149');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('151', 'Queen size', 'Queen size', '149');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('152', 'Matrimonial', 'Matrimonial', '149');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('153', 'Individual', 'Individual', '149');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('154', 'Cuna', 'Cuna', '149');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('155', 'Edredones', 'Edredones', '148');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('156', 'King size', 'King size', '155');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('157', 'Queen size', 'Queen size', '155');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('158', 'Matrimonial', 'Matrimonial', '155');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('159', 'Individual', 'Individual', '155');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('160', 'Cuna', 'Cuna', '155');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('161', 'Duvet', 'Duvet', '148');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('162', 'King size', 'King size', '161');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('163', 'Queen size', 'Queen size', '161');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('164', 'Matrimonial', 'Matrimonial', '161');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('165', 'Individual', 'Individual', '161');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('166', 'Cuna', 'Cuna', '161');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('167', 'Cobijas', 'Cobijas', '148');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('168', 'King size', 'King size', '167');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('169', 'Queen size', 'Queen size', '167');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('170', 'Matrimonial', 'Matrimonial', '167');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('171', 'Individual', 'Individual', '167');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('172', 'Cuna', 'Cuna', '167');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('173', 'Colchonetas', 'Colchonetas', '148');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('174', 'Frazadas', 'Frazadas', '148');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('175', 'Almohadas', 'Almohadas', '148');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('176', 'Mesa', 'Mesa', '147');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('177', 'Servilletas', 'Servilletas', '176');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('178', 'Manteles', 'Manteles', '176');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('179', 'Redondos', 'Redondos', '178');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('180', 'Cuadrados', 'Cuadrados', '178');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('181', 'Rectangulares', 'Rectangulares', '178');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('182', 'Individuales', 'Individuales', '178');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('183', 'Caminos de mesa', 'Caminos de mesa', '176');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('184', 'Carpetas', 'Carpetas', '176');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('185', 'Cojines', 'Cojines', '147');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('186', 'Cortinas', 'Cortinas', '147');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('187', 'Toallas', 'Toallas', '147');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('188', 'Equipo de cómputo', 'Equipo de cómputo', '147');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('189', 'Computadoras de escritorio', 'Computadoras de escritorio', '188');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('190', 'Scanner', 'Scanner', '188');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('191', 'impresora', 'impresora', '188');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('192', 'Copiadora', 'Copiadora', '188');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('193', 'Adornos temáticos', 'Adornos temáticos', '1');
INSERT INTO `inventory`.`category` (`category_id`, `name`, `description`, `parent_category`) VALUES ('194', 'Varios', 'Varios', '1');
