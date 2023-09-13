INSERT INTO usuarios (email, pwd, nombre, apellido, direccion, id, enable, rol) VALUES ('b@gmaisd.com', '$2a$10$KU6kuGKGr0ugv6rA3h.0G.0UT0CMLeYkfgNM29c5MQPPoC40RLM42', 'Alejandro', 'Martinez', 'Calleasdafsg', 'a', 'true', 'USER');
INSERT INTO usuarios (email, pwd, nombre, apellido, direccion, id, enable, rol) VALUES ('a@gmaisd.com', '$2a$10$KU6kuGKGr0ugv6rA3h.0G.0UT0CMLeYkfgNM29c5MQPPoC40RLM42', 'Alejandro', 'Martinez', 'Calleasdafsg', 'b', 'true', 'USER');

INSERT INTO tipos (id, name) VALUES ('a', 'a');
INSERT INTO tipos (id, name) VALUES ('b', 'b');



INSERT INTO productos (id, cantidad, descripcion, enable, img, nombre, precio, tipo_producto_id) VALUES ('a', 22, 'as', 'true', 'img', 'a', 12.5, 'a');
INSERT INTO productos (id, cantidad, descripcion, enable, img, nombre, precio, tipo_producto_id) VALUES ('b', 22, 'b', 'true', 'img', 'b', 12.5, 'b');
INSERT INTO productos (id, cantidad, descripcion, enable, img, nombre, precio, tipo_producto_id) VALUES ('c', 22, 'c', 'true', 'img', 'c', 12.5, 'a');
INSERT INTO productos (id, cantidad, descripcion, enable, img, nombre, precio, tipo_producto_id) VALUES ('d', 22, 'd', 'true', 'img', 'd', 12.5, 'b');


INSERT INTO ordenes (id, agno, fecha_creacion, mes, total, usuario_id) VALUES ('a', '2023', '2023-07-01', 7, 12.5, 'a');
INSERT INTO ordenes (id, agno, fecha_creacion, mes, total, usuario_id) VALUES ('b', '2023', '2023-07-01', 7, 12.5, 'b');


INSERT INTO detalle_ordenes (id, cantidad, total, orden_id, producto_id) VALUES ('a', 1, 12.5, 'a', 'a');
INSERT INTO detalle_ordenes (id, cantidad, total, orden_id, producto_id) VALUES ('b', 1, 12.5, 'b', 'b');
