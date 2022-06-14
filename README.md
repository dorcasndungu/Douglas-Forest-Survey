#  Douglas Fir Forest Survey

## Description
This is an application that stores data given by game wardens in a database and displays it using handlebars. It uses Spark REST routing and SQL queries.
## Technologies used
- Postgresql
- SparkJava
- Handlebars
- Intelli j
- Gradle
- Junit

## Behavior Driven Development



| Behavior                   | Input                                                                   | Expected Output                                                                                           |
|----------------------------|-------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|
| Register Ranger            | Ranger name and KWS pin                                                 | A new Sighting object is created that contains ranger details                                                 |
|  Ranger can record animal  |      Ranger enters animal details in a form                             | A new animal object is stored in database                                             |
| View Sightings and Ranger  | User is directed after submitting animal form                           | Animal details are displayed and the ranger's name                                             |


## Setup
### Prerequisites
- Postgresql ( 14.2 )
- Java Development Kit ( JDK )
- Gradle

### Install

Clone the repository using the following command:
```
git clone https://github.com/dorcasndungu/Douglas-Forest-Survey.git
```

### Recreate Database
Navigate to the root directory of the project in your terminal and Run the following command to recreate the database on your local machine
```
psql < create.sql;
```
If the database seems to be populated, reset it using:
```
psql < drop.sql
```
and recreate it again.


### Run
Open the project in Intellij Idea and refresh gradle to download dependencies.  
After downloading dependencies, run
```
gradle build
```  
and thereafter
```
gradle run
```
to ignite a Spark server.The project can now be viewed at
```
localhost:4567
```

## Live Link


## Contact
For any issues, additional requests or compliments, reach out to me using:
dorcas.ndungu@student.moringaschool.com



## License and Copyright

### License
This software is under the [MIT](LICENSE) license
Copyright (c) {2022} 
