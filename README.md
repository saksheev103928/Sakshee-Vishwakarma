# Sakshee Vishwakarma — Java Real-Time Portfolio

## Stack
- Java 17
- Spring Boot
- Spring WebSocket
- Spring Data JPA / Hibernate
- MySQL
- JavaScript
- HTML5
- CSS3

## Real-time communication
The Contact section uses a native WebSocket connection at `/ws`.
Messages are received by the Java Spring Boot backend, stored in MySQL, and immediately broadcast to connected browser tabs. The online visitor count also updates in real time.

Flow:

Browser → JavaScript WebSocket → Spring Boot → MySQL → WebSocket broadcast

## MySQL setup

Run:

```bash
mysql -u root -p < database/schema.sql
```

Or create the database manually:

```sql
CREATE DATABASE sakshee_portfolio;
```

Local defaults:
- DB: `sakshee_portfolio`
- User: `root`
- Password: `root`

For another database, set:

```bash
export DB_URL="jdbc:mysql://localhost:3306/sakshee_portfolio?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
export DB_USERNAME="root"
export DB_PASSWORD="YOUR_PASSWORD"
```

## Run

Requirements: Java 17+, Maven, MySQL.

```bash
mvn spring-boot:run
```

Open `http://localhost:8080`.

Open the portfolio in two browser tabs and send a message to test real-time communication.

## Build

```bash
mvn clean package
java -jar target/sakshee-realtime-portfolio-1.0.0.jar
```

## Deploy

The app is deployment-ready for a Java-capable host such as Render, Railway, or a VM. Configure `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD`. The server reads the hosting platform's `PORT` variable automatically.

For production use HTTPS/WSS and a managed MySQL database.

## Links
LinkedIn: https://www.linkedin.com/in/sakshee-vishwakarma-b19a2024a/
LeetCode: https://leetcode.com/u/saksheev103928/
Email: saksheev103928@gmail.com


## One-command local run with MySQL

From the project folder:

```bash
docker compose up --build
```

Then open `http://localhost:8080`. The Java backend, MySQL database and real-time WebSocket feature run together.

## Public deployment

This project is packaged with a `Dockerfile` and `docker-compose.yml` so it is deployment-ready. I cannot log into your hosting account or publish it under your account from this chat. To make it public, push this folder to GitHub and deploy the Dockerfile on a host that supports WebSockets and a MySQL database, then set `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, and `PORT`.


## Education
- B.Tech — Electronics & Instrumentation Engineering, Veer Bahadur Singh Purvanchal University, Jaunpur — Aug 2018 to Jul 2022 — 76.3%
- 12th, M I P Public Inter College, Jaunpur — Jul 2017 to May 2018 — 65%
- 10th — Mathematics, Munnar Ram UMV College, Jaunpur — Jul 2014 to May 2016 — 82%


## Profile additions
- Cyber Security Engineer internship experience at GICSEH Data Security Private Limited.
- Java/Spring Boot real-time communication and IP-based announcement work at Coral Telecom.
- Production support experience at Tech Mahindra.
- HackerRank certifications: Software Engineer, SQL (Advanced), REST API (JAVA).
- Cybersecurity learning: Network Security, Ethical Hacking, Vulnerability Assessment, Penetration Testing and Linux.
