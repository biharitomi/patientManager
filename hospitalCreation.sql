DROP DATABASE IF EXISTS patientManager;
create database patientManager;

use patientManager;

CREATE TABLE doctor (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name CHAR (30) NOT NULL,
	PRIMARY KEY (id),
	INDEX (name)

) DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci, ENGINE=InnoDB;

CREATE TABLE patient (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name CHAR (30) NOT NULL,
	mobileNumber CHAR(20) NOT NULL,
	PRIMARY KEY (id),
	INDEX (name)

) DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci, ENGINE=InnoDB;

SET NAMES utf8;

INSERT INTO doctor (name) VALUES
    ('Antony'),('Patrick'),('Sally'),
    ('Dolly'),('Nelly'),('Molly');

INSERT INTO patient (name, mobileNumber) VALUES
    ('Melissa','+36301233233'),('Doris', '+36301233222'),('Sandy', '+36301233244'),
	('Jamaar','+36311133233'),('Garick', '+36322233222'),('Cy', '+36333333244'),
	('Gord','+36366633233'),('Kaiden', '+36355533222'),('Skylar', '+36344433244'),
    ('Micike', '+36301233277'),('Kicsi András Béla', '+36301233266'),('Sanyibá', '+36301233255');

CREATE TABLE appointment (
	id BIGINT NOT NULL AUTO_INCREMENT,
	date Date NOT NULL,
	doctor_id BIGINT,
	patient_id BIGINT,
	PRIMARY KEY (id),
	FOREIGN KEY (patient_id) REFERENCES patient(id),
	FOREIGN KEY (doctor_id) REFERENCES doctor(id)

) DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci, ENGINE=InnoDB;