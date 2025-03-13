# Library Management System (JavaFX)

This **Library Management System** is built with **JavaFX**, designed to efficiently handle book borrowing, returns, fines, and user management with full **CRUD** operations.

## 🚀 Features
- 📚 **Book Management** – Add, update, delete, and view books.  
  ![Book Management](images/book_management.png)
- 👤 **User & Customer Management** – Full CRUD operations for customers and system users.  
  ![User Management](images/user_management.png)
- 🔄 **Borrow & Return Management** – Add, update, delete, and view borrowed books and returns.  
  ![Borrow & Return](images/borrow_return.png)
- 💰 **Fine Management** – Full CRUD operations for calculating, updating, deleting, and managing late return fines.  
  ![Fine Management](images/fine_management.png)
- 📑 **Generate Reports** – Use **JasperReports** to create book borrowing reports.  
  ![Report Generation](images/report_generation.png)
- 📧 **Forgot Password OTP** – Secure **JavaMail-based OTP** system for password resets.  
  ![OTP System](images/otp_system.png)
- 🎨 **Custom UI Design** – Styled with **CSS** for a modern look.  
  ![Custom UI](images/custom_ui.png)
- 🏗️ **Layered Architecture** – Well-structured, modular code for maintainability.  
- 🛢️ **Hybrid Database Approach** – Uses **Hibernate** with custom **MySQL queries**.  

## 🛠️ Tech Stack
- **JavaFX** for UI  
- **Hibernate & MySQL** for data management  
- **JasperReports** for reporting  
- **JavaMail** for email services  
- **CSS** for UI customization  

## 📦 Installation & Setup

1. **Clone the repository:**  
   ```sh
   git clone https://github.com/your-username/library-management-system.git
   ```

2. **Open the project in your preferred IDE (IntelliJ IDEA, Eclipse, NetBeans, etc.).**

3. **Set up the database:**  
   - Create a MySQL database.
   - Import the provided database schema (`database.sql`).
   - Update database connection settings in `hibernate.cfg.xml`.

4. **Configure Email for OTP Service:**  
   - Update SMTP settings in the project configuration.

5. **Run the Application:**  
   - Compile and run the JavaFX application.

## 📜 License
This project is open-source and available under the **MIT License**.

## 📬 Contact
For any issues or feature requests, feel free to open an issue or contact me at [your email or GitHub profile].
