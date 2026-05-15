<?php
include('db.php');

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $class_id = $_POST['class_id'];
    $name = $_POST['name'];

    $sql = "INSERT INTO students (class_id, name) VALUES ('$class_id', '$name')";
    
    if ($conn->query($sql) === TRUE) {
        echo "New student added successfully";
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Student</title>
</head>
<body>
    <form method="POST" action="add_students.php">
        Class ID: <input type="number" name="class_id" required><br>
        Name: <input type="text" name="name" required><br>
        <button type="submit">Add Student</button>
    </form>
</body>
</html>
