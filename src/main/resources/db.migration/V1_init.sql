drop table owner_roles;
drop table roles;
drop table cat_friends;
drop table cat;
drop table owner;
drop sequence owner_seq;

CREATE TABLE Owner (
                       id BIGINT PRIMARY KEY,
                       name VARCHAR(255),
                       birthdate DATE,
                       password VARCHAR(255)
);

CREATE TABLE Cat (
                     id BIGINT PRIMARY KEY,
                     name VARCHAR(255),
                     birthdate DATE,
                     breed VARCHAR(255),
                     color VARCHAR(255),
                     owner_id BIGINT,
                     FOREIGN KEY (owner_id) REFERENCES Owner(id)
);

CREATE TABLE Cat_Friends (
                             cat_id BIGINT,
                             friend_id BIGINT,
                             PRIMARY KEY (cat_id, friend_id),
                             FOREIGN KEY (cat_id) REFERENCES Cat(id),
                             FOREIGN KEY (friend_id) REFERENCES Cat(id)
);

CREATE TABLE Roles (
                       id BIGINT PRIMARY KEY,
                       name VARCHAR(255)
);

CREATE TABLE Owner_Roles (
                             owner_id INT,
                             role_id BIGINT,
                             PRIMARY KEY (owner_id, role_id),
                             FOREIGN KEY (owner_id) REFERENCES Owner(id),
                             FOREIGN KEY (role_id) REFERENCES Roles(id)
);

CREATE SEQUENCE owner_seq START 20;


INSERT INTO Owner (id, name, birthdate) VALUES
                                            (1, 'Владелец 1', '1990-01-01'),
                                            (2, 'Владелец 2', '1985-05-15'),
                                            (3, 'Владелец 3', '1998-12-20');

INSERT INTO Cat (id, name, birthdate, breed, color, owner_id) VALUES
                                                                  (1, 'Котик 1', '2020-02-15', 'Британская', 'Серый', 1),
                                                                  (2, 'Котик 2', '2019-05-20', 'Мейн-кун', 'Тигровый', 2),
                                                                  (3, 'Котик 3', '2021-10-10', 'Персидская', 'Белый', 3);

INSERT INTO Cat_Friends (cat_id, friend_id) VALUES
                                                (1, 2),
                                                (2, 3),
                                                (3, 1);


INSERT INTO Roles (id, name) VALUES
                                 (1, 'ROLE_USER'),
                                 (2, 'ROLE_ADMIN'),
                                 (3, 'ROLE_OWNER');
