# Phone Book Web

### Used:
* Spring Boot
* Maven
* MySQL
* H2 for Integration Tests
* JavaScript and JQuery

### Before run: 
* Install Java
* Install Maven
* Install MySQL Server
* Create database in MySQL with name 'phoneBookDB'
* Change user's password and name for MySQL in application.yml

## To Build Project and Run Tests:
* command `mvn install` will build the project and run all integration tests

## SQL Scripts
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


[1]: web/src/main/resources/assets/main.jpeg
[2]: web/src/main/resources/assets/registration.jpeg
[3]: web/src/main/resources/assets/login.jpeg
[4]: web/src/main/resources/assets/add_new_contect.jpeg
[5]: web/src/main/resources/assets/edit_contact.jpeg
[6]: web/src/main/resources/assets/search_contact.jpeg

#### main page
![Alt text][1]

#### registration page
![Alt text][2]

#### login page
![Alt text][3]

#### add new contact
![Alt text][4]

#### edit contact
![Alt text][5]

#### search contact by name or mobile phone
![Alt text][6]