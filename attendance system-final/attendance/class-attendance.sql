CREATE DATABASE class_attendance;

USE class_attendance;

-- Table for storing class data
CREATE TABLE classes (
    class_id INT AUTO_INCREMENT PRIMARY KEY,
    section VARCHAR(50),
    total_students INT
);

-- Table for storing student data
CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    class_id INT,
    name VARCHAR(100),
    FOREIGN KEY (class_id) REFERENCES classes(class_id)
);

-- Table for storing attendance data
CREATE TABLE attendance (
    attendance_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    date DATE,
    status ENUM('present', 'absent') NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(student_id)
);
