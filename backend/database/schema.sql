BEGIN TRANSACTION;

DROP TABLE IF EXISTS prize_user;
DROP TABLE IF EXISTS prizes;
DROP TABLE IF EXISTS activity;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) UNIQUE NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	is_parent boolean,
	family_id int,
	
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE books (
        isbn varchar(17) PRIMARY KEY,
        title varchar(200) NOT NULL,
        author varchar(1000) NOT NULL,
        thumbnail varchar(1000),
        page_count int,
        description varchar(10000)
);

CREATE TABLE activity (
        activity_id serial PRIMARY KEY,
        user_id int NOT NULL,
        isbn varchar NOT NULL,
        date_read date NOT NULL,
        minutes_read int default 0,
        is_finished boolean default false,
        media_type varchar(30),
        
        CONSTRAINT fk_activity_books FOREIGN KEY (isbn) REFERENCES books (isbn),
        CONSTRAINT fk_activity_users FOREIGN KEY (user_id) REFERENCES users (user_id)
);


CREATE TABLE prizes (
        prize_id serial PRIMARY KEY,
        prize_name varchar(100) NOT NULL,
        description varchar(300) NOT NULL,
        milestone int NOT NULL,
        family_id int, 
        max_prizes int NOT NULL,
        start_date date NOT NULL,
        end_date date NOT NULL,
        is_active boolean
);

CREATE TABLE prize_user (
        prize_id int,
        user_id int,
        
        CONSTRAINT fk_prize_user_users FOREIGN KEY (user_id) REFERENCES users (user_id),
        CONSTRAINT fk_prize_user_prizes FOREIGN KEY (prize_id) REFERENCES prizes (prize_id)
);

INSERT INTO users (username,password_hash,role, is_parent, family_id)
VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER',true, default);
INSERT INTO users (username,password_hash,role)
VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');






COMMIT TRANSACTION;
