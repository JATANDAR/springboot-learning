drop table todo if exists;
create table todo(id bigint NOT NULL AUTO_INCREMENT, description VARCHAR(300), target_date DATE, isItDone BOOLEAN);