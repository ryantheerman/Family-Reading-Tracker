BEGIN TRANSACTION;

DROP TABLE IF EXISTS prizes;
DROP TABLE IF EXISTS activity;
DROP TABLE IF EXISTS users_books;
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
        author varchar(100) NOT NULL
        
        -- Anything else we're getting from the api that we can use here?
);

CREATE TABLE activity (
        -- Corey's idea about composite key here... but for now
        activity_id serial PRIMARY KEY,
        user_id int NOT NULL,
        isbn varchar NOT NULL,
        date_read date NOT NULL,
        minutes_read int NOT NULL,
        is_finished boolean default false,
        
        -- pages read?
        -- to get individual books a user hasn't finished we might use group by or sort by date_read limit 1 (return most recent unfinished book)
        
        CONSTRAINT fk_activity_books FOREIGN KEY (isbn) REFERENCES books (isbn),
        CONSTRAINT fk_activity_users FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE prizes (
        prize_id serial PRIMARY KEY,
        prize_name varchar(100) NOT NULL,
        description varchar(300) NOT NULL,
        milestone int NOT NULL,
        family_id int NOT NULL, 
        max_prizes int NOT NULL,
        start_date date NOT NULL,
        end_date date NOT NULL,
        is_active boolean
        
        -- do we connect this to the rest of the database?
);

INSERT INTO users (username,password_hash,role, is_parent, family_id)
VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER',true, default);
INSERT INTO users (username,password_hash,role)
VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

-- Dummy data
INSERT INTO books (isbn, title, author)
VALUES ('9780575068568', 'Dune', 'Frank Herbert');
INSERT INTO books (isbn, title, author)
VALUES ('9780747532743', 'Harry Potter and the Sorcerers Stone', 'J. K. Rowling');
INSERT INTO books (isbn, title, author)
VALUES ('9780786863624', 'The Man Who Loved Only Numbers', 'Paul Hoffman');
INSERT INTO books (isbn, title, author)
VALUES ('9780060199067','Evolution: The Triumph of an Idea', 'Carl Zimmer');




COMMIT TRANSACTION;
