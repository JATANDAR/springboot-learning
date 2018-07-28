drop table todo if exists;
drop table users if exists;

create table todo(id bigint NOT NULL AUTO_INCREMENT, description VARCHAR(300), target_date DATE, isItDone BOOLEAN,  PRIMARY KEY (id));

create table users(id bigint NOT NULL AUTO_INCREMENT, name VARCHAR(300) NOT NULL, email_address VARCHAR(300) NOT NULL, password VARCHAR(255) NOT NULL, phone VARCHAR(20), enabled BOOLEAN, PRIMARY KEY (id));

create table verificationtoken(id bigint NOT NULL AUTO_INCREMENT, token VARCHAR(50) NOT NULL, email_address varchar(300), foreign key(email_address) references users(email_address), PRIMARY KEY(id));

create table authorities(id bigint NOT NULL AUTO_INCREMENT, email_address VARCHAR(300) NOT NULL, authority VARCHAR(50) DEFAULT 'USER', FOREIGN KEY(email_address) REFERENCES users(email_address), PRIMARY KEY(id));