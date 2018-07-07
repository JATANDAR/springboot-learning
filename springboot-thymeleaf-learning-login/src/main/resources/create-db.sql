drop table todo if exists;
drop table users if exists;

create table todo(id bigint NOT NULL AUTO_INCREMENT, description VARCHAR(300), target_date DATE, isItDone BOOLEAN,  primary key (id));

create table users(id bigint NOT NULL AUTO_INCREMENT, name VARCHAR(300) NOT NULL, email_address VARCHAR(300) NOT NULL, password VARCHAR(255) NOT NULL, phone VARCHAR(20), primary key (id));