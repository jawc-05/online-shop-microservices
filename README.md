# 🛒 Online Shop Microservices

[![pt-BR](https://img.shields.io/badge/lang-pt--BR-green)](README.pt-BR.md)

A complete microservices ecosystem for e-commerce developed with **Spring Boot 4 / Java 21**, utilizing distributed architecture, centralized configuration management, automatic service discovery, and NoSQL databases integrated via **Docker Compose**.

---

## 🏗️ System Architecture

The project is divided into 5 main components that communicate transparently through Docker's internal network:

1. **`Config Server` (Port 8888):** Centralized configuration server configured in `native` mode. It manages the `.yml` property files of all other services.
2. **`Service Discovery` (Port 8761):** Eureka server that acts as a service catalog, enabling dynamic load balancing via Spring Cloud LoadBalancer.
3. **`Client Service` (Port 8081 / Internal 8080):** Microservice responsible for customer management and persistence in MongoDB (`client_db`).
4. **`Product Service` (Port 8082 / Internal 8080):** Microservice that manages the product catalog and persistence in MongoDB (`product_db`).
5. **`Sale Service` (Port 8083 / Internal 8080):** Sales microservice that consumes customer and product data via **Feign Client** to process and record orders in MongoDB (`sale_db`).

---

## 🛠️ Technologies Used

* **Java 21** & **Spring Boot 4**
* **Spring Cloud Config** & **Spring Cloud Netflix Eureka**
* **Spring Cloud OpenFeign** & **Spring Cloud LoadBalancer**
* **Spring Data MongoDB**
* **Springdoc OpenAPI (Swagger UI)** for API documentation
* **Docker** & **Docker Compose**

---

## ⚙️ Prerequisites

Before starting, make sure you have installed on your machine:

* JDK 21
* Maven 3.x or Maven Wrapper (`./mvnw`) included in the projects
* Docker Desktop

---

## 🚀 How to Run the Project

### 1. Clone the repository and access the root

```bash
git clone https://github.com/jawc-05/online-shop-microservices.git
cd online-shop-microservices
```

### 2. Compile all microservices

Execute the packaging command in each of the directories to generate the `.jar` files needed for Docker images:

```bash
cd ConfigServer && ./mvnw clean package -DskipTests && cd ..
cd ServiceDiscovery && ./mvnw clean package -DskipTests && cd ..
cd ClientService && ./mvnw clean package -DskipTests && cd ..
cd ProductService && ./mvnw clean package -DskipTests && cd ..
cd SaleService && ./mvnw clean package -DskipTests && cd ..
```

### 3. Start the ecosystem with Docker Compose

In the project root (where the `docker-compose.yml` file is located), execute:

```bash
docker compose up --build
```

---

## 📌 Useful Links and Local Ports

After successful boot of the entire infrastructure, you can access the following services in your browser:

| Service | Local URL | Description |
|---------|-----------|-------------|
| Eureka Server | http://localhost:8761 | Monitoring dashboard of active instances |
| Config Server | http://localhost:8888/client-service/default | Endpoint to validate served properties |
| Client Swagger | http://localhost:8081/swagger-ui.html | Documentation and testing of Client routes |
| Product Swagger | http://localhost:8082/swagger-ui.html | Documentation and testing of Product routes |
| Sale Swagger | http://localhost:8083/swagger-ui.html | Documentation and testing of Sales routes |

---

## 🧪 Basic Testing Flow (Clean Slate)

Since the MongoDB database initializes empty, follow this order to test a complete sales flow:

### 1. Register a Customer

Make a POST request to `http://localhost:8081/client` or use the Clients Swagger. Copy the generated ID from the response.

### 2. Register a Product

Make a POST request to `http://localhost:8082/product` creating a valid item.

### 3. Perform the Sale

Send the sales request to the sale-service (`http://localhost:8083/sale`), pasting the actual customer ID generated in step 1:

```json
{
  "code": "01",
  "clientId": "PASTE_THE_ID_HERE",
  "saleDate": "2026-05-25"
}
```

The service will validate the customer's existence via internal synchronous communication and return status `201 Created`.

---

## 📝 License

This project is available for use and contributions. For more information, open an issue in the repository.
