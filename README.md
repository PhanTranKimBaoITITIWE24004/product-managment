# Product Management System

## Student Information
- **Name:** Phan Tran Kim Bao
- **Student ID:** ITITIWE24004
- **Class:** Web Application Development_S2_2025-26_G02_lab01

## Technologies Used
- Spring Boot 4.1.0-SNAPSHOT
- Spring Data JPA
- MySQL 8.0
- Thymeleaf
- Maven

## Setup Instructions
1. Import project into VS Code
2. Create database: `product_management`
3. Update `application.properties` with your MySQL credentials
4. Run: `mvn spring-boot:run`
5. Open browser: http://localhost:8080/products

## Completed Features
- [x] CRUD operations
- [x] Search functionality
- [x] Advanced search with filters
- [x] Validation
- [x] Sorting
- [x] Pagination
- [ ] REST API (Bonus)

## Project Structure
```bash
product-management/
│
├── .mvn/wrapper/
│   └── maven-wrapper.properties            # Contains Maven Wrapper version and distribution properties
├── src/
│   ├── main/
│   │   ├── java/com/example/productmanagement/
│   │   │   ├── controller/
│   │   │   │   └── ProductController.java   # Controller handling web routing endpoints and model mappings
│   │   │   ├── entity/
│   │   │   │   └── Product.java            # JPA Entity object representing the 'products' schema model table
│   │   │   ├── repository/
│   │   │   │   └── ProductRepository.java  # Repository interface encapsulating Spring Data JPA queries
│   │   │   ├── service/
│   │   │   │   ├── ProductService.java     # Service interface declaring operational business contracts
│   │   │   │   └── ProductServiceImpl.java # Transactional implementation containing business domain logic
│   │   │   └── ProductManagementApplication.java # Primary bootstrap application entry execution point
│   │   │
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── product-form.html       # Thymeleaf input dashboard layout for inserting or updating items
│   │       │   └── product-list.html       # Central UI view template displaying inventory records
│   │       └── application.properties       # Environment properties file maintaining data connections and configurations
│   │
│   └── test/java/com/example/product_management/
│       └── ProductManagementApplicationTests.java # Core Spring Boot test execution configuration file
│
├── .gitattributes                          # Defines explicit line endings paths behavior rules for tracking attributes
├── .gitignore                              # Declares untracked environment build directories and paths to safely ignore
├── mvnw                                    # Executable Maven Wrapper shell compilation engine utility for UNIX platforms
├── mvnw.cmd                                # Executable Maven Wrapper compilation batch execution file for Windows machines
├── pom.xml                                 # Declarative configuration script holding definitions and frameworks dependency trees
└── product_management.sql                  # Initial database structural layout file with sample table populations
```
## Database Schema
See `product_management.sql` for database structure.

## Known Issues
- No backend-driven validation alerts displayed inside the edit/create form view if constraints are violated in the database layer (relies strictly on HTML native required and step behaviors).
- Deleting a non-existent ID redirects to the index view safely but does not log a domain-specific custom missing exception.

## Time Spent
Approximately 8 hours
