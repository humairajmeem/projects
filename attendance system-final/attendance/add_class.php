<?php
include('db.php');

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $section = $_POST['section'];
    $total_students = $_POST['total_students'];

    $sql = "INSERT INTO classes (section, total_students) VALUES ('$section', '$total_students')";
    
    if ($conn->query($sql) === TRUE) {
        echo "New class added successfully";
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
    <title>Add Class</title>
</head>
<body>
    <form method="POST" action="add_class.php">
        Section: <input type="text" name="section" required><br>
        Total Students: <input type="number" name="total_students" required><br>
        <button type="submit">Add Class</button>
    </form>
</body>
</html>
