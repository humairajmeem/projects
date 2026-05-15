<?php
include('db.php');

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $section = $_POST['section'];

    // Query to fetch class details by section
    $classQuery = "SELECT * FROM classes WHERE section = '$section'";
    $classResult = $conn->query($classQuery);

    if ($classResult->num_rows > 0) {
        $classData = $classResult->fetch_assoc();
        $class_id = $classData['class_id'];
        $total_students = $classData['total_students'];

        // Query to fetch students for the selected class
        $studentsQuery = "SELECT * FROM students WHERE class_id = $class_id";
        $studentsResult = $conn->query($studentsQuery);
    } else {
        echo "No class found with section: $section";
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Class by Section</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <form method="POST" action="search_class.php">
        Class Section: <input type="text" name="section" required><br>
        <button type="submit">Search</button>
    </form>

    <?php if (isset($studentsResult) && $studentsResult->num_rows > 0): ?>
        <h3>Students in Class Section: <?php echo $section; ?></h3>
        <form method="POST" action="take_attendance.php">
            <input type="hidden" name="class_id" value="<?php echo $class_id; ?>">
            <input type="hidden" name="date" value="<?php echo date('Y-m-d'); ?>">

            <?php while ($student = $studentsResult->fetch_assoc()): ?>
                <label>
                    <input type="checkbox" name="students[]" value="<?php echo $student['student_id']; ?>">
                    <?php echo $student['name']; ?>
                </label>
                <select name="statuses[]">
                    <option value="present">Present</option>
                    <option value="absent">Absent</option>
                </select><br>
            <?php endwhile; ?>

            <button type="submit">Submit Attendance</button>
        </form>
    <?php endif; ?>
</body>
</html>