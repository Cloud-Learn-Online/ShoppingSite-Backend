# Sample E-Commerce Application
This is a purely back end application implemented using Spring Boot and Rest API and Maven.
To access this Project following tools are needed Postman,Eclipse or IntelliJ, Java installed, MySql.
## Project Description:
Project has Three section Users,Items and Orders.
1. Only Admin user can create update and delete items.
2. Other user can see the list of Items and can order them.
3. User can see all the list of items which he/she has ordered.
4. User can not order the items which are out of stock.
5. User can order in bulk like same item more than or diffrent diffrent items more than one.

## This Project Requires a Bit of Setup:
#### Creating Tables in MySql:
1. Create Item Table: create table item(id int auto_increment primary key,item_name int,quantity int, item_price int);
2. Create Users Table: create table users(id int auto_increment primary key,user_name varchar(40),email varchar(40),user_type varchar(20) default 'Customer');
3. Create Orders Table: create table orders(id int auto_increment primary key,item_id int,quantity int, price int,order_id int,foreign key ifk(item_id) references item(id));
4. Create Order_User Table: create table order_user(order_id int unique not null primary key, users_id int, foreign key ufk(users_id) references users(id));
#### Add a Tomcat Server for your application using the IDE(eclipse,intelliJ)
#### Adding User id and Password of your DataBase to the application.properties file of the project:
Path: ShoppingSite\src\main\resources\application.properties
Put the DB name user name and password in the following lines.
spring.datasource.url=jdbc:mysql://localhost:3306/<DB_NAME>
spring.datasource.username=<USER_NAME>
spring.datasource.password=<PASSWORD>
#### Refresh the project so that all the dependency gets downloaded properly.

## Run the Project!
1. Run the ShoppingSiteApplication.java file which is under com.anish.ShoppingSite.
2. Application will start and can be accessible using localhost:8080.
3. Open Postman and Hit the apis.
#### List of APIs.
1. To Create/Update/Delete/GetAll Users
		
		http://localhost:8080/users/list
		
		http://localhost:8080/users/create       JSON DATA: {"user_name": "Anish Prasad","email": "anish8129@gmail.com","user_type": "Admin"}
		
		http://localhost:8080/users/update       JSON DATA: {"id":1,"user_name": "Anish Prasad","email": "anish8129@gmail.com","user_type": "Admin"}
		
		http://localhost:8080/users/<user_id>

2. To Create/Update/Delete/GetAll Item
		
		http://localhost:8080/<user_id>/Items/list
		
		http://localhost:8080/<user_id>/Items/create       JSON DATA: {"item_name": "Pen","item_price": 5,"quantity": 30}
		
		http://localhost:8080/<user_id>/Items/update       JSON DATA: {"id":1,"item_name": "Pen","item_price": 5,"quantity": 30}
		
		http://localhost:8080/<user_id>/Items/<item_id>

3. To Create Order and to get the orders done by user.
 		
		http://localhost:8080/<user_id>/order/create      {"items":[{"id":8,"item_name": "Powder","item_price": 70,"quantity": 5},{"id":9,"item_name": "Pen","item_price": 5,"quantity": 20}],"price": 450}

		http://localhost:8080/<user_id>/order/list
		
## Expected Output:
   
1. Response will be in json with message.

2. If User is admin then only creation or deletion or updation in item will happend other wise message will come as cannot authorized.

3. After creation of Order order id will be returned in JSON. If the item is Out of Stock then order request will fail and it will tell because of which item it failed.
   Order/list api will return the number of order made py the user with all the item details.
   
##Note:
 Login Log Out features has not been implemented, neither the view (jsp or html) has been created.
  


 
