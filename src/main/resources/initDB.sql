drop table if exists points cascade;
create table points(
                       id serial primary key,
                       latitude numeric(9, 7),
                       longitude numeric(9, 7),
                       delivery_from time,
                       delivery_to time
);

insert into points(id, latitude, longitude, delivery_from, delivery_to) values (1, 50.4839843, 30.5350063, '10:00', '20:00');
insert into points(id, latitude, longitude, delivery_from, delivery_to) values (2, 50.4716034, 30.4831969, '14:00', '16:00');
insert into points(id, latitude, longitude, delivery_from, delivery_to) values (3, 50.4085094, 30.5713501, '12:00', '15:00');
insert into points(id, latitude, longitude, delivery_from, delivery_to) values (4, 50.4258262, 30.5716457, '19:00', '20:00');
insert into points(id, latitude, longitude, delivery_from, delivery_to) values (5, 49.9947277, 36.1457429, '10:00', '18:00');