INSERT INTO user(id, name) VALUES(1, 'Dana White');

INSERT INTO camera(id, url, description, latitude, longitude, max_cars, cur_cars, img_count, height, width) VALUES(1, 'http://93.92.206.45:555/zEKZnYak?container=mjpeg&stream=main', 'камера на улице', 60.0111199, 30.3496216, 10, 0, 0, 0, 0);
INSERT INTO camera(id, url, description, latitude, longitude, max_cars, cur_cars, img_count, height, width) VALUES(2, 'http://93.92.207.123:555/REtex9Cn?container=mjpeg&stream=main', 'тореза', 60.0111299, 30.3392216, 10, 0, 0, 0, 0);
--INSERT INTO camera(id, url, description, latitude, longitude, max_cars, cur_cars, img_count, height, width) VALUES(3, 'http://10.100.60.37:6888/video/live.mjpg?id=91c25c80-e691-0993-5fa1-d81dc4b624d4', 'телефон', 60.0111399, 30.3293216, 10, 0, 0, 0, 0);

--INSERT INTO monitoring(user_id, left, top, right, bottom, camera_id, crate_date, is_active, is_here_theft) VALUES(1, 0, 1, 0, 1, 1, CURRENT_DATE(), true, false);
--INSERT INTO monitoring(user_id, left, top, right, bottom, camera_id, crate_date, is_active, is_here_theft) VALUES(1, 0, 1, 0, 1, 2, CURRENT_DATE(), true, false);