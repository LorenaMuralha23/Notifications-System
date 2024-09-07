# About the Project👾

The idea of this API is to provide a way for companies to send notifications to their clients via email and/or SMS without requiring complex configurations.

## Project Objective 🎯

This project is part of my personal portfolio. Its main purpose is for personal learning about Java/Spring Boot, Message Brokers, API communication, and Microservices creation and communication.

I would greatly appreciate any feedback you can provide about the project, code, structure, or anything else that could help me become a better developer!

Email-me: marialorenamuralhalima2301@gmail.com

Connect with me at <a href="www.linkedin.com/in/dev-maria-lorena">LinkedIn</a>

Also, you can use this Project as you wish!

It's free!

<br>
<br>
    
# Installation and Execution Guide ⚙

## Prerequisites 📜

- Java 17.0.10 or higher
- Docker
- Twilio Account and Credentials (if you don't have a Twilio account or need help obtaining credentials, you can follow this tutorial: <a href="https://youtu.be/gmONYJBAp44">Twilio Tutorial</a>
- Gmail account and App password (if you don't know how to get the app password from your Gmail account, you can follow this tutorial: <a href="https://youtu.be/2D8jpws-4hA">G-Mail Tutorial</a>
- A software to test HTTP requests (e.g., Postman)

## Installation 📥

Replace "where-you-cloned" with the path to the directory where you cloned the repository.

1. Clone the Repository

```bash
git clone https://github.com/LorenaMuralha23/Notifications-System.git
```

2. Open your terminal and navigate to the directory where you cloned the project, then go to /Docker/RabbitMQ using:

```bash
    cd where-you-cloned\Notification-System\Docker\RabbitMQ
```

3. Run the docker image by using this command

```bash
    docker-compose up -d
```

## Running the Development environment 🖥️

Replace "where-you-cloned" with the path to the directory where you cloned the repository.

### Running the server

1. Navigate to the /target directory of the server using this command:

```bash
    cd where-you-cloned\Notification-System\demo\target
```

2. Run this command to start the server:

```bash
    java -jar demo-0.0.1-SNAPSHOT.jar
```

### Running the e-mail service

1. Navigate to the /target directory of the email service using this command:

```bash
    cd where-you-cloned\Notification-System\email-consumer\target
```

2. Run this command to start the email microservice:

```bash
    java -jar email-consumer-0.0.1-SNAPSHOT.jar
```

### Running the SMS service

1. Navigate to the /target directory of the SMS service using this command:

```bash
    cd where-you-cloned\Notification-System\sms-consumer\target
```

2. Run this command to start the SMS microservice:

```bash
    java -jar sms-consumer-0.0.1-SNAPSHOT.jar
```

## Testing the API 🧪

For this project, I have used POSTMAN for testing the HTTP requests. However, you can choose a tool of your preference.

For this guide, we will assume you have all the needed credentials.

### Email Service

For testing the email service, you need your Gmail app credentials (email address and App Password).

1. Configure your credentials before using the service. You can do this by sending a POST request to the following address:

```bash
    http://localhost:8080/api/notification/email/config
```

and sending a JSON with this structure:

```bash
{
    "username": "your-email@gmail.com",
    "password": "xxxx yyyy aaaa bbbb"
}
```

Replace "xxxx yyyy aaaa bbbb" with your App password.

2. Now you can send email notifications using this address:

```bash
    http://localhost:8080/api/notification/send
```

and a JSON with this structure:

```bash
{
    "title": "Your title",
    "body": "body message",
    "toSend": "who-you-want-send@email.com",
    "statusCode": 1,
    "deliveryMethodCode": 1
}
```

### SMS Service

For testing the SMS service, you need your Twilio credentials (Account SID, Auth Token, and Twilio Phone Number).

1. Configure your credentials before using the service. You can do this by sending a POST request to the following address:

```bash
    http://localhost:8080/api/notification/sms/config
```

and sending a JSON with this structure:

```bash
{
    "ACCOUNT_SID": "your account sid",
    "AUTH_TOKEN": "your auth token",
    "PHONE_NUMBER": "your twilio number"
}
```

1. Now you can send SMS notifications using this address:

```bash
    http://localhost:8080/api/notification/send
```

and a JSON with this structure:

```bash
{
    "title": "Your title",
    "body": "body message",
    "toSend": "+2378177381732837", #the number you want to send
    "statusCode": 1,
    "deliveryMethodCode": 2
}
```

<br>
<br>

# Built With 🛠️

- **Programming Languages:** Java
- **Frameworks:** Spring Boot, Spring AMQP
- **API's:** Gmail API, Twilio
- **Message Broker:** RabbitMQ
- **Containerization:** Docker
- **CI/CD:** GitHub Actions

<br>

# Contributing 🤝

Feel free to send as many pull requests (PRs) as you like; I'll be happy to review and accept them! If you have any questions about the project...

Email-me: marialorenamuralhalima2301@gmail.com

Connect with me at <a href="www.linkedin.com/in/dev-maria-lorena">LinkedIn</a>

Thank you!
