
### 🔹 Frontend (HTML + Bootstrap + jQuery)

The frontend code is available here:  
👉 [aad-frontend-final](https://github.com/thambe96/aad-frontend-final)

1. Clone the repository:

   ```bash
   git clone https://github.com/thambe96/aad-frontend-final.git
   cd aad-frontend-final



# PetCare Fund

## 📌 Project Description

PetCare Fund is a full-stack web application designed to help pet owners raise funds for their pets' medical treatments. The platform enables **pet owners** to register their pets, create treatment requests, and upload health records, while **administrators** can verify requests and manage the system efficiently. Donors and users can view treatment requests and contribute towards the medical costs of the pets.

The project is built using **Spring Boot (backend)** and a **Bootstrap/jQuery frontend**, with **JWT authentication** for secure access.

---

## 📷 Screenshots

### 1. Admin Dashboard



### 1. Admin Dashboard
![Admin Dashboard](assets/admin dashboard.png)

### 2. Admin – Verify Requests Feature
![Verify Requests](assets/admin feature to verity request.png)

### 3. Create Treatment Request (Pet Owner)
![Create Treatment Request](assets/creating the treatment request.png)

### 4. Pet-Owner Dashboard (Pet Owner)
![Pet owner dashboard](assets/pet owner dashboard.png)


### 5. Create New Pet (Pet Owner)
![Create New Pet](assets/registering the pet.png)


### 6. Sponsor Dashboard (Sponsor)
![Sponsor dashboard](assets/sponsor dashboard.png)


### 7. Make the Donation using visa card (Sponsor)
![Make Donation](assets/selecting payment card.png)











---

## ⚙️ Setup Instructions

### 🔹 Backend (Spring Boot)

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/petcare-fund-backend.git
   cd petcare-fund-backend
   ```

2. Configure the database in `application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/petcare_fund
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

3. Run the backend application:

   ```bash
   ./mvnw spring-boot:run
   ```

   Backend will start at: [**http://localhost:8080**](http://localhost:8080)

---

### 🔹 Frontend (HTML + Bootstrap + jQuery)

1. Clone the repository:

   ```bash
   https://github.com/thambe96/aad-backend-final
   
   ```

2. Open the project folder and launch `index.html` using **Live Server** or any static server (e.g., VS Code Live Server).

   ```
   http://127.0.0.1:5500/index.html
   ```

3. Ensure the backend is running so the frontend can communicate with the API.

---

### 🔹 Authentication & Roles

- **Login/Register** via `/api/v1/auth` endpoints.
- Roles: `PET_OWNER`, `ADMIN` , `SPONSOR`.
- Use the JWT token from login response for authenticated requests.

---

## 🚀 Features

- Pet Owners:
  - Register new pets.
  - Create treatment requests with estimated cost.
  - Upload health records for verification.
- Admins:
  - View all treatment requests.
  - Verify and change request statuses.
- Sponsor:
    - View verified treatment requests.
    - Make Donations.  
- Dynamic UI with Bootstrap and jQuery.
- Image upload support for pets and health records with Cloudinary.
- Stripe payment gateway 
- JWT-based secure authentication.

---

## 🛠️ Tech Stack

- **Backend:** Spring Boot, Spring Security (JWT), Hibernate, MySQL
- **Frontend:** HTML, Bootstrap, jQuery, AJAX
- **Cloud:** Cloudinary (for image storage)
- **Payment Gateway:** Stripe (for payment transaction handling)

---

## 👨‍💻 Contributors

- **Kasun** – Admin features
- **Siril** – Pet owner features
- **Anupama** – Sponsor
- **Oshadha Thambavita** – Full-stack developer (integration and enhancements)

---


---

## 🔗 Related Repositories

- **Frontend Repository:** [aad-frontend-final](https://github.com/thambe96/aad-frontend-final)
- **Backend Repository:** (this repo)

---

## 📺 Project Walkthrough (YouTube)

Watch the full project explanation here:  
👉 [PetCare Fund – Demo & Explanation](https://youtu.be/sykRU8xtZ4s)

---






## 📜 License

This project is licensed under the MIT License. You are free to use, modify, and distribute this project with attribution.

