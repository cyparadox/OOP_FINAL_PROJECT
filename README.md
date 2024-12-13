# Healthcare Management System

## Project Overview
The Healthcare Management System is a Java-based application designed to streamline the management of healthcare appointments. It provides functionalities for managing patients, doctors, and appointment scheduling. With features like automated doctor assignment based on ailments and appointment rescheduling, the system ensures efficient and user-friendly healthcare management.

## Application of OOP Principles
This project leverages Object-Oriented Programming (OOP) principles to ensure modularity, reusability, and scalability:

1. **Encapsulation**:
   - Data members of classes such as `Patient`, `Doctor`, and `Appointment` are private, with public getters and setters to control access.
   - The encapsulation ensures secure handling of sensitive information like patient details.

2. **Abstraction**:
   - The system abstracts complex operations like automatic doctor assignment and appointment scheduling into methods, hiding unnecessary implementation details from the user.

3. **Inheritance**:
   - While not directly demonstrated in the current implementation, the system’s modular design allows for extending base functionality by inheriting and overriding classes in future developments.

4. **Polymorphism**:
   - Methods like `toString()` are overridden in multiple classes to provide class-specific details when objects are printed, demonstrating polymorphic behavior.

## Alignment with SDG (Sustainable Development Goal)
The project aligns with **SDG 3: Good Health and Well-being**, which aims to ensure healthy lives and promote well-being for all at all ages. By facilitating easy appointment booking, automated doctor assignment, and efficient healthcare management, the system promotes accessible healthcare services, contributing to this global goal.

## Instructions for Running the Program

### Prerequisites
- **Java Development Kit (JDK)**: Ensure JDK 8 or later is installed.
- **IDE or Text Editor**: An IDE like IntelliJ IDEA, Eclipse, or a text editor with Java support.

### Steps to Run
1. Clone or download the project files.
2. Open the project in your preferred IDE or ensure all `.java` files are in the same directory.
3. Compile the program using the following command in your terminal:
   ```bash
   javac HealthManagementSystem.java
   ```
4. Run the compiled program:
   ```bash
   java HealthManagementSystem
   ```

### Usage
Upon running the program, follow the interactive menu to:
1. View available doctors.
2. Book, cancel, or reschedule appointments.
3. View the list of scheduled appointments.

The program ensures a smooth user experience with detailed prompts at every step.

### Note
The application uses a basic in-memory data structure for managing data. Future iterations may integrate persistent storage or a database for enhanced functionality.

