# **Library Filter System 📚**  

This project is a ** collaborative Java application**, developed together with another team member, designed for book enthusiasts, helping users discover books that match their preferences through an **interactive quiz**. It utilizes a **PostgreSQL database** to manage information about authors, books, and publishers, while offering a **graphical user interface (GUI)** for an intuitive user experience. The project also includes **unit testing** to ensure database integrity and functionality.  

---

## **🔹 Key Features**  

### **📌 Database Management**  
- Uses **Hibernate and JPA** for database interaction.  
- Enables adding and querying **authors, books, and publishers** through repository classes.  

### **📌 Book Filtering via Interactive Quiz**  
- Users answer a set of **literary preference questions**.  
- The system returns **personalized book recommendations** based on their responses.  

### **📌 Interactive Graphical Interface (GUI)**  
- Built using **Swing**, offering a visually appealing and user-friendly experience.  
- Includes a **homepage, quiz section, and results display**.  

### **📌 Logging & Debugging**  
- Implements a **logging system** to track activities and detect errors.    

### **📌 Unit Testing for Repository & Database Validation**  
- Includes **JUnit test cases** to verify repository operations.  
- Ensures proper functionality of **database transactions** and **entity retrieval**.  
- Example test cases:  
  - `create()` → Verifies author insertion into the database.  
  - `findById()` → Checks retrieval of an author by ID.  
  - `findByName()` → Ensures correct query results for authors with specific names.  

---

## **🔹 Technologies Used**  

- **Java 17+** (Core programming language)  
- **Swing** (GUI development)  
- **JPA & Hibernate** (ORM for database interaction)  
- **PostgreSQL** (Relational database)  
- **Apache Commons CSV** (For data import from CSV files)  
- **Log4J** (Logging system)  
- **JUnit** (Testing framework)  
- **Maven** (Dependency management)  



