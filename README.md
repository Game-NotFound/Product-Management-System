# Product Management System

Welcome to the Product Management System, which consists of two separate applications: one for managing data using Hibernate and another for the user interface using JavaFX. Both applications connect to a MySQL database.

## 1. Hibernate Project

### Description
This project uses Hibernate to manage product entities in a MySQL database. It allows you to easily perform CRUD (Create, Read, Update, Delete) operations.

### Technologies
- **Hibernate**: ORM library for Java.
- **MySQL**: Database management system.
- **Java**: Primary programming language.

### Setup
1. Install MySQL and create a new database.
2. Configure the connection information in `hibernate.cfg.xml`.
3. Run the application to perform database operations.

### Usage
- Entity classes are defined in the `model` directory.
- Use `SessionFactory` to manage sessions with the database.
- Examples of CRUD operations are available in the `Main` class.

---

## 2. JavaFX Project

### Description
This project builds a user interface application using JavaFX, allowing users to interact with product data from the MySQL database via Hibernate.

### Technologies
- **JavaFX**: UI library for Java.
- **Hibernate**: For data management.
- **MySQL**: Database management system.

### Setup
1. Install the JavaFX SDK and add it to the classpath.
2. Ensure that Hibernate is configured correctly.
3. Run the JavaFX application to open the user interface.

### Usage
- The interface allows users to add, edit, delete, and view product information.
- All data operations are handled through Hibernate, ensuring consistency and performance.

---

## Contact
If you have any questions, please contact me at: [email@example.com].

Thank you for checking out this project!
