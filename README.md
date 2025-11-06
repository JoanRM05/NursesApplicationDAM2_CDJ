# 🏥 Nurses Management API

A Spring Boot RESTful API designed to manage nurses information within a healthcare system.  
This project provides endpoints for creating, reading, updating, deleting, and authenticating nurse records.  
It serves as a foundational backend for hospital management systems or any application requiring nurse data management.

---

## 📋 Description

The **Nurses Management API** allows CRUD operations and authentication for nurse entities.  
It simplifies the management of healthcare personnel by providing structured REST endpoints for interaction with a nurses database.

### 🩺 Main Features
- Create, read, update, and delete nurse records.
- Authenticate users via username and password.
- Search nurses by name or ID.
- Includes automated tests using JUnit and MockMvc.
- Configured with **GitHub Actions** for continuous integration (CI) using Maven.

---

## ⚙️ Installation

### 1️⃣ Prerequisites
Make sure you have installed:
- **Java 17** or higher  
- **Maven 3.8+**  
- **Git**  

### 2️⃣ Clone the Repository
```bash
git clone https://github.com/<your-username>/nurses.git
cd nurses
```

### 3️⃣ Build the Project
Use Maven to download dependencies and build the project:
```bash
mvn clean install
```

### 4️⃣ Run the Application
You can start the Spring Boot server using:
```bash
mvn spring-boot:run
```

The application will start by default on:
```
http://localhost:8080
```

---

## 🚀 Usage

Below are examples of the main endpoints provided by the API.

### ➕ Create a Nurse
**POST** `/nurse`  
**Body:**
```json
{
  "name": "Ana",
  "surname": "López",
  "email": "ana@hospital.com",
  "user": "ana",
  "pass": "1234"
}
```

### 🔍 Get All Nurses
**GET** `/nurse/index`  
Returns a list of all nurses in the database.

### 🧑 Get Nurse by ID
**GET** `/nurse/{id}`  
Example:  
```
/nurse/1
```

### 🧾 Find Nurse by Name
**GET** `/nurse/name?name=Luciana`

### 🔐 Login
**POST** `/nurse/login`  
**Body:**
```json
{
  "user": "luciana",
  "pass": "1234"
}
```
**Response:**  
- `true` → login successful  
- `false` → invalid credentials  

### ✏️ Update a Nurse
**PUT** `/nurse/{id}`  
**Body:**
```json
{
  "email": "newemail@hospital.com"
}
```

### ❌ Delete a Nurse
**DELETE** `/nurse/{id}`

---

## 🧪 Testing

The project includes unit and integration tests for all endpoints.  
To run tests, execute:
```bash
mvn test
```

---

## 🧰 Postman Collection

You can test the API using the Postman collection included in this repository.

- File: [`Nurses_API.postman_collection.json`](./postman/Nurses_API.postman_collection.json)
- Import it into Postman using **File → Import → Upload Files**.

---

## 🤖 Continuous Integration (CI)

This project uses **GitHub Actions** for automatic build and test execution on every push or pull request to the `master` branch.  
See the workflow file `.github/workflows/maven.yml` for details.

---

## 👩‍💻 Author
Developed by **Daiana Cañas Moya, Joan Rodrigues, Cassius Tedesco**

