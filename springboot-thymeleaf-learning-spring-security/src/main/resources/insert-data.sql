insert into todo(description, target_date, isItDone) values('Create embedded data', NOW(), true);
insert into todo(description, target_date, isItDone) values('never done', NOW(), false);
insert into todo(description, target_date, isItDone) values('ever done', NOW(), false);
insert into todo(description, target_date, isItDone) values('done danna done', NOW(), false);
insert into todo(description, target_date, isItDone) values('billalalala', NOW(), false);
insert into todo(description, target_date, isItDone) values('lalalala', NOW(), false);
insert into todo(description, target_date, isItDone) values('hello world', NOW(), false);


insert into users (name, email_address, password, enabled) values ('First Last', 'test@test.com','test', true);
insert into users (name, email_address, password, enabled) values ('admin User', 'testAdminUser@test.com', 'test', true);
insert into users (name, email_address, password, phone, enabled) values ('shop Owner', 'testshopOwner@test.com', 'test', '1234567890', true);
insert into users (name, email_address, password, phone, enabled) values ('Charlie Bucket', 'charlie@packtpub.com', 'charlie', '1234567890', true);
insert into users (name, email_address, password, phone, enabled) values ('Jatandar Dhirwani', 'jatandar.dhirwani@gmail.com', 'charlie', '0424983104', true);


insert into authorities (email_address, authority) values ('test@test.com', 'USER');
insert into authorities (email_address, authority) values ('testAdminUser@test.com', 'USER');
insert into authorities (email_address, authority) values ('testshopOwner@test.com', 'USER');
insert into authorities (email_address, authority) values ('charlie@packtpub.com', 'ROLE_ADMIN');
insert into authorities (email_address, authority) values ('charlie@packtpub.com', 'USER');

insert into authorities (email_address, authority) values ('jatandar.dhirwani@gmail.com', 'ROLE_ADMIN');
insert into authorities (email_address, authority) values ('jatandar.dhirwani@gmail.com', 'DELETE');
insert into authorities (email_address, authority) values ('jatandar.dhirwani@gmail.com', 'READ');
insert into authorities (email_address, authority) values ('jatandar.dhirwani@gmail.com', 'WRITE');