
# to Java 21 and SpringBoot

This project contains the migration of the application to Java 21 and SpringBoot

After checking out the project go into the `kitchensinkh2` directory and run the steps below




## How to start the application

To run this project, you will need to run the following steps

    1. mvn clean install

    2. mvn spring-boot:run -X


## API Reference

#### Get all Members

```http
  GET /members/allMembers
```


#### Register a member

```http
  POST /members/register
```

Sample JSON iput 
```
{
    "name": "John Doe",
    "email": "john.doe@example.com",
    "phoneNumber": "3333330000"
}
```


