use shopping_site;
select * from orders;

select * from item;

select * from users;

select * from order_user;
/*drop table order_user;*/

create table orders(id int auto_increment primary key,item_id int,quantity int, price int,order_id int,foreign key ifk(item_id) references item(id));

Create table users(id int auto_increment primary key,user_name varchar(40),email varchar(40),user_type varchar(20) default 'Customer');

Create table order_user(order_id int unique not null primary key, users_id int, foreign key ufk(users_id) references users(id));

insert into users values(2,'Nishtha','nishtha8129@gmail.com','Customer');

delete from orders where id > 0;

show tables;