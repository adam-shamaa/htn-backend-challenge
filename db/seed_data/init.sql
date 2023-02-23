CREATE DATABASE db;
USE db;

CREATE TABLE user(
                     id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                     name VARCHAR(255),
                     email VARCHAR(255),
                     phone VARCHAR(255)
);

CREATE TABLE hacker(
                       id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                       company VARCHAR(100),
                       user_id BIGINT,
                       FOREIGN KEY (user_id) REFERENCES user(id)
);


CREATE TABLE hackerskill(
                      id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                      skill VARCHAR(100),
                      rating_ten INT,
                      hacker_id BIGINT,
                      FOREIGN KEY (hacker_id) REFERENCES hacker(id)
);

LOAD DATA INFILE '/var/lib/mysql-files/users.csv'
INTO TABLE user
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE '/var/lib/mysql-files/hackers.csv'
INTO TABLE hacker
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE '/var/lib/mysql-files/skills.csv'
INTO TABLE hackerskill
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;