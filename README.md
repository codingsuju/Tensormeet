# Tensormeet
Tensormeet is social media server written in Java integrated with Spring Security to protect resource database from unauthorized access which also includes preventing one user from accessing other user's data. 

# Run the project
* Edit application.properties file
```
spring.profiles.active=local
```
* Run the following command from the root of the project
```
mvn spring-boot:run
```
If you do not have Maven installed locally on your machine, run the following command instead
```
./mvnw spring-boot:run
```
