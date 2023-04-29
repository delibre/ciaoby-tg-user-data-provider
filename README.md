# User Data Provider

## Overview

The **"User Data Provider"** is a microservice built with Spring Boot that uses a REST API to manage user data in a 
database. It has been designed to support the functionality of two Telegram bots, the 
[**"English Level Tester"**](https://github.com/delibre/tg-english-test-bot/) and 
[**"Admin Bot"**](https://github.com/delibre/tg-admin-bot).

When users interact with the [**"English Level Tester"**](https://github.com/delibre/tg-english-test-bot/) bot, their 
information is sent to the application's endpoints. Later, the [**"Admin Bot"**](https://github.com/delibre/tg-admin-bot)
can use this data to retrieve information about users.

### Technologies Used

* **Java17**
* **Spring Boot** v3.0.5
* **Spring Web** v3.0.5
* **Spring Data JPA** v3.0.5 
* **Spring Context Support** v3.0.5
* **MySQL Connector/J** v8.0.33
* **Lombok** v1.18.26


## Guidelines

How to run the application locally:

1. Download the most recent release of the project
2. Install **Docker** by following the instructions [here](https://docs.docker.com/engine/install/)
3. Run the **"docker-compose.yml"** file using your IDE or from the console using the following command:
```bash
docker compose up
```
4. Modify the application.yml file to meet your requirements. Verify that the port provided for the application is 
available
5. Launch the application
6. Download, install and launch the ["English Level Tester"](https://github.com/delibre/tg-english-test-bot) bot
7. Download, install and launch the ["Admin Bot"](https://github.com/delibre/tg-admin-bot)


## Source Code Review

### Entity Implementation

The "User" class is a Hibernate entity that is utilized to store user data. It contains the following properties:

```java
@Entity
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    @Column(unique=true)
    private String phone;

    private String username;

    private String referral;

    private String englishLvl;

    private String numOfCorrectAnswers;

    @Temporal(TemporalType.TIMESTAMP)
    private Date testCompletionDate;

    @Column(unique=true)
    private String chatId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date additionDateTime;
    
}
```

To prevent data overload transmitted between microservices, a **Data Transfer Object** (DTO) was created to provide only
the necessary information from the **"User"** entity.


### API Implementation

The API methods have been moved to a separate class called **"UserService"** to make the code clearer. Here are the 
essential endpoints:

* The **"/last-hour"** endpoint retrieves all users created during the last hour.
```java
@GetMapping("/last-hour")
public ResponseEntity<List<UserDto>> getUsersForTheLastHour() {
    return ResponseEntity.ok(userService.getUsersForTheLastHour());
}
```
This is implemented by calling the **"getUsersForTheLastHour"** method in the **"UserService"** class, which retrieves
the users from the database using the **"userRepository"** and returns them as a list of **"UserDto"** objects

* The **"/last-day"** endpoint retrieves all users created during the previous day.
```java
@GetMapping("/last-day")
public ResponseEntity<List<UserDto>> getUsersForTheLastDay() {
    return ResponseEntity.ok(userService.getUsersForTheLastDay());
}
```
This is implemented by calling the **"getUsersForTheLastDay"** method in the **"UserService"** class, which retrieves
the users from the database using the **"userRepository"** and returns them as a list of **"UserDto"** objects.

* The **"/update-contactinfo/{chatId}"** endpoint updates the user's name, surname, phone number, and information about
how the user heard about the school.
```java
@PutMapping("/update-contactinfo/{chatId}")
public ResponseEntity<UserDto> updateContactInfo(@PathVariable String chatId, @RequestBody UserDto updateUser) {
    return ResponseEntity.ok(userService.updateContactInfo(chatId, updateUser));
}
```
This is implemented by calling the **"updateContactInfo"** method in the **"UserService"** class, which retrieves the
user from the database using the **"userRepository"** and updates the relevant fields with the new information provided
in the **"UserDto"** object.

* The **"/update-testinfo/{chatId}"** endpoint updates the user's English level, number of correct answers, and test 
completion date.
```java
@PutMapping("/update-testinfo/{chatId}")
public ResponseEntity<UserDto> updateTestInfo(@PathVariable String chatId, @RequestBody UserDto updateUser) {
    return ResponseEntity.ok(userService.updateTestInfo(chatId, updateUser));
}
```
This is implemented by calling the **"updateTestInfo"** method in the **"UserService"** class, which retrieves the user 
from the database using the **"userRepository"** and updates the relevant fields with the new information provided in 
the **"UserDto"** object.


## License

MIT

The code in this repository is covered by the included license.