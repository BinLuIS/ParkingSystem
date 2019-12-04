# Parking System Backend

If you think this project is intersting or helpful, please give it a star.
## Background
This repository stores the source code of our parking system backend.

- Parking System Mobile Web App: https://binluis-parking-mobile.herokuapp.com
- Parking System Management Console: https://binluis-parkingwebapp.herokuapp.com/login
- Parking System Backend: https://parkingsystem.herokuapp.com
- Parking System Database: https://parkingsystem.herokuapp.com/h2-console (JDBC URL: jdbc:h2:file:./h2/binluis, User Name: sa, Blank Password)

This parking system is the final project of a bootcamp which we joint in Nov, 2018, we learnt several technology stacks as below

### Programming Languages
- Java
- Javascript

### Frameworks
- React JS
- Spring Boot

## Business Logic of Parking System
This web app is a inhouse tool of a multinational parking service provider.

Customers use our application to ask for car parking service in this web app, our internal parking clerks use this app to compete for car parking service to make extra money. On top of that, we have a management console for human resource management, parking lot management (i.e. which parking lot that a parking clerk will manage), car service request assignment (assign car service to some parking clerk)

## Business Flow (a.k.a Quickstart)
0. Spin Up the machines by browsing below URL</br>
   https://binluis-parking-mobile.herokuapp.com<br/>
   https://binluis-parkingwebapp.herokuapp.com/login<br/>
   https://parkingsystem.herokuapp.com<br/>
   https://parkingsystem.herokuapp.com/h2-console<br/>
1. Create a parking clerk account <br/>
   POST https://parkingsystem.herokuapp.com/api/auth/signup
   {"name":"clerk","username":"clerk","email":"clerk@email.com","password":"clerk","phoneNumber":"12345678","role":"PARKINGCLERK"}
2. Create a manager account<br/>
   POST https://parkingsystem.herokuapp.com/api/auth/signup
   {"name":"mgr","username":"mgr","email":"mgr@email.com","password":"mgr","phoneNumber":"98765432","role":"MANAGER"}
3. Create a parking lot Resource   (Log in to https://binluis-parkingwebapp.herokuapp.com with User name: mgr & Password: mgr)
4. Assign a parking lot to a parking clerk
5. Create a car parking request at https://binluis-parking-mobile.herokuapp.com/requestformpage
6. Parking clerk accept the car parking request (Log in to https://binluis-parking-mobile.herokuapp.com/login with User name: clerk & Password: clerk)
7. Customer can view the status of his/her car parking request at https://binluis-parking-mobile.herokuapp.com/requestformpage
8. Parking clerk park the car
9. Manager can view the status of car parking requests
10. Manager can view the parking lot utilization rate
11. Customer ask for car picking up
12. Parking clerk accept the car picking up request and drive the car to customer's location

## Remark
Data will not persist, the database will erase all data whenever it is offline.

## Team Members (in alphabetical order)
- Connie Ip (Tech Lead & Full Stack)
- Iker Suen (Full Stack & Security Function)
- Kyle Yip (Fullstack)
- Joe Ho (Team Lead & Full Stack)
- Tommy Hui (Frontend)
- Venice Lam (Frontend)
