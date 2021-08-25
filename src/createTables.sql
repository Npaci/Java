USE TECHNOBEL;

CREATE TABLE technobel.option (
	id_option BIGINT auto_increment NOT NULL,
	nom varchar(50) NOT NULL,
	prix DOUBLE NOT NULL,
	CONSTRAINT option_pk PRIMARY KEY (id_option)
)
--***** pour DBEAVER
--ENGINE=InnoDB
--DEFAULT CHARSET=utf8mb4
--COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE technobel.marque (
    ID_MARQUE BIGINT AUTO_INCREMENT NOT NULL,
    NOM VARCHAR(20) NOT NULL,
    CONSTRAINT marque_pk PRIMARY KEY(ID_MARQUE)
)
--***** pour DBEAVER
--ENGINE=InnoDB
--DEFAULT CHARSET=utf8mb4
--COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE technobel.modele (
	id_modele BIGINT auto_increment NOT NULL,
	nom varchar(50) NOT NULL,
	marque_id BIGINT not null,
	constraint modele_fk foreign key (marque_id) references technobel.marque(id_marque),
	CONSTRAINT modele_pk PRIMARY KEY (id_modele)
)
--***** pour DBEAVER
--ENGINE=InnoDB
--DEFAULT CHARSET=utf8mb4
--COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE technobel.voiture (
	id_voiture BIGINT auto_increment NOT NULL,
	modele_id BIGINT NOT NULL,
	prix DOUBLE not null,
	couleur VARCHAR(20) not null,
	carburant VARCHAR(10) not null,
	kilometre DOUBLE not null,
	constraint voiture_fk foreign key (modele_id) references technobel.modele(id_modele),
	CONSTRAINT voiture_pk PRIMARY KEY (id_voiture)
)
--***** pour DBEAVER
--ENGINE=InnoDB
--DEFAULT CHARSET=utf8mb4
--COLLATE=utf8mb4_0900_ai_ci;

--CREATE TABLE technobel.voiture_option (
--	voiture_id BIGINT not null,
--	option_id BIGINT not null,
--	constraint voiture_option_fk1 foreign key (voiture_id) references technobel.voiture(id_voiture),
--	constraint voiture_option_fk2 foreign key (option_id) references technobel.option(id_option),
--	CONSTRAINT voiture_pk PRIMARY KEY (voiture_id, option_id)
--)
CREATE TABLE technobel.voiture_option (
	id_voiture_option BIGINT auto_increment not null,
	voiture_id BIGINT not null,
	option_id BIGINT not null,
	constraint voiture_option_fk1 foreign key (voiture_id) references technobel.voiture(id_voiture),
	constraint voiture_option_fk2 foreign key (option_id) references technobel.option(id_option),
	CONSTRAINT voiture_pk PRIMARY KEY (id_voiture_option)
)
--***** pour DBEAVER
--ENGINE=InnoDB
--DEFAULT CHARSET=utf8mb4
--COLLATE=utf8mb4_0900_ai_ci;


//Insertion

insert into technobel.`option` (id_option, nom, prix) values (1, 'ABS', 2000.0),
(2, 'Bluetooth', 200),
(3, 'Airco', 1000),
(4, 'Sièges chauffants', 2000),
(5, 'Caméra de recul', 2500),
(6, 'Capteurs de recul', 1000),
(7, 'GPS', 2000);

INSERT INTO technobel.modele (id_modele, nom, marque_id)
VALUES(1, 'Fiesta', 1),
(2, 'Serie 1', 2),
(3, 'Polo', 3),
(4, 'A1', 4),
(5, 'Class A', 5);


INSERT INTO technobel.voiture_option (voiture_id, option_id)
VALUES(1, 1),
(1, 2),
(1, 6);