# Bajaj Broking – Trading API Wrapper SDK

## 📌 Overview

This project is a **simulated Trading Backend SDK** built as part of the **Bajaj Broking Campus Hiring Assignment**.
It demonstrates backend system design, RESTful API development, and fundamental trading domain concepts such as **orders, trades, and portfolio management**.

The system is **fully simulated** and does **not connect to real markets or live Bajaj APIs**.

---

## 🛠 Tech Stack

* **Language:** Java 17
* **Framework:** Spring Boot 3.x
* **API Style:** REST (JSON)
* **Database:** In-memory storage (H2 / Map-based)
* **Documentation:** Swagger / OpenAPI 3.0
* **Build Tool:** Maven

---

## 🚀 Features Implemented

### 1️⃣ Instruments

* Fetch list of tradable instruments
* Fields: symbol, exchange, instrumentType, lastTradedPrice

### 2️⃣ Order Management

* Place BUY / SELL orders
* MARKET and LIMIT order types
* Order validation
* Order lifecycle states:

  * NEW
  * PLACED
  * EXECUTED
  * CANCELLED

### 3️⃣ Trade Management

* Automatic trade creation when orders are executed
* Trades are immutable execution records

### 4️⃣ Portfolio Management

* Portfolio derived from executed trades
* Calculates:

  * Quantity
  * Average Buy Price
  * Current Value (based on latest price)

### 5️⃣ Non‑Functional Enhancements

* Centralized exception handling (`@ControllerAdvice`)
* Clean layered architecture (Controller → Service → Repository)
* Swagger UI for API testing and documentation

---

## 🧩 System Architecture

```
Controller  →  Service  →  Repository  →  In‑Memory Storage
```

* **Controller:** Handles HTTP requests and responses
* **Service:** Business logic & trading rules
* **Repository:** Data access (simulated in-memory)

---

## ⚙️ Setup & Run Instructions

### Prerequisites

* Java 17 installed
* Maven installed

### Steps

```bash
# Clone the repository
git clone <your-repo-url>

# Navigate to project directory
cd trading-sdk

# Build project
mvn clean install

# Run application
mvn spring-boot:run
```

Application will start at:

```
http://localhost:8080
```

---

## 📘 Swagger / API Documentation

Once the application is running, open:

```
http://localhost:8080/swagger-ui/index.html
```

You can:

* View all APIs
* Execute requests
* Inspect request/response models

---

## 🔗 API Endpoints

### Instruments

```
GET /api/v1/instruments
```

### Orders

```
POST /api/v1/orders
GET  /api/v1/orders/{orderId}
```

### Trades

```
GET /api/v1/trades
```

### Portfolio

```
GET /api/v1/portfolio
```

---

## 📌 Sample API Usage

### Place Market Buy Order

```json
POST /api/v1/orders
{
  "symbol": "TCS",
  "orderType": "BUY",
  "orderStyle": "MARKET",
  "quantity": 10
}
```

### Fetch Order Status

```
GET /api/v1/orders/{orderId}
```

### Fetch Trades

```
GET /api/v1/trades
```

### Fetch Portfolio

```
GET /api/v1/portfolio
```

---

## 🧠 Order Execution Logic (Simulation)

* **MARKET Order**

  * Executes immediately
  * Uses last traded price

* **LIMIT Order**

  * BUY executes if limit price ≥ LTP
  * SELL executes if limit price ≤ LTP
  * Otherwise remains PLACED

---

## 📝 Assumptions

* Single hardcoded user (authentication mocked)
* No real market connectivity
* Portfolio is derived dynamically (not stored)
* SELL trades reduce quantity (simplified logic)

---

## 🎯 Evaluation Focus

This project demonstrates:

* REST API design
* Backend architecture
* Trading domain understanding
* Clean and maintainable code
* Error handling and documentation

---

## 👤 Author

**Lakshya Khandelwal**
Campus Hiring Assignment – Bajaj Broking

---

✅ *Assignment complete and ready for evaluation.*
