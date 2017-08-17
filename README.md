Simple Web Phone Book.

Used:
* Spring Boot
* Maven
* MySQL
* H2 for Integration Tests
* JavaScript and JQuery

Before run: 
* Install MySQL Server
* Create database in MySQL with name 'phoneBookDB'
* Change user's password and name for MySQL in application.yml

SQL scripts for creating tables users and contacts:
```sql
create table users
(
	id int auto_increment primary key,
	full_name varchar(255) null,
	login varchar(255) null,
	password varchar(255) null,
	state varchar(255) null
);
```
```sql
create table contacts
(
	id int auto_increment primary key,
	address varchar(255) null,
	home_phone varchar(255) null,
	mail varchar(255) null,
	mobile_phone varchar(255) null,
	name varchar(255) null,
	surname varchar(255) null,
	user_id int references users (id),
);

```




