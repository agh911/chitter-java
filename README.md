# Chitter Java Server

## Table of Contents
1. [Overview](#overview)
2. [Approach](#approach)
3. [Technologies Used](#technologies-used)
4. [Domain Model](#domain-model)
5. [Application Data Flow Schema](#application-data-flow-schema)
6. [Testing](#testing)
7. [Future Improvements](#future-improvements)
8. [Credits](#credits)

## Overview
Chitter is a social media platform developed to address the need for a simple and user-friendly blogging space.The goal is to create a space where users could share their thoughts and engage with others in a concise manner, similar to popular platforms like Twitter, however potentially less overwhelming.

## Approach
Details the chosen approach to address the problem, including the architectural decisions, technologies used, and the overall development strategy.


### Technologies Used
- **Backend**
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Mockito](https://img.shields.io/badge/Mockito-78a63d?style=for-the-badge)
- **Frontend**
![JavaScript](https://img.shields.io/badge/JavaScript-323330?style=for-the-badge&logo=javascript&logoColor=F7DF1E)
![React](https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB)
- **Database**
![MongoDB](https://img.shields.io/badge/MongoDB-4EA94B?style=for-the-badge&logo=mongodb&logoColor=white)
- **API Communication**
![Axios](https://img.shields.io/badge/axios-007ACC?style=for-the-badge&logo=axios&logoColor=white)

## Getting Started

1. Clone this repository to your local machine.

2. Navigate to the project's Backend directory and start the backend server by running the following command:
   ```sh
   $ ./mvnw spring-boot:run
   ```
   
3. 2. Navigate to the project's Frontend directory and install dependencies:
   ```sh
   $ npm install
   ```

4. Start the frontend development server using the following command:
   ```sh
   $ npm run dev
   ```

To run the tests 
1. Navigate to the Backend directory and run the following command:
   ```sh
   $ ./mvnw test
   ```

To run the test coverage:
1. Make sure you are in the Backend directory and run the following command:
   ```sh
   $ ./mvnw clean test jacoco:report
   ```
2. Locate the index.html file (BackEnd/target/site/jacoco/index.html) right click on it and Open in Default Browser.

## Domain Model
| Object | Properties              | Methods                      | Output        |
|--------|-------------------------|------------------------------|---------------|
| Peep   | id@String               | getId()                      | String        |
|        | name@String             | getName(name@String)         | String        |
|        | username@String         | setName()                    | Void          |
|        | content@String          | getUsername()                | String        |
|        | createdAt@LocalDateTime | setUsername(username@String) | Void          |
|        |                         | getContent()                 | String        |
|        |                         | setContent(content@String)   | Void          |
|        |                         | getCreatedAt()               | LocalDateTime |
| User   | id@String               | getId()                      | String        |
|        | name@String             | getName()                    | String        |
|        | username@String         | setName(name@String)         | Void          |
|        | email@String            | getUsername()                | String        |
|        | password@String         | setUsername(username@String) | Void          |
|        |                         | getEmail()                   | String        |
|        |                         | setEmail(email@String)       | Void          |
|        |                         | getPassword()                | String        |
|        |                         | setPassword(password@String) | Void          |

## Application Data Flow Schema
| Description                                   | Method | Route                 | Data Response                                                     |
|-----------------------------------------------|--------|-----------------------|-------------------------------------------------------------------|
| Retrieve all peeps                            | GET    | /peeps                | List of Peep objects                                              |
| Add a new peep                                | POST   | /peeps                | Newly added Peep object                                           |
| Delete a peep by ID                           | DELETE | /peeps/{id}           | -                                                                 |
| Retrieve all users                            | GET    | /getUser              | List of User objects                                              |
| Retrieve user data by email (for sign-in)     | POST   | /getUser/signedInUser | User object if credentials are valid, otherwise error message     |
| Retrieve user by ID                           | GET    | /getUser/{id}         | User object                                                       |
| Sign in a user                                | POST   | /getUser/signIn       | Success message if sign-in is successful, otherwise error message |
| Sign up a new user                            | POST   | /getUser/signUp       | Newly added User object if successful, otherwise error message    |
| Delete a user by ID                           | DELETE | /getUser/{id}         | -                                                                 |


## Testing
#### Peep Controller
1. Test that Peep object creation and accessor methods work correctly.
2. Test that getAllPeeps returns the expected list.
3. Test that addPeep correctly adds a new peep.
4. Test that a specific peep can be deleted using deletePeep.

#### User Controller
1. Test that User object creation and accessor methods work correctly.
2. Test that getAllUsers returns the expected list.
3. Test that getUserData returns the correct user data.
4. Test that getUserData handles user not found scenario.
5. Test that getUserById returns the expected user.
6. Test that signIn is successful.
7. Test that signIn handles user not found scenario.
8. Test that signIn handles incorrect password scenario.
9. Test that setPassword method sets the password correctly in SignInRequest.
10. Test that signUp is successful.
11. Test that signUp handles username already exists scenario.
12. Test that signUp handles email already exists scenario.
13. Test that a specific user can be deleted using deleteUser .

#### Peep Service
1. Test that getAllPeeps returns the expected list.
2. Test that getPeepById returns the expected peep.
3. Test that addPeep correctly adds a new peep.
4. Test that deletePeep invokes the corresponding method in PeepRepository.

#### User Service
1. Test that getAllUsers returns the expected list.
2. Test that addUser correctly adds a new user.
3. Test that getUserById returns the expected user.
4. Test that getUserByEmail returns the expected user.
5. Test that getUserByUsername returns the expected user.
6. Test that deleteUser invokes the corresponding method in UserRepository.

## Future improvements
- Work on improving error handling both in the backend and frontend.
- Improve security by adding JWT.
- Add the logic to be able to delete users/peeps in the frontend side.

## Credits
[Spring Boot Guide](https://spring.io/guides/gs/spring-boot/)
[MVN Repositories](https://mvnrepository.com/)
[Using Mockito](https://www.vogella.com/tutorials/Mockito/article.html)