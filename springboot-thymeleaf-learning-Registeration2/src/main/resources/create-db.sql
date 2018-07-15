drop table todo if exists;
drop table users if exists;

create table todo(id bigint NOT NULL AUTO_INCREMENT, description VARCHAR(300), target_date DATE, isItDone BOOLEAN,  PRIMARY KEY (id));

create table users(id bigint NOT NULL AUTO_INCREMENT, name VARCHAR(300) NOT NULL, email_address VARCHAR(300) NOT NULL, password VARCHAR(255) NOT NULL, phone VARCHAR(20), enabled BOOLEAN, PRIMARY KEY (id));

create table verificationtoken(id bigint NOT NULL AUTO_INCREMENT, token VARCHAR(30) NOT NULL, expiry_date DATE, email_address varchar(300), foreign key(email_address) references users(email_address), PRIMARY KEY(id));