-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

-- Esse arquivo é executado automaticamente pelo Quarkus ao subir em modo dev ou test
-- Serve para popular o banco com dados iniciais, evitando que ele fique vazio a cada restart
-- A tabela "game" é criada pelo Hibernate automaticamente com base na entidade Game.java
-- Por isso não precisamos de CREATE TABLE aqui — só os INSERTs

-- Cada linha insere um jogo com: id, nome, nota, data de lançamento e URL da imagem
INSERT INTO game (id, name, rating, released, background_image) VALUES (3498, 'Grand Theft Auto V', 4.48, '2013-09-17', 'https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg');
INSERT INTO game (id, name, rating, released, background_image) VALUES (4200, 'Portal 2', 4.62, '2011-04-18', 'https://media.rawg.io/media/games/9bd/9bd33f7b6e9c4eabec6b1e93b5a2b8c9.jpg');
INSERT INTO game (id, name, rating, released, background_image) VALUES (3328, 'The Witcher 3: Wild Hunt', 4.67, '2015-05-18', 'https://media.rawg.io/media/games/0c3/0c3c8c8d2c0d9a4c9c8c5c3c5c7e3d7b.jpg');
INSERT INTO game (id, name, rating, released, background_image) VALUES (5286, 'Tomb Raider (2013)', 4.05, '2013-03-05', 'https://media.rawg.io/media/games/99b/99b7f2a7f5b3f2b2e6c7c8f2a7f5b3f2.jpg');
INSERT INTO game (id, name, rating, released, background_image) VALUES (13536, 'Portal', 4.51, '2007-10-09', 'https://media.rawg.io/media/games/c6b/c6bd26767c1053fef2b10bb852943559.jpg');
INSERT INTO game (id, name, rating, released, background_image) VALUES (5679, 'The Elder Scrolls V: Skyrim', 4.42, '2011-11-11', 'https://media.rawg.io/media/games/2f5/2f5f8b4f8e5c8e5c8e5c8e5c8e5c8e5c.jpg');
INSERT INTO game (id, name, rating, released, background_image) VALUES (4062, 'BioShock Infinite', 4.39, '2013-03-26', 'https://media.rawg.io/media/games/60b/60b1b6c8c8e5c8e5c8e5c8e5c8e5c8e5.jpg');
INSERT INTO game (id, name, rating, released, background_image) VALUES (28, 'Red Dead Redemption 2', 4.59, '2018-10-26', 'https://media.rawg.io/media/games/4d9/4d9c1b6c8c8e5c8e5c8e5c8e5c8e5c8e.jpg');
INSERT INTO game (id, name, rating, released, background_image) VALUES (802, 'Borderlands 2', 4.11, '2012-09-18', 'https://media.rawg.io/media/games/7e4/7e4c8c8e5c8e5c8e5c8e5c8e5c8e5c8e.jpg');
INSERT INTO game (id, name, rating, released, background_image) VALUES (3439, 'Life is Strange', 4.11, '2015-01-30', 'https://media.rawg.io/media/games/6d1/6d1c8c8e5c8e5c8e5c8e5c8e5c8e5c8e.jpg');