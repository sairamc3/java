# Deploying Spring Boot applications

## Spring boot deployment options
1. Traditional
2. Container-based
3. Cloud-based
4. Non-Web based

## Project specifications

- Read-Only contact info backend server application

### Technical Features

- Rest Api, relational DB, no UI
- Spring Boot, Spring web, Spring data, H2 DB

## Steps

1. Clone the `contacts` maven project
2. Build using `mvn clean install`
3. Run the application in an IDE or using java `java -jar` or using spring `mvn spring-boot:run`
4. There are two api's that you can hit
```bash
http :8080
```
```text
Status: Running, version: 0.0.1-SNAPSHOT
```

```bash
http :8080/contacts
```

```json
[
    {
        "email": "jsmith@pluralsight.com",
        "id": 1,
        "name": "John Smith",
        "phone": "123-456-7890"
    },
    {
        "email": "sdavis@pluralsight.com",
        "id": 2,
        "name": "Samantha Davis",
        "phone": "098-765-4321"
    }
]
```
